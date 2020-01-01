package com.cjb.service;

import com.cjb.dao.OrderDao;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    @LcnTransaction
    public void test(){
        //下订单
        orderDao.test("order:"+UUID.randomUUID().toString());

    }
}
