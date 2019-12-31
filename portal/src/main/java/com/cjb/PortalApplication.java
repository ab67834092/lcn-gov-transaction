package com.cjb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.sun.corba.se.spi.orb.DataCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableDubbo
@EnableDistributedTransaction
public class PortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }

}
