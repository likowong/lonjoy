package com.rmi.vo.jni;

import java.io.Serializable;

public class VOFhrParameter  implements Serializable {
	private Integer numFetalMove; // 胎动次数
	private Integer bpmVarDist1; // 一般变异振幅，大于该值认为发生一次一般变异
	private Integer bpmVarDist2; // 最小变异振幅，大于该值认为发生一次最小变异
	private Integer accLength; // 胎心率相对基线增加值为acc_height时持续的时间 ，用来判断是否发生加速
	private Integer accHeight; // 胎心率相对率基线的增加值，用来判断是否发生
	private Integer decLength; // 胎心率相对基线减少值为dec_height时持续的时间 ，用来判断是否发生减速
	private Integer decHeight; // 胎心率相对率基线的减少值，用来判断是否发生

	public Integer getNumFetalMove() {
		return numFetalMove;
	}

	public void setNumFetalMove(Integer numFetalMove) {
		this.numFetalMove = numFetalMove;
	}

	public Integer getBpmVarDist1() {
		return bpmVarDist1;
	}

	public void setBpmVarDist1(Integer bpmVarDist1) {
		this.bpmVarDist1 = bpmVarDist1;
	}

	public Integer getBpmVarDist2() {
		return bpmVarDist2;
	}

	public void setBpmVarDist2(Integer bpmVarDist2) {
		this.bpmVarDist2 = bpmVarDist2;
	}

	public Integer getAccLength() {
		return accLength;
	}

	public void setAccLength(Integer accLength) {
		this.accLength = accLength;
	}

	public Integer getAccHeight() {
		return accHeight;
	}

	public void setAccHeight(Integer accHeight) {
		this.accHeight = accHeight;
	}

	public Integer getDecLength() {
		return decLength;
	}

	public void setDecLength(Integer decLength) {
		this.decLength = decLength;
	}

	public Integer getDecHeight() {
		return decHeight;
	}

	public void setDecHeight(Integer decHeight) {
		this.decHeight = decHeight;
	}

}