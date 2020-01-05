package com.cjb.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

/**
 * rabbitMq 配置类
 * @author milicool
 * Created on 2018/9/14
 */
@Configuration
public class RabbitConfig {


    /** ======================== 定制一些处理策略 =============================*/

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    /**
     * 定制化amqp模版
     *
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        Logger log = LoggerFactory.getLogger(RabbitTemplate.class);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause) {
                if(!ack){
                    log.debug("消息发送到exchange失败,原因: {}", cause);
                    return;
                }
                System.out.println(JSON.toJSONString(correlationData));
                System.out.println(JSON.toJSONString(cause));
            }
        });
        return rabbitTemplate;
    }
}