package com.rmi.vo.jni;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class FhrParameter extends Structure {
	public int num_fetal_move; // 胎动次数
	public int bpm_var_dist1; // 一般变异振幅，大于该值认为发生一次一般变异
	public int bpm_var_dist2; // 最小变异振幅，大于该值认为发生一次最小变异
	public int acc_length; // 胎心率相对基线增加值为acc_height时持续的时间 ，用来判断是否发生加速
	public int acc_height; // 胎心率相对率基线的增加值，用来判断是否发生
	public int dec_length; // 胎心率相对基线减少值为dec_height时持续的时间 ，用来判断是否发生减速
	public int dec_height; // 胎心率相对率基线的减少值，用来判断是否发生
	public static class ByReference extends FhrParameter implements Structure.ByReference {
	}

	public static class ByValue extends FhrParameter implements Structure.ByValue {
	}

	@Override
	protected List getFieldOrder() {
		return Arrays.asList("num_fetal_move", "bpm_var_dist1", "bpm_var_dist2", "acc_length", "acc_height",
				"dec_length", "dec_height");
	}

	@Override
	public String toString() {
		return "FhrParameter [num_fetal_move=" + num_fetal_move + ", bpm_var_dist1=" + bpm_var_dist1
				+ ", bpm_var_dist2=" + bpm_var_dist2 + ", acc_length=" + acc_length + ", acc_height=" + acc_height
				+ ", dec_length=" + dec_length + ", dec_height=" + dec_height + "]";
	}
	
}