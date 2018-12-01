package com.sell.service.impl;

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
import com.sell.service.ProductInfoService;
import com.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;
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
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
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
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findList(String buyerOpenid) {
        List<OrderMaster> orderMasterList = orderMasterDao.findByBuyerOpenid(buyerOpenid);
        List<OrderDTO> orderDTOList = orderMasterList.stream().map(orderMaster -> {
            List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderMaster.getOrderId());
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            orderDTO.setOrderDetailList(orderDetailList);
            return orderDTO;
        }).collect(Collectors.toList());
        return orderDTOList;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {

        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {

        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {

        return null;
    }

    @Override
    public List<OrderDTO> findList() {

        return null;
    }
}
