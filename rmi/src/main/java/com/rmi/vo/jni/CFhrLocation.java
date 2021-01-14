package com.rmi.vo.jni;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class CFhrLocation extends Structure {
	public Pointer acc_begin; // 加速开始点
	public int acc_begin_len;
	public Pointer acc_end; // 加速结束点
	public int acc_end_len;
	public Pointer dec_begin; // 减速开始点
	public int dec_begin_len;
	public Pointer dec_end; // 减速结束点
	public int dec_end_len;

	public static class ByReference extends FhrParameter implements Structure.ByReference {
	}

	public static class ByValue extends FhrParameter implements Structure.ByValue {
	}

	@Override
	protected List getFieldOrder() {
		return Arrays.asList("acc_begin", "acc_begin_len", "acc_end", "acc_end_len", "dec_begin", "dec_begin_len",
				"dec_end", "dec_end_len");
	}
	
	@Override
	public String toString() {
		return "CFhrLocation [acc_begin=" + acc_begin + ", acc_begin_len=" + acc_begin_len + ", acc_end=" + acc_end
				+ ", acc_end_len=" + acc_end_len + ", dec_begin=" + dec_begin + ", dec_begin_len=" + dec_begin_len
				+ ", dec_end=" + dec_end + ", dec_end_len=" + dec_end_len + "]";
	}

}