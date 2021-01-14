package com.rmi.vo.jni;

import java.io.Serializable;

public class VOFhrParameterNew  implements Serializable {
	/*private Integer numFetalMove; // 胎动次数
	private Integer bpmVarDist1; // 一般变异振幅，大于该值认为发生一次一般变异
	private InteInteger bpmVarDist2; // 最小变异振幅，大于该值认为发生一次最小变异
	private Integer accLength; // 胎心率相对基线增加值为acc_height时持续的时间 ，用来判断是否发生加速
	private Integer accHeight; // 胎心率相对率基线的增加值，用来判断是否发生
	private Integer decLength; // 胎心率相对基线减少值为dec_height时持续的时间 ，用来判断是否发生减速
	private Integer decHeight; // 胎心率相对率基线的减少值，用来判断是否发生
	 */	
	public Integer bpmVarDist1; // 一般变异振幅，大于该值认为发生一次一般变异
	public Integer bpmVarDist2; // 最小变异振幅，大于该值认为发生一次最小变异
	public Integer accLength; // 胎心率相对基线增加值为acc_height时持续的时间 ，用来判断是否发生加速
	public Integer accHeight; // 胎心率相对率基线的增加值，用来判断是否发生
	//以下参数  5.8  新增算法 新增
	public Integer accInterval; // 胎心率减速间隔
	
	public Integer decLength; // 胎心率相对基线减少值为dec_height时持续的时间 ，用来判断是否发生减速
	public Integer decHeight; // 胎心率相对率基线的减少值，用来判断是否发生
	//以下参数  5.8  新增算法 新增
	public Integer decInterval; // 胎心率减速间隔
	public Integer findPeaksUPh;	///6     最小峰高度门限
	public Integer findPeaksUPp;	///6     峰最小突起幅度门限
	public Integer findPeaksUPd;	///24    最小峰间距门限
	public Integer findPeaksUPw;	///15    最小峰宽度
	public Integer findPeaksPh;	///10    最小峰高度门限
	

	public Integer findPeaksPp;	///10    峰最小突起幅度门限
	public Integer findPeaksPd;	///60    最小峰间距门限
	public Integer fmMode; // 胎动类型   0 自动，1 手动 ，2 自动+手动
	public Integer fmPeriod; // 胎动计数时长s
	
	
	 

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

	public Integer getAccInterval() {
		return accInterval;
	}

	public void setAccInterval(Integer accInterval) {
		this.accInterval = accInterval;
	}

	public Integer getDecInterval() {
		return decInterval;
	}

	public void setDecInterval(Integer decInterval) {
		this.decInterval = decInterval;
	}

	public Integer getFindPeaksUPh() {
		return findPeaksUPh;
	}

	public void setFindPeaksUPh(Integer findPeaksUPh) {
		this.findPeaksUPh = findPeaksUPh;
	}

	public Integer getFindPeaksUPp() {
		return findPeaksUPp;
	}

	public void setFindPeaksUPp(Integer findPeaksUPp) {
		this.findPeaksUPp = findPeaksUPp;
	}

	public Integer getFindPeaksUPd() {
		return findPeaksUPd;
	}

	public void setFindPeaksUPd(Integer findPeaksUPd) {
		this.findPeaksUPd = findPeaksUPd;
	}

	public Integer getFindPeaksUPw() {
		return findPeaksUPw;
	}

	public void setFindPeaksUPw(Integer findPeaksUPw) {
		this.findPeaksUPw = findPeaksUPw;
	}

	public Integer getFindPeaksPh() {
		return findPeaksPh;
	}

	public void setFindPeaksPh(Integer findPeaksPh) {
		this.findPeaksPh = findPeaksPh;
	}

	public Integer getFindPeaksPp() {
		return findPeaksPp;
	}

	public void setFindPeaksPp(Integer findPeaksPp) {
		this.findPeaksPp = findPeaksPp;
	}

	public Integer getFindPeaksPd() {
		return findPeaksPd;
	}

	public void setFindPeaksPd(Integer findPeaksPd) {
		this.findPeaksPd = findPeaksPd;
	}

	public Integer getFmMode() {
		return fmMode;
	}

	public void setFmMode(Integer fmMode) {
		this.fmMode = fmMode;
	}

	public Integer getFmPeriod() {
		return fmPeriod;
	}

	public void setFmPeriod(Integer fmPeriod) {
		this.fmPeriod = fmPeriod;
	}

}