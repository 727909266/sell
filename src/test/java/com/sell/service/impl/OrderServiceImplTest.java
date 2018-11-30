package com.sell.service.impl;

import com.sell.dto.OrderDTO;
import com.sell.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "1101110";

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("胡浩然");
        orderDTO.setBuyerAddress("火星");
        orderDTO.setBuyerPhone("123456789012");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        //orderDetail.setProductId();
        //orderDetail.setProductQuantity();

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderServiceImpl.create(orderDTO);
        log.info(" [创建订单] result={}", result);


    }

    @Test
    public void findOne(String orderId) {
    }

    @Test
    public void findList(String buyerOpenid) {

    }

    @Test
    public void cancel(OrderDTO orderDTO) {

    }

    @Test
    public void finish(OrderDTO orderDTO) {

    }

    @Test
    public void paid(OrderDTO orderDTO) {

    }

    @Test
    public void findList() {

    }

}