package com.rmi.vo.jni;

import java.io.Serializable;

public class VOCFhrLocation  implements Serializable {

	private int x; // x轴 秒
	private int y; // y轴
	private boolean flag; // true为加速,false为减速

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}