package com.lonjoy.framework.rabbitmq.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author luke
 * @date 2022/8/15 21:20
 * @desc
 **/

public class Message implements Serializable {
    protected long id;
    protected String messageBody;
    protected String from;
    protected Timestamp creationDate;
    protected Timestamp updationDate;
    protected int failedCount;

    public Message() {
    }

    public Message(String messageBody) {
        this.messageBody = messageBody;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Timestamp getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getUpdationDate() {
        return this.updationDate;
    }

    public void setUpdationDate(Timestamp updationDate) {
        this.updationDate = updationDate;
    }

    public int getFailedCount() {
        return this.failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }
}
