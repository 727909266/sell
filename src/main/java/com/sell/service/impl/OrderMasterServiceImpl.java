package com.sell.service.impl;

import com.sell.dao.OrderMasterDao;
import com.sell.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private OrderMasterDao orderMasterDao;
}
