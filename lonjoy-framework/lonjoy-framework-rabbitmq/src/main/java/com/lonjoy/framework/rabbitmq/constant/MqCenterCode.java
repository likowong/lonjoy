package com.lonjoy.framework.rabbitmq.constant;

/**
 * @author luke
 * @date 2022/8/15 21:24
 * @desc
 **/

public enum MqCenterCode{
    SUCCESS(1, "消费成功"),
    ARG_ERROR(10500, "参数错误，%s"),
    SEND_FAILED(10501, "消息发送失败"),
    RECEIVE_FAILED(10502, "消息接收失败"),
    ACK_FAILED(10503, "确认消息消费失败"),
    CANCEL_ACK_FAILED(10504, "取消确认消息消费失败"),
    REGISTER_FAILED(10505, "注册监听服务失败"),
    REGISTER_CODE_DUP_FAILED(10506, "注册监听服务失败, %s冲突，已经被注册了"),
    MESSAGE_CONSUME_FAILED(10507, "消息消费失败"),
    MESSAGE_CONSUMER_NOT_FOUND(10508, "消费者实例未找到"),
    CONSUME_PROCESS_FAILED(10509, "消费方未启动 或 消息消费超时");

    private int code;
    private String message;
    private String format;

    private MqCenterCode(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
        this.format = message;
    }

    public MqCenterCode format(Object... msgArgs) {
        this.message = String.format(this.format, msgArgs);
        return this;
    }

    public String toString() {
        return Integer.toString(this.getCode());
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
