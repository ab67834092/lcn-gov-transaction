package com.cjb.controller;

import com.cjb.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
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

    @GetMapping(value = "test/{num}")
    public void test(@PathVariable int num){
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(num);
        for(int i=1;i<=num;i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        begin.await();
                        portalService.test();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        end.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        try {
            System.out.println("开始执行...");
            begin.countDown();
            end.await();
            System.out.println("所有的都执行完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }

}
