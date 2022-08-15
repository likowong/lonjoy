package com.lonjoy.framework.rabbitmq.model;

import java.io.Serializable;

/**
 * @author luke
 * @date 2022/8/15 21:54
 * @desc
 **/

public class Destination implements Serializable {
    private String exchange;
    private String routingKey;
    private String queue;

    public Destination() {
    }

    public Destination(String exchange, String routingKey) {
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public Destination(String exchange, String routingKey, String queue) {
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.queue = queue;
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
}
