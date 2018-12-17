package com.sell.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sell.dao.OrderDetailDao;
import com.sell.dao.OrderMasterDao;
import com.sell.dto.CartDTO;
import com.sell.dto.OrderDTO;
import com.sell.enums.OrderStatusEnum;
import com.sell.enums.PayStatusEnum;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.model.OrderDetail;
import com.sell.model.OrderMaster;
import com.sell.model.ProductInfo;
import com.sell.service.OrderService;
import com.sell.service.PayService;
import com.sell.service.ProductInfoService;
import com.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private PayService payService;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        //查询商品（数量，单价）//肯定需要查看该商品库存以及数据库中的价格，不能相信前端传入的价格数据
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderTotalAmount = orderDTO.getOrderDetailList().stream().map(orderDetail -> {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo == null) {
                /*
                    Java中的异常分为两类，受检查异常（Checked Exception）跟非受检异常（UnChecked Exception）

                    非受检异常就是运行时异常,其他是受检类异常,主要为IOException及其子类

                    forEach中的Consumer中accept没有throws,所以在Stream的forEach方法中如果抛出受检异常，那么我们必须要把它捕获吃掉，而不能抛给上一级

                    编写一个泛型方法对异常进行包装
                    static <E extends Exception> void doThrow(Exception e) throws E {
                        throw (E)e;
                    }
                    这里的原理是利用泛型把要抛出异常的类型隐藏起来了，从泛型方法的声明来看，编译器不能明确的知道抛出异常类型
                */
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //计算总价
            BigDecimal orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()));
            //订单详情入库 orderDetail
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailDao.saveModel(orderDetail);
            return orderAmount;

        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        //写入订单数据库(orderMaster)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderTotalAmount);
        orderMasterDao.saveModel(orderMaster);

        //扣库存
        List<CartDTO> cartDTOList =  orderDTO.getOrderDetailList().stream().map(orderDetail ->
            new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findById(orderId);
        if(orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findList(String buyerOpenid, int page, int size) {
        PageInfo<OrderMaster> pageInfoOrderMaster = PageHelper.startPage(page, size).doSelectPageInfo(() -> orderMasterDao.findByBuyerOpenid(buyerOpenid));
        List<OrderDTO> orderDTOList = pageInfoOrderMaster.getList().stream().map(orderMaster -> {
            //List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderMaster.getOrderId());
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            //orderDTO.setOrderDetailList(orderDetailList);
            return orderDTO;
        }).collect(Collectors.toList());
        return orderDTOList;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[取消订单]订单状态不正确, order={}, orderStatus={}",orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
        }
        //修改订单状态->取消
        int check = orderMasterDao.updateOrderStatus(OrderStatusEnum.CANCEL.getCode(), orderDTO.getOrderId());
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());//为测试写的
        if(check == 0) {
            log.error("[取消订单] 更新失败 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[取消订单] 订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(orderDetail -> {
            return new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
        }).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        //如果已经支付，退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            payService.refund(orderDTO);

        }
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[完结订单] 订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        int check = orderMasterDao.updateOrderStatus(OrderStatusEnum.FINISH.getCode(), orderDTO.getOrderId());
        if(check == 0) {
            log.error("[完结订单] 更新失败 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[订单支付完成] 订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("[订单支付完成] 订单支付状态不正确，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        int check = orderMasterDao.updatePayStatus(PayStatusEnum.SUCCESS.getCode(), orderDTO.getOrderId());
        if(check == 0) {
            log.error("[订单支付完成] 更新失败 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findList(int page, int size) {
        PageInfo<OrderMaster> pageInfoOrderMaster = PageHelper.startPage(page, size).doSelectPageInfo(() -> orderMasterDao.findAll());
        List<OrderDTO> orderDTOList = pageInfoOrderMaster.getList().stream().map(orderMaster -> {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            //orderDTO.setOrderDetailList(orderDetailList);
            return orderDTO;
        }).collect(Collectors.toList());
        return orderDTOList;
    }
}
