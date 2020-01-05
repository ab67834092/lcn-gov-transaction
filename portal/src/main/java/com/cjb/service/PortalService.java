package com.cjb.service;

import com.cjb.lock.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalService {

    @Autowired
    LockService lockService;

    @Autowired
    BusinessService businessService;


    public void test() throws InterruptedException {
        try{
            if(lockService.tryLock("lcok")){
                //统一处理业务入口
                businessService.test();
            }
        }finally {
            lockService.unLock("lock");
        }
    }
}
