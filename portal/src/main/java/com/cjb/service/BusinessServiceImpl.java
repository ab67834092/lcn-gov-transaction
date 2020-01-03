package com.cjb.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.cjb.api.OrderApi;
import com.cjb.api.WarehouseApi;
import com.cjb.bean.MqMsg;
import com.cjb.dao.BusinessDao;
import com.cjb.dao.MqMsgDao;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BusinessServiceImpl implements BusinessService {

    public static final String QUEUE_NAME = "queue-test";

    @Reference(version = "1.0.0")
    public OrderApi orderApi;

    @Reference(version = "1.0.0")
    public WarehouseApi warehouseApi;

    @Autowired
    BusinessDao businessDao;

    @Autowired
    MqMsgDao mqMsgDao;

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Override
    @LcnTransaction //分布式事务注解
    @Transactional
    public void test(){
        /**以下为本系统RPC调用**/
        //本地业务
        businessDao.insert(UUID.randomUUID().toString());
        //下订单
        orderApi.test();
        //扣减库存
        warehouseApi.test();
        /**以下为通过mq调用其他系统**/

        //可以做成消息服务（可以统一管理），也可以做成本地消息（本地事务好控制，以这个为例子）
        //本地记录调用消息日志 1 发送中  2 发送完成
        //定时任务对发送中消息进行再次发送
        MqMsg mqMsg = new MqMsg();
        mqMsg.setMessageId(UUID.randomUUID().toString());
        mqMsg.setMessageBody("123");
        mqMsg.setMessageSendNum(1);
        mqMsg.setMessageStatus(1);
        mqMsg.setQueueName(QUEUE_NAME);
        mqMsgDao.insert(mqMsg);
        //调用mq 发送消息
        this.rabbitTemplate.convertAndSend(QUEUE_NAME, JSON.toJSONString(mqMsg));
    }

    //mq消息发送确认机制
    public void setUp(){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String s) {
                if(!ack){
                    return;
                }
                System.out.println(JSON.toJSONString(correlationData));
                System.out.println(JSON.toJSONString(s));
            }
        });
    }
}
