package com.cjb.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cjb.api.OrderApi;
import com.cjb.api.WarehouseApi;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PortalService {

    @Reference(version = "1.0.0")
    public OrderApi orderApi;

    @Reference(version = "1.0.0")
    public WarehouseApi warehouseApi;

    @LcnTransaction //分布式事务注解
    public void test(){
        //下订单
        orderApi.test();
        //扣减库存
        warehouseApi.test();

    }
}
