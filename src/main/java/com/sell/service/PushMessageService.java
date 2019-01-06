package com.sell.service;

import com.sell.dto.OrderDTO;

/**
 * 推送消息
 * Created by huhaoran on 2019/1/6 0006.
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
