package com.lonjoy.framework.rabbitmq.model;

/**
 * @author luke
 * @date 2022/8/15 21:46
 * @desc
 **/

public class SendMessageRequest {
    private String exchange;
    private String routingKey;
    private String queue;
    private Object content;
    private int priority = 50;
    private String host;

    public SendMessageRequest() {
    }

    public String getExchange() {
        return this.exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return this.routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getQueue() {
        return this.queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
