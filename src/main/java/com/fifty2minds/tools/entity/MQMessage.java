package com.fifty2minds.tools.entity;

public class MQMessage {
    private String queue;
    private String message;

    public String getMqQueue() {
        return queue;
    }

    public void setMqQueue(String mqQueue) {
        this.queue = mqQueue;
    }

    public String getMqMessage() {
        return message;
    }

    public void setMqMessage(String mqMessage) {
        this.message = mqMessage;
    }
}
