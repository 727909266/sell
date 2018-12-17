package com.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.sell.dto.OrderDTO;

/**
 * Created by huhaoran on 2018/12/10 0010.
 */
public interface PayService {

    PayResponse notify(String notifyData);
    PayResponse create(OrderDTO orderDTO);
    RefundResponse refund(OrderDTO orderDTO);
}
