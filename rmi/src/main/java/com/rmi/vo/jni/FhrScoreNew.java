package com.rmi.vo.jni;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class FhrScoreNew extends Structure {
	public int total_score; // 总评分
	public int basal_score; // 胎心基线评分
	public int bpm_v_score; // 变异振幅评分
	public int freq_v_score; // 变异频率评分
	public int num_acc_score; // 胎心率加速评分
	public int num_dec_score; // 胎心率减速评分
	public int num_fm_score; // 胎动次数评分
	//5.8 新增
	public int acog_list0;  // 【】胎心基线正常(110-160bpm)
	public int acog_list1;  // 【】胎心过速(>160bpm)
	public int acog_list2;  // 【】胎心过缓(<110bpm)不伴基线变异缺失
	public int acog_list3;  // 【】胎心过缓(<110bpm)伴基线变异缺失
	public int acog_list4;  // 【】中度(6-25bpm)基线变异
	public int acog_list5;  // 【】变异缺失(0bpm)不伴反复出现的晚期减速
	public int acog_list6;  // 【】微小变异(<5bpm)
	public int acog_list7;  // 【】显著变异(>25bpm)
	public int acog_list8;  // 【】变异缺失(0bpm)伴胎心过缓或反复出现变异减速或晚期减速
	public int acog_list9;  // 【】有胎心加速
	public int acog_list10;  // 【】无胎心加速
	public int acog_list11;  // 【】刺激胎儿后仍缺失
	public int acog_list12;  // 【】无胎心加速
	public int acog_list13;  // 【】有早期减速
	public int acog_list14;  // 【】无早期减速
	public int acog_list15;  // 【】无早期减速
	public int acog_list16;  // 【】无早期减速
	public int acog_list17;  // 【】无变异减速
	public int acog_list18;  // 【】反复出现伴微小变异或中度变异
	public int acog_list19;  // 【】延长减速
	public int acog_list20;  // 【】非特异性变异减速
	public int acog_list21;  // 【】反复出现伴基线变异缺失
	public int acog_list22;  // 【】无晚期减速
	public int acog_list23;  // 【】反复出现伴基线中度变异
	public int acog_list24;  // 【】反复出现伴基线变异缺失
	public int acog_list25;  // 【】无正弦曲线
	public int acog_list26;  // 【】无正弦曲线
	public int acog_list27;  // 【】有正弦曲线
	//------------------5.8之前
	public int basal; // 胎心基线
	public double bpm_var; // 振幅变异
	public double freq_var; // 周期变异
	public int num_acc; // 加速次
	public int num_dec; // 减速次
	public int type_dec; // 减速类型
	public int severity; //type_dec的最大值
	//5.8 新增 修改
	public int num_ed; //type_dec的最大值
	public int num_vd; //type_dec的最大值
	
	public int num_pd; // PD次数
	
	public int num_ld;  // 晚期减速次数，2018-07-05
	public int num_od;  // 其他减速次数，2018-07-06
	public int exist_ns_vd; // 是否存在特异性变异减速次数，2018-07-06
	public int exist_sp; //是否为正弦曲线，2018-07-06
	public int num_fm;  // 胎动次数，2018-07-07
	public int dec_loc; // 最后一次减速发生时间
	

	public static class ByReference extends FhrScoreNew implements Structure.ByReference {
	}

	public static class ByValue extends FhrScoreNew implements Structure.ByValue {
	}

	@Override
	public String toString() {
		return "FhrScore [total_score=" + total_score + ", basal_score=" + basal_score + ", bpm_v_score=" + bpm_v_score
				+ ", freq_v_score=" + freq_v_score + ", num_acc_score=" + num_acc_score + ", num_dec_score="
				+ num_dec_score + ", num_fm_score=" + num_fm_score + ", acog_list0=" + acog_list0 + ", acog_list1="
				+ acog_list1 + ", acog_list2=" + acog_list2 + ", acog_list3=" + acog_list3 + ", acog_list4="
				+ acog_list4 + ", acog_list5=" + acog_list5 + ", acog_list6=" + acog_list6 + ", acog_list7="
				+ acog_list7 + ", acog_list8=" + acog_list8 + ", acog_list9=" + acog_list9 + ", acog_list10="
				+ acog_list10 + ", acog_list11=" + acog_list11 + ", acog_list12=" + acog_list12 + ", acog_list13="
				+ acog_list13 + ", acog_list14=" + acog_list14 + ", acog_list15=" + acog_list15 + ", acog_list16="
				+ acog_list16 + ", acog_list17=" + acog_list17 + ", acog_list18=" + acog_list18 + ", acog_list19="
				+ acog_list19 + ", acog_list20=" + acog_list20 + ", acog_list21=" + acog_list21 + ", acog_list22="
				+ acog_list22 + ", acog_list23=" + acog_list23 + ", acog_list24=" + acog_list24 + ", acog_list25="
				+ acog_list25 + ", acog_list26=" + acog_list26 + ", acog_list27=" + acog_list27 + ", basal=" + basal
				+ ", bpm_var=" + bpm_var + ", freq_var=" + freq_var + ", num_acc=" + num_acc + ", num_dec=" + num_dec
				+ ", type_dec=" + type_dec + ", severity=" + severity + ", num_ed=" + num_ed + ", num_vd=" + num_vd
				+ ", num_pd=" + num_pd + ", num_ld=" + num_ld + ", num_od=" + num_od + ", exist_ns_vd=" + exist_ns_vd
				+ ", exist_sp=" + exist_sp + ", num_fm=" + num_fm + ", dec_loc=" + dec_loc + "]";
	}
	@Override
	protected List getFieldOrder() {
		return Arrays.asList("total_score", "basal_score", "bpm_v_score", "freq_v_score", "num_acc_score",
				"num_dec_score", "num_fm_score", "acog_list0", "acog_list1", "acog_list2", "acog_list3", "acog_list4",
				"acog_list5", "acog_list6", "acog_list7", "acog_list8", "acog_list9","acog_list10", "acog_list11", "acog_list12", "acog_list13", "acog_list14",
				"acog_list15", "acog_list16", "acog_list17", "acog_list18", "acog_list19", "acog_list20", "acog_list21",
				"acog_list22", "acog_list23", "acog_list24", "acog_list25", "acog_list26","acog_list27", "basal", "bpm_var", "freq_var", "num_acc",
				"num_dec", "type_dec", "severity", "num_ed", "num_vd", "num_pd", "num_ld",
				"num_od", "exist_ns_vd", "exist_sp", "num_fm", "dec_loc");
	}
	
	/*@Override
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
	}*/
	
}