package com.rmi.vo.jni;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class CFhrBaseline extends Structure {

	public Pointer fhrBas;

	public int len;

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("fhrBas", "len");
	}

	@Override
	public String toString() {
		return "CFhrBaseline [fhrBas=" + fhrBas + ", len=" + len + "]";
	}

}
