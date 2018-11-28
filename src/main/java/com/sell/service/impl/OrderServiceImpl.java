package com.sell.service.impl;

import com.sell.dao.OrderDetailDao;
import com.sell.dao.OrderMasterDao;
import com.sell.dto.OrderDTO;
import com.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //查询商品（数量，单价）
        orderDTO.getOrderDetailList().forEach(orderDetail -> {

        });
        //计算总价
        //写入订单数据库(orderMaster,orderDetail)
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
