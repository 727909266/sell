package com.sell.controller;

import com.sell.dto.OrderDTO;
import com.sell.service.OrderService;
import io.swagger.models.auth.In;
import okhttp3.internal.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by huhaoran on 2018/12/16 0016.
 */

@Controller
@RequestMapping("/seller/order")
public class SellerOrderContriller {
    @Autowired
    private OrderService orderService;

    /**
     *
     * @param page 第几页 从1开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam("page") Integer page,
                             @RequestParam("size") Integer size,
                             Map<String, Object> map) {
        List<OrderDTO> orderDTOPage = orderService.findList(page - 1, size);
        map.put("orderDTOPage", orderDTOPage);
        return new ModelAndView("order/list", map);
    }
}
