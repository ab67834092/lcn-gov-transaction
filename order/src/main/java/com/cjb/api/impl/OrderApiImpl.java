package com.cjb.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cjb.api.OrderApi;
import com.cjb.service.OrderService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(version = "1.0.0")
public class OrderApiImpl implements OrderApi {

    @Autowired
    OrderService orderService;

    @Override
    public void test() {
        orderService.test();
    }
}
