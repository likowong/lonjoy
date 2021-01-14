package com.rmi.vo.jni;

import java.io.Serializable;

public class VOFhrScoreNew  implements Serializable {
	private int totalScore; // 总评分
	private int basalScore; // 胎心基线评分
	private int bpmVScore; // 变异振幅评分
	private int freqVScore; // 变异频率评分
	private int numAccScore; // 胎心率加速评分
	private int numDecScore; // 胎心率减速评分
	private int numFmScore; // 胎动次数评分
	//5.8 新增
	private int acogList0;  // 【】胎心基线正常(110-160bpm)
	private int acogList1;  // 【】胎心过速(>160bpm)
	private int acogList2;  // 【】胎心过缓(<110bpm)不伴基线变异缺失
	private int acogList3;  // 【】胎心过缓(<110bpm)伴基线变异缺失
	private int acogList4;  // 【】中度(6-25bpm)基线变异
	private int acogList5;  // 【】变异缺失(0bpm)不伴反复出现的晚期减速
	private int acogList6;  // 【】微小变异(<5bpm)
	private int acogList7;  // 【】显著变异(>25bpm)
	private int acogList8;  // 【】变异缺失(0bpm)伴胎心过缓或反复出现变异减速或晚期减速
	private int acogList9;  // 【】有胎心加速
	private int acogList10;  // 【】无胎心加速
	private int acogList11;  // 【】刺激胎儿后仍缺失
	private int acogList12;  // 【】无胎心加速
	private int acogList13;  // 【】有早期减速
	private int acogList14;  // 【】无早期减速
	private int acogList15;  // 【】无早期减速
	private int acogList16;  // 【】无早期减速
	private int acogList17;  // 【】无变异减速
	private int acogList18;  // 【】反复出现伴微小变异或中度变异
	private int acogList19;  // 【】延长减速
	private int acogList20;  // 【】非特异性变异减速
	private int acogList21;  // 【】反复出现伴基线变异缺失
	private int acogList22;  // 【】无晚期减速
	private int acogList23;  // 【】反复出现伴基线中度变异
	private int acogList24;  // 【】反复出现伴基线变异缺失
	private int acogList25;  // 【】无正弦曲线
	private int acogList26;  // 【】无正弦曲线
	private int acogList27;  // 【】有正弦曲线
	//------------------5.8之前
	private int basal; // 胎心基线
	private double bpmVar; // 振幅变异
	private double freqVar; // 周期变异
	private int numAcc; // 加速次
	private int numDec; // 减速次
	private int typeDec; // 减速类型
	private int severity; //type_dec的最大值
	//5.8 新增 修改
	private int numEd; //type_dec的最大值
	private int numVd; //type_dec的最大值
	
	private int numPd; // PD次数
	
	public int numLd;  // 晚期减速次数，2018-07-05
	public int numOd;  // 其他减速次数，2018-07-06
	public int existNsVd; // 是否存在特异性变异减速次数，2018-07-06
	public int existSp; //是否为正弦曲线，2018-07-06
	public int numFm;  // 胎动次数，2018-07-07
	public int decLoc; // 最后一次减速发生时间
	
	//是否有宫缩（0=无；1=有）
	private int contractionsFlag;

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getBasalScore() {
		return basalScore;
	}

	public void setBasalScore(int basalScore) {
		this.basalScore = basalScore;
	}

	public int getBpmVScore() {
		return bpmVScore;
	}

	public void setBpmVScore(int bpmVScore) {
		this.bpmVScore = bpmVScore;
	}

	public int getFreqVScore() {
		return freqVScore;
	}

	public void setFreqVScore(int freqVScore) {
		this.freqVScore = freqVScore;
	}

	public int getNumAccScore() {
		return numAccScore;
	}

	public void setNumAccScore(int numAccScore) {
		this.numAccScore = numAccScore;
	}

	public int getNumDecScore() {
		return numDecScore;
	}

	public void setNumDecScore(int numDecScore) {
		this.numDecScore = numDecScore;
	}

	public int getBasal() {
		return basal;
	}

	public void setBasal(int basal) {
		this.basal = basal;
	}

	public double getBpmVar() {
		return bpmVar;
	}

	public void setBpmVar(double bpmVar) {
		this.bpmVar = bpmVar;
	}

	public double getFreqVar() {
		return freqVar;
	}

	public void setFreqVar(int freqVar) {
		this.freqVar = freqVar;
	}

	public int getNumAcc() {
		return numAcc;
	}

	public void setNumAcc(int numAcc) {
		this.numAcc = numAcc;
	}

	public int getNumDec() {
		return numDec;
	}

	public void setNumDec(int numDec) {
		this.numDec = numDec;
	}

	public int getNumFmScore() {
		return numFmScore;
	}

	public void setNumFmScore(int numFmScore) {
		this.numFmScore = numFmScore;
	}

	public int getAcogList0() {
		return acogList0;
	}

	public void setAcogList0(int acogList0) {
		this.acogList0 = acogList0;
	}

	public int getAcogList1() {
		return acogList1;
	}

	public void setAcogList1(int acogList1) {
		this.acogList1 = acogList1;
	}

	public int getAcogList2() {
		return acogList2;
	}

	public void setAcogList2(int acogList2) {
		this.acogList2 = acogList2;
	}

	public int getAcogList3() {
		return acogList3;
	}

	public void setAcogList3(int acogList3) {
		this.acogList3 = acogList3;
	}

	public int getAcogList4() {
		return acogList4;
	}

	public void setAcogList4(int acogList4) {
		this.acogList4 = acogList4;
	}

	public int getAcogList5() {
		return acogList5;
	}

	public void setAcogList5(int acogList5) {
		this.acogList5 = acogList5;
	}

	public int getAcogList6() {
		return acogList6;
	}

	public void setAcogList6(int acogList6) {
		this.acogList6 = acogList6;
	}

	public int getAcogList7() {
		return acogList7;
	}

	public void setAcogList7(int acogList7) {
		this.acogList7 = acogList7;
	}

	public int getAcogList8() {
		return acogList8;
	}

	public void setAcogList8(int acogList8) {
		this.acogList8 = acogList8;
	}

	public int getAcogList9() {
		return acogList9;
	}

	public void setAcogList9(int acogList9) {
		this.acogList9 = acogList9;
	}

	public int getAcogList10() {
		return acogList10;
	}

	public void setAcogList10(int acogList10) {
		this.acogList10 = acogList10;
	}

	public int getAcogList11() {
		return acogList11;
	}

	public void setAcogList11(int acogList11) {
		this.acogList11 = acogList11;
	}

	public int getAcogList12() {
		return acogList12;
	}

	public void setAcogList12(int acogList12) {
		this.acogList12 = acogList12;
	}

	public int getAcogList13() {
		return acogList13;
	}

	public void setAcogList13(int acogList13) {
		this.acogList13 = acogList13;
	}

	public int getAcogList14() {
		return acogList14;
	}

	public void setAcogList14(int acogList14) {
		this.acogList14 = acogList14;
	}

	public int getAcogList15() {
		return acogList15;
	}

	public void setAcogList15(int acogList15) {
		this.acogList15 = acogList15;
	}

	public int getAcogList16() {
		return acogList16;
	}

	public void setAcogList16(int acogList16) {
		this.acogList16 = acogList16;
	}

	public int getAcogList17() {
		return acogList17;
	}

	public void setAcogList17(int acogList17) {
		this.acogList17 = acogList17;
	}

	public int getAcogList18() {
		return acogList18;
	}

	public void setAcogList18(int acogList18) {
		this.acogList18 = acogList18;
	}

	public int getAcogList19() {
		return acogList19;
	}

	public void setAcogList19(int acogList19) {
		this.acogList19 = acogList19;
	}

	public int getAcogList20() {
		return acogList20;
	}

	public void setAcogList20(int acogList20) {
		this.acogList20 = acogList20;
	}

	public int getAcogList21() {
		return acogList21;
	}

	public void setAcogList21(int acogList21) {
		this.acogList21 = acogList21;
	}

	public int getAcogList22() {
		return acogList22;
	}

	public void setAcogList22(int acogList22) {
		this.acogList22 = acogList22;
	}

	public int getAcogList23() {
		return acogList23;
	}

	public void setAcogList23(int acogList23) {
		this.acogList23 = acogList23;
	}

	public int getAcogList24() {
		return acogList24;
	}

	public void setAcogList24(int acogList24) {
		this.acogList24 = acogList24;
	}

	public int getAcogList25() {
		return acogList25;
	}

	public void setAcogList25(int acogList25) {
		this.acogList25 = acogList25;
	}

	public int getAcogList26() {
		return acogList26;
	}

	public void setAcogList26(int acogList26) {
		this.acogList26 = acogList26;
	}

	public int getAcogList27() {
		return acogList27;
	}

	public void setAcogList27(int acogList27) {
		this.acogList27 = acogList27;
	}

	public int getTypeDec() {
		return typeDec;
	}

	public void setTypeDec(int typeDec) {
		this.typeDec = typeDec;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public int getNumEd() {
		return numEd;
	}

	public void setNumEd(int numEd) {
		this.numEd = numEd;
	}

	public int getNumVd() {
		return numVd;
	}

	public void setNumVd(int numVd) {
		this.numVd = numVd;
	}

	public int getNumPd() {
		return numPd;
	}

	public void setNumPd(int numPd) {
		this.numPd = numPd;
	}

	public int getNumLd() {
		return numLd;
	}

	public void setNumLd(int numLd) {
		this.numLd = numLd;
	}

	public int getNumOd() {
		return numOd;
	}

	public void setNumOd(int numOd) {
		this.numOd = numOd;
	}

	public int getExistNsVd() {
		return existNsVd;
	}

	public void setExistNsVd(int existNsVd) {
		this.existNsVd = existNsVd;
	}

	public int getExistSp() {
		return existSp;
	}

	public void setExistSp(int existSp) {
		this.existSp = existSp;
	}

	public int getNumFm() {
		return numFm;
	}

	public void setNumFm(int numFm) {
		this.numFm = numFm;
	}

	public int getDecLoc() {
		return decLoc;
	}

	public void setDecLoc(int decLoc) {
		this.decLoc = decLoc;
	}

	public void setFreqVar(double freqVar) {
		this.freqVar = freqVar;
	}

	public int getContractionsFlag() {
		return contractionsFlag;
	}

	public void setContractionsFlag(int contractionsFlag) {
		this.contractionsFlag = contractionsFlag;
	}
	
	

}