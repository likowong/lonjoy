package com.rmi.utils;


import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rent
 * @date 2015-08-13
 * @desc json操作工具类
 */
public class JsonUtils {
	
	private static final Logger logger = Logger.getLogger(JsonUtils.class);
	

	

	
	public static <T> List<T> convertToList(List<String> dataList, Class<T> t){
		try {
			List<T> list = new ArrayList<T>();
			if(dataList != null && !dataList.isEmpty()){
				for(String str : dataList){
					list.add(JSON.parseObject(str, t));
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取服务器json文件内容
	 * @param myurl 传入的URL 仅json
	 * @return json字符串
	 * @throws Exception
	 */
	public static String getJsonFileStr(String myurl) {
		BufferedReader br = null;
		try {
			URL url = new URL(myurl);
			br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String s = "";
			StringBuilder sb = new StringBuilder();
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return String.valueOf("");
		} finally {
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Integer getFetalHeartTestTime(String fileUrl){
		String result = getJsonFileStr(fileUrl);
		/** 小于3是判断至少文件中除[]以外至少存在一个点 **/
		if(StringUtils.isEmpty(result) || result.length() < 3 || result.equals("null")){
			return 0;
		}
		String[] str = result.split(",");
		Integer time = (int)Math.floor(str.length / 2);
		return time;
	}
	

	public static void main(String args[]){
		Integer testTime = JsonUtils.getFetalHeartTestTime("http://192.168.2.67:8888/group1/M00/04/BE/wKgCQ1iqXACAH6CzAAAVjFw8mOk01.json");
		System.out.println(testTime);
	}
}
