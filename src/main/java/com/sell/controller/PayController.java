package com.sell.controller;

import com.sell.dto.OrderDTO;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by huhaoran on 2018/12/10 0010.
 */

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl) {
        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付

    }
}
