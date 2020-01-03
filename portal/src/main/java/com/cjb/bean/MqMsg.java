package com.cjb.bean;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by cjb on 2018/12/21.
 */
public class MqMsg implements Serializable {

    private String id;//主键

    private String messageId;//消息ID

    private String messageBody;//消息内容

    private Integer messageSendNum;//消息重发次数

    private Integer messageStatus;//1 发送中 2 发送完成

    private String queueName;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Integer getMessageSendNum() {
        return messageSendNum;
    }

    public void setMessageSendNum(Integer messageSendNum) {
        this.messageSendNum = messageSendNum;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }
}
