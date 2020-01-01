package com.cjb.controller;

import com.cjb.lock.LockService;
import com.cjb.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cjb on 2018/12/25.
 */
@Controller
@RequestMapping("/portal")
public class TestController {

    @Autowired
    PortalService portalService;

    @Autowired
    LockService lockService;

    @GetMapping(value = "test/{num}")
    public void test(@PathVariable int num) throws InterruptedException {
        List count = new ArrayList();
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(num);
        for(int i=1;i<=num;i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
//                        begin.await();
                        if(lockService.tryLock("lcok")){
                            portalService.test();
//                            if(CollectionUtils.isEmpty(count)){
//                                count.add(100);
//                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        lockService.unLock("lcok");
//                        end.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        System.out.println("count:=============="+count.size());
        try {
//            System.out.println("开始执行...");
//            begin.countDown();
//            end.await();
//            System.out.println("所有的都执行完了");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }

}
