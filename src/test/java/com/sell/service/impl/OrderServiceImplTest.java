package com.sell.service.impl;

import com.sell.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Test
    public void create(OrderDTO orderDTO) {

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