package com.rmi.vo.jni;

import java.io.Serializable;
import java.util.List;

public class VOFhrInfo implements Serializable {
	// 加速减速的开始和结束坐标
	private List<VOCFhrLocation> vocFhrLocation;
	// 胎心率评分
	private VOFhrScore voFhrScore;
	// 基线数组
	private int[] baselines;

	public List<VOCFhrLocation> getVocFhrLocation() {
		return vocFhrLocation;
	}

	public void setVocFhrLocation(List<VOCFhrLocation> vocFhrLocation) {
		this.vocFhrLocation = vocFhrLocation;
	}

	public VOFhrScore getVoFhrScore() {
		return voFhrScore;
	}

	public void setVoFhrScore(VOFhrScore voFhrScore) {
		this.voFhrScore = voFhrScore;
	}

	public int[] getBaselines() {
		return baselines;
	}

	public void setBaselines(int[] baselines) {
		this.baselines = baselines;
	}

}
