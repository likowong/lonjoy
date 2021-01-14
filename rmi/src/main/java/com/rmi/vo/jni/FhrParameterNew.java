package com.rmi.vo.jni;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class FhrParameterNew extends Structure {
	//public int num_fetal_move; // 胎动次数
	public int bpm_var_dist1; // 一般变异振幅，大于该值认为发生一次一般变异
	public int bpm_var_dist2; // 最小变异振幅，大于该值认为发生一次最小变异
	public int acc_length; // 胎心率相对基线增加值为acc_height时持续的时间 ，用来判断是否发生加速
	public int acc_height; // 胎心率相对率基线的增加值，用来判断是否发生
	//以下参数  5.8  新增算法 新增
	public int acc_interval; // 胎心率减速间隔
	
	public int dec_length; // 胎心率相对基线减少值为dec_height时持续的时间 ，用来判断是否发生减速
	public int dec_height; // 胎心率相对率基线的减少值，用来判断是否发生
	//以下参数  5.8  新增算法 新增
	public int dec_interval; // 胎心率减速间隔
	public int findPeaksU_ph;	///6     最小峰高度门限
	public int findPeaksU_pp;	///6     峰最小突起幅度门限
	public int findPeaksU_pd;	///24    最小峰间距门限
	public int findPeaksU_pw;	///15    最小峰宽度
	public int findPeaks_ph;	///10    最小峰高度门限
	

	public int findPeaks_pp;	///10    峰最小突起幅度门限
	public int findPeaks_pd;	///60    最小峰间距门限
	public int fm_mode; // 胎动类型   0 自动，1 手动 ，2 自动+手动
	public int fm_period; // 胎动计数时长s

	public static class ByReference extends FhrParameterNew implements Structure.ByReference {
	}

	public static class ByValue extends FhrParameterNew implements Structure.ByValue {
	}

	/*@Override
	protected List getFieldOrder() {
		return Arrays.asList("num_fetal_move", "bpm_var_dist1", "bpm_var_dist2", "acc_length", "acc_height",
				"dec_length", "dec_height");
	}

	@Override
	public String toString() {
		return "FhrParameter [num_fetal_move=" + num_fetal_move + ", bpm_var_dist1=" + bpm_var_dist1
				+ ", bpm_var_dist2=" + bpm_var_dist2 + ", acc_length=" + acc_length + ", acc_height=" + acc_height
				+ ", dec_length=" + dec_length + ", dec_height=" + dec_height + "]";
	}*/
	
	@Override
	public String toString() {
		return "FhrParameter [bpm_var_dist1=" + bpm_var_dist1 + ", bpm_var_dist2=" + bpm_var_dist2 + ", acc_length="
				+ acc_length + ", acc_height=" + acc_height + ", acc_interval=" + acc_interval + ", dec_length="
				+ dec_length + ", dec_height=" + dec_height + ", dec_interval=" + dec_interval + ", findPeaksU_ph="
				+ findPeaksU_ph + ", findPeaksU_pp=" + findPeaksU_pp + ", findPeaksU_pd=" + findPeaksU_pd
				+ ", findPeaksU_pw=" + findPeaksU_pw + ", findPeaks_ph=" + findPeaks_ph + ", findPeaks_pp="
				+ findPeaks_pp + ", findPeaks_pd=" + findPeaks_pd + ", fm_mode=" + fm_mode + ", fm_period=" + fm_period
				+ "]";
	}

	@Override
	protected List getFieldOrder() {
		return Arrays.asList("bpm_var_dist1", "bpm_var_dist2", "acc_length", "acc_height","acc_interval",
				"dec_length", "dec_height","dec_interval","findPeaksU_ph","findPeaksU_pp",
				"findPeaksU_pd","findPeaksU_pw","findPeaks_ph","findPeaks_pp","findPeaks_pd","fm_mode","fm_period");
	}

 
}