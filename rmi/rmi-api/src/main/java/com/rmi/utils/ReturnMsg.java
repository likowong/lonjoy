package com.rmi.utils;



import java.io.Serializable;

public class ReturnMsg<T> implements Serializable {
	private static final long serialVersionUID = 6589008794478961923L;
	public static final int SUCCESS = 1;
	public static final int DATA_NULL = 10;
	public static final int FAIL = 0;
	private int msg;
	private String msgbox;
	private T data;

	public ReturnMsg() {
	}

	public ReturnMsg(int msg, String msgbox, T data) {
		this.msg = msg;
		this.msgbox = msgbox;
		this.data = data;
	}

	public ReturnMsg(int msg, String msgbox) {
		this.msg = msg;
		this.msgbox = msgbox;
	}

	public ReturnMsg(int msg, T data) {
		this.msg = msg;
		this.data = data;
	}

	public int getMsg() {
		return this.msg;
	}

	public void setMsg(int msg) {
		this.msg = msg;
	}

	public String getMsgbox() {
		return this.msgbox;
	}

	public void setMsgbox(String msgbox) {
		this.msgbox = msgbox;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
