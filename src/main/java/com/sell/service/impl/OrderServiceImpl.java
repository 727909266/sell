package com.sell.service.impl;

import com.sell.dao.OrderDetailDao;
import com.sell.dao.OrderMasterDao;
import com.sell.dto.OrderDTO;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.model.ProductInfo;
import com.sell.service.OrderService;
import com.sell.service.ProductInfoService;
import com.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
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
            BigDecimal orderAmount = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()));
            //订单详情入库 orderDetail
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailDao.saveModel(orderDetail);
            return orderAmount;

        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        //写入订单数据库(orderMaster)




        //扣库存

        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public List<OrderDTO> findList(String buyerOpenid) {
        return null;
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
