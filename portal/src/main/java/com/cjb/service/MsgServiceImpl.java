package com.cjb.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cjb.api.OrderApi;
import com.cjb.api.WarehouseApi;
import com.cjb.dao.MsgDao;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MsgServiceImpl implements MsgService {

    @Reference(version = "1.0.0")
    public OrderApi orderApi;

    @Reference(version = "1.0.0")
    public WarehouseApi warehouseApi;

    @Autowired
    MsgDao msgDao;

    @Override
    @LcnTransaction //分布式事务注解
    @Transactional
    public void addMsg(){
        //本地业务
        msgDao.addMsg(UUID.randomUUID().toString());
        //下订单
        orderApi.test();
        //扣减库存
        warehouseApi.test();
    }
}
