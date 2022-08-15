package com.lonjoy.framework.rabbitmq.constant;

/**
 * @author luke
 * @date 2022/8/15 21:09
 * @desc 状态码
 **/
public enum ConsumeCode {
    SUCCESS(0, "消息成功消费"),
    FAILED(-1, "消息消费失败");

    private int code;
    private String message;
    private String format;

    private ConsumeCode(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
        this.format = message;
    }

    public ConsumeCode format(Object... msgArgs) {
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
