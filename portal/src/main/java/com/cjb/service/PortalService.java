package com.cjb.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cjb.api.OrderApi;
import com.cjb.api.WarehouseApi;
import com.cjb.dao.MsgDao;
import com.cjb.lock.LockService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PortalService {

    @Reference(version = "1.0.0")
    public OrderApi orderApi;

    @Reference(version = "1.0.0")
    public WarehouseApi warehouseApi;

    @Autowired
    LockService lockService;

    @Autowired
    MsgService msgService;

    @Autowired
    MsgDao msgDao;

    @LcnTransaction //分布式事务注解
    public void test() throws Exception {
        try{
            if(lockService.tryLock("lcok")){
                //处理业务
                msgService.addMsg();

//                msgDao = null;
//
//
//                msgDao.addMsg(UUID.randomUUID().toString());



                //下订单
                orderApi.test();
                //扣减库存
                warehouseApi.test();
            }
        }finally {
            lockService.unLock("lock");
        }
    }
}
