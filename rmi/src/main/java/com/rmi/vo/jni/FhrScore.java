package com.rmi.vo.jni;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class FhrScore extends Structure {
	public int total_score; // 总评分
	public int basal_score; // 胎心基线评分
	public int bpm_v_score; // 变异振幅评分
	public int freq_v_score; // 变异频率评分
	public int num_acc_score; // 胎心率加速评分
	public int num_dec_score; // 胎心率减速评分
	public int num_fetal_move_score; // 胎动次数评分

	public int basal; // 胎心基线
	public double bpm_var; // 振幅变异
	public double freq_var; // 周期变异
	public int num_acc; // 加速次
	public int num_dec; // 减速次
	public int type_dec; // 减速类型
	public int severity; //type_dec的最大值
	public int edLen; // ED 次数
	public int vdLen; // VD 次数
	public int num_pd; // PD次数

	public static class ByReference extends FhrScore implements Structure.ByReference {
	}

	public static class ByValue extends FhrScore implements Structure.ByValue {
	}

	@Override
	protected List getFieldOrder() {
		return Arrays.asList("total_score", "basal_score", "bpm_v_score", "freq_v_score", "num_acc_score",
				"num_dec_score", "num_fetal_move_score", "basal", "bpm_var", "freq_var", "num_acc", "num_dec",
				"type_dec", "severity", "edLen", "vdLen", "num_pd");
	}

	@Override
	public String toString() {
		return "FhrScore [total_score=" + total_score + ", basal_score=" + basal_score + ", bpm_v_score=" + bpm_v_score
				+ ", freq_v_score=" + freq_v_score + ", num_acc_score=" + num_acc_score + ", num_dec_score="
				+ num_dec_score + ", num_fetal_move_score=" + num_fetal_move_score + ", basal=" + basal + ", bpm_var="
				+ bpm_var + ", freq_var=" + freq_var + ", num_acc=" + num_acc + ", num_dec=" + num_dec + ", type_dec="
				+ type_dec + ", severity=" + severity + ", edLen=" + edLen + ", vdLen=" + vdLen + ", num_pd=" + num_pd
				+ "]";
	}

}