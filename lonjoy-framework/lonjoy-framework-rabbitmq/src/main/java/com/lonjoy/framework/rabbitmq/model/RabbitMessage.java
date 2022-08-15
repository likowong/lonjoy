package com.lonjoy.framework.rabbitmq.model;


/**
 * @author luke
 * @date 2022/8/15 21:08
 * @desc mq消息体
 **/
public class RabbitMessage extends Message{
    private static final long serialVersionUID = 20180922164612L;
    private Destination destination;
    private long deliveryTag;
    private boolean autoDeclare = false;
    private int priority = 50;

    public RabbitMessage() {
    }

    public RabbitMessage(String messageBody) {
        super(messageBody);
    }

    public boolean isAutoDeclare() {
        return this.autoDeclare;
    }

    public void setAutoDeclare(boolean autoDeclare) {
        this.autoDeclare = autoDeclare;
    }

    public Destination getDestination() {
        return this.destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public long getDeliveryTag() {
        return this.deliveryTag;
    }

    public void setDeliveryTag(long deliveryTag) {
        this.deliveryTag = deliveryTag;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
