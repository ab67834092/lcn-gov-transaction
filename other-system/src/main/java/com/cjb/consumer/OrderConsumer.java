package com.cjb.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.log4j2.AmqpAppender;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by chenjiabao on 2020/1/5.
 */
@Component
public class OrderConsumer {


    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "topic-order",autoDelete = "false"),
                    exchange = @Exchange(value = "test.topic",type = ExchangeTypes.TOPIC)
            )
    )
    public void messageConsumer(String msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){

        try {
            System.out.println(msg);
            //手动ack
//            channel.basicAck(tag,false);
        } catch (Exception e) {
            e.printStackTrace();
            //异常可以mq重发，次数限制
        }
    }
}
