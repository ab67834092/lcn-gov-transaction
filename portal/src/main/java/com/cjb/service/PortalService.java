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

    @Autowired
    LockService lockService;

    @Autowired
    MsgService msgService;

    @Autowired
    MsgDao msgDao;


    public void test() throws InterruptedException {
        try{
            if(lockService.tryLock("lcok")){
                //统一处理业务入口
                msgService.addMsg();
            }
        }finally {
            lockService.unLock("lock");
        }
    }
}
