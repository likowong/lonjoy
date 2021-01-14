package com.rmi.utils;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.rmi.vo.MonitorHeartrateRecord;
import com.rmi.vo.jni.*;
import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liuxl
 * @date 2017年8月24日
 * 2017年8月24日下午6:18:08
 */
public class JNIUtilsNew {
	private static Logger log = Logger.getLogger(JNIUtilsNew.class);

	/**
	 * @author luke
	 * @date 2021/1/7 0007 9:08
	 * @desc
	 **/
	public static interface JniDllNew extends Library {
		//	JniDll Instance = (JniDll) Native.loadLibrary("fhr.so", JniDll.class);
		JniDllNew Instance = (JniDllNew) Native.loadLibrary("fhr_new2.so", JniDllNew.class);
		//JniDllNew Instance = (JniDllNew) Native.loadLibrary("fhr_new.dll", JniDllNew.class);
		// ***************Kreb***************
		// 获取所有胎心率评分
		//FhrScoreNew calFhrKreb(int[] data,int fm[], int len, int type);
		FhrScoreNew JP1_calFhrKrebs(int[] data, int fm[], int len, int type);

		// 设置所有评分参数
		//int setParameter(FhrParameterNew param);
		int JP1_setParameter(FhrParameterNew param);

		// 获取所有评分参数
		//FhrParameterNew getParameter();
		FhrParameterNew JP1_getParameter();

		// 获取加速减速的开始和结束坐标
		//CFhrLocation getKrebLocation();
		CFhrLocation JP1_getKrebsLocation();

		// 获取基线数组
		//CFhrBaseline getKrebBaseline();
		CFhrBaseline JP1_getKrebsBaseline();

		// ***************Fischer2***************
		//FhrScoreNew calFhrFischer2(int data[], int uc[],int fm[],int len, int fs);
		FhrScoreNew JP1_calFhrFischer2(int data[], int uc[], int fm[], int len, int fs);

		//CFhrLocation getFischer2Location();
		CFhrLocation JP1_getFischer2Location();

		//CFhrBaseline getFischer2Baseline();
		CFhrBaseline JP1_getFischer2Baseline();

		// 测试
		void showFhr(int[] data, int len, int type);
	}
	/**
	 * 设置设置所有评分参数
	 * 
	 * @param vo
	 * @return
	 */
	public static ReturnMsg setParameter(VOFhrParameterNew vo) {
		FhrParameterNew fhrParameter = new FhrParameterNew();
		fhrParameter.acc_height = vo.getAccHeight() == null ? Integer.parseInt((ConfigProUtils.get("acc_height")==null)?"15":ConfigProUtils.get("acc_height")) : vo.getAccHeight();
		fhrParameter.acc_length = vo.getAccLength() == null ? Integer.parseInt((ConfigProUtils.get("acc_length")==null)?"15":ConfigProUtils.get("acc_length")) : vo.getAccLength();
		fhrParameter.acc_interval = vo.getAccInterval() == null ? Integer.parseInt((ConfigProUtils.get("acc_interval")==null)?"120":ConfigProUtils.get("acc_interval")) : vo.getAccInterval();

		fhrParameter.bpm_var_dist1 = vo.getBpmVarDist1() == null ? Integer.parseInt((ConfigProUtils.get("bpm_var_dist1")==null)?"5":ConfigProUtils.get("bpm_var_dist1")) : vo.getBpmVarDist1();
		fhrParameter.bpm_var_dist2 = vo.getBpmVarDist2() == null ? Integer.parseInt((ConfigProUtils.get("bpm_var_dist2")==null)?"3":ConfigProUtils.get("bpm_var_dist2")) : vo.getBpmVarDist2();
		fhrParameter.dec_height = vo.getDecHeight() == null ? Integer.parseInt((ConfigProUtils.get("dec_height")==null)?"11":ConfigProUtils.get("dec_height")) : vo.getDecHeight();
		fhrParameter.dec_length = vo.getDecLength() == null ? Integer.parseInt((ConfigProUtils.get("dec_length")==null)?"11":ConfigProUtils.get("dec_length")) : vo.getDecLength();
		//fhrParameter.num_fetal_move = vo.getNumFetalMove() == null ? 5 : vo.getNumFetalMove();
		fhrParameter.dec_interval = vo.getDecInterval() == null ? Integer.parseInt((ConfigProUtils.get("dec_interval")==null)?"60":ConfigProUtils.get("dec_interval")) : vo.getDecInterval();
		fhrParameter.findPeaksU_ph = vo.getFindPeaksUPh() == null ? Integer.parseInt((ConfigProUtils.get("findPeaksU_ph")==null)?"6":ConfigProUtils.get("findPeaksU_ph")) : vo.getFindPeaksUPh();
		fhrParameter.findPeaksU_pp = vo.getFindPeaksUPp()== null ? Integer.parseInt((ConfigProUtils.get("findPeaksU_pp")==null)?"6":ConfigProUtils.get("findPeaksU_pp")) : vo.getFindPeaksUPp();
		fhrParameter.findPeaksU_pd = vo.getFindPeaksUPd() == null ? Integer.parseInt((ConfigProUtils.get("findPeaksU_pd")==null)?"24":ConfigProUtils.get("findPeaksU_pd")) : vo.getFindPeaksUPd();
		fhrParameter.findPeaksU_pw = vo.getFindPeaksUPw() == null ? Integer.parseInt((ConfigProUtils.get("findPeaksU_pw")==null)?"15":ConfigProUtils.get("findPeaksU_pw")) : vo.getFindPeaksUPw();
		fhrParameter.findPeaks_ph = vo.getFindPeaksPh() == null ? Integer.parseInt((ConfigProUtils.get("findPeaks_ph")==null)?"10":ConfigProUtils.get("findPeaks_ph")) : vo.getFindPeaksPh();
		fhrParameter.findPeaks_pp = vo.getFindPeaksPp()== null ? Integer.parseInt((ConfigProUtils.get("findPeaks_pp")==null)?"10":ConfigProUtils.get("findPeaks_pp")) : vo.getFindPeaksPp();
		fhrParameter.findPeaks_pd = vo.getFindPeaksPd() == null ? Integer.parseInt((ConfigProUtils.get("findPeaks_pd")==null)?"60":ConfigProUtils.get("findPeaks_pd")) : vo.getFindPeaksPd();
		fhrParameter.fm_mode = vo.getFmMode() == null ? Integer.parseInt((ConfigProUtils.get("fm_mode")==null)?"2":ConfigProUtils.get("fm_mode")) : vo.getFmMode();
		fhrParameter.fm_period = vo.getFmPeriod() == null ? Integer.parseInt((ConfigProUtils.get("fm_period")==null)?"300":ConfigProUtils.get("fm_period")) : vo.getFmPeriod();
		
		int state = JniDllNew.Instance.JP1_setParameter(fhrParameter);
		ReturnMsg msg = null;

		switch (state) {
		case 0:
			msg = new ReturnMsg<>(ReturnMsg.SUCCESS, "设置评分参数成功!");
			break;
		/*case 1:
			msg = new ReturnMsg<>(ReturnMsg.FAIL, "设置评分参数失败,numFetalMove超出规定范围(0-1000)!", vo.getNumFetalMove());
			break;*/
		case 1:
			msg = new ReturnMsg<>(ReturnMsg.FAIL, "设置评分参数失败,bpmVarDist1超出规定范围(6-10)!", vo.getBpmVarDist1());
			break;
		case 2:
			msg = new ReturnMsg<>(ReturnMsg.FAIL, "设置评分参数失败,bpmVarDist2超出规定范围(3-5)!", vo.getBpmVarDist2());
			break;
		case 3:
			msg = new ReturnMsg<>(ReturnMsg.FAIL, "设置评分参数失败,accLength超出规定范围(12-15)!", vo.getAccLength());
			break;
		case 4:
			msg = new ReturnMsg<>(ReturnMsg.FAIL, "设置评分参数失败,accHeight超出规定范围(12-15)!", vo.getAccHeight());
			break;
		case 5:
			msg = new ReturnMsg<>(ReturnMsg.FAIL, "设置评分参数失败,decLength超出规定范围(5-10)!", vo.getDecLength());
			break;
		case 6:
			msg = new ReturnMsg<>(ReturnMsg.FAIL, "设置评分参数失败,decHeight超出规定范围(12-15)!", vo.getDecHeight());
			break;
		default:
			msg = new ReturnMsg<>(ReturnMsg.SUCCESS, "设置评分参数成功!");
			break;
		}
		return msg;
	}

	/**
	 * 获取所有评分参数
	 * 
	 * @return
	 */
	public static VOFhrParameterNew getParameter() {
		FhrParameterNew parameter = JniDllNew.Instance.JP1_getParameter();
		VOFhrParameterNew vo = new VOFhrParameterNew();
		vo.setAccHeight(parameter.acc_height);
		vo.setAccLength(parameter.acc_length);
		vo.setAccInterval(parameter.acc_interval);
		vo.setBpmVarDist1(parameter.bpm_var_dist1);
		vo.setBpmVarDist2(parameter.bpm_var_dist2);
		vo.setDecHeight(parameter.dec_height);
		vo.setDecLength(parameter.dec_length);
		vo.setDecInterval(parameter.dec_interval);
		vo.setFindPeaksPd(parameter.findPeaks_pd);
		vo.setFindPeaksPh(parameter.findPeaks_ph);
		vo.setFindPeaksPp(parameter.findPeaks_pp);
		vo.setFindPeaksUPd(parameter.findPeaksU_pd);
		vo.setFindPeaksUPh(parameter.findPeaksU_ph);
		vo.setFindPeaksUPp(parameter.findPeaksU_pp);
		vo.setFindPeaksUPw(parameter.findPeaksU_pw);
		vo.setFmMode(parameter.fm_mode);
		vo.setFmPeriod(parameter.fm_period);
		//vo.setNumFetalMove(parameter.num_fetal_move);
		return vo;
	}

	/**
	 * 获取自动评分(改良Fischer)莱康宁版本
	 * 
	 * @param record
	 * @param end
	 * @param start
	 * @return
	 */
	public static ReturnMsg getFischerFhrInfo(MonitorHeartrateRecord record, Integer start, Integer end) {
		synchronized (JNIUtilsNew.class) {
			try {
				// 获取胎心和宫缩数据
				ReturnMsg msg = getRecordAndUsFile(record, start, end);
				if (msg.getMsg() == 0) {
					return msg;
				}
				Map<String, int[]> map = (Map<String, int[]>) msg.getData();
				int[] recordFile = map.get("recordFile");
				int[] usFile = map.get("usFile");
				log.info("recordFile:"+recordFile);
				log.info("usFile:"+recordFile);
				// 计算胎动次数
				//int fetalMoveNum = getFetalMoveNum(record.getFetalMoveValue(), start, end);
				// 计算胎动次数
				int fetalMoveNum = getFetalMoveNum(record.getFetalMoveValue(),record.getAutoFetalMoveValue(), start, end);
				// 获取胎心评分
				ReturnMsg fischerScore = getFischerScore(recordFile, usFile,new int[usFile.length], fetalMoveNum);
				
				log.info("fischerScore.getMsg():"+fischerScore.getMsg());
				if (fischerScore.getMsg() == 0) {
					return fischerScore;
				}
				// 获取加速减速的开始和结束坐标
				ReturnMsg fischerLocation = getFischerLocation(recordFile, usFile);
				if (fischerLocation.getMsg() == 0) {
					return fischerLocation;
				}
				log.info("fischerLocation.getMsg():"+fischerLocation.getMsg());
				// 获取基线数组
				ReturnMsg fischerBaseline = getFischerBaseline();
				if (fischerBaseline.getMsg() == 0) {
					return fischerBaseline;
				}
				log.info("fischerBaseline.getMsg():"+fischerBaseline.getMsg());
				VOFhrInfo info = new VOFhrInfo();
				info.setBaselines((int[]) fischerBaseline.getData());
				info.setVocFhrLocation((List<VOCFhrLocation>) fischerLocation.getData());
				info.setVoFhrScore((VOFhrScore) fischerScore.getData());
				List<VOFhrInfo> list = new ArrayList<>();
				list.add(info);
				return new ReturnMsg(ReturnMsg.SUCCESS, "获取胎心自动评分信息成功!", list);
			} catch (Exception e) {
				log.error("获取胎心自动评分信息失败!", e);
				return new ReturnMsg(ReturnMsg.FAIL, "获取胎心自动评分信息失败!");
			}
		}
	}
	
	
	/**
	 * 获取自动评分(改良Fischer)莱康宁版本(胎监6.6迭代新增)
	 * 
	 * @param record
	 * @param end
	 * @param start
	 * @return
	 */
//	public static ReturnMsg getFischerFhrInfo(MonitorHeartrateRecord record, Integer start, Integer end,MonitorHospital mHospital) {
//		synchronized (JNIUtilsNew.class) {
//			try {
//				// 获取胎心和宫缩数据
//				ReturnMsg msg = getRecordAndUsFile(record, start, end);
//				if (msg.getMsg() == 0) {
//					return msg;
//				}
//				Map<String, int[]> map = (Map<String, int[]>) msg.getData();
//				int[] recordFile = map.get("recordFile");
//				int[] usFile = map.get("usFile");
//				log.info("recordFile:"+recordFile);
//				log.info("usFile:"+recordFile);
//				// 计算胎动次数
//				int fetalMoveNum = getFetalMoveNum(record.getFetalMoveValue(), start, end);
//				// 获取胎心评分
//				ReturnMsg fischerScore = getFischerScore(recordFile, usFile,new int[usFile.length], fetalMoveNum);
//
//				log.info("fischerScore.getMsg():"+fischerScore.getMsg());
//				if (fischerScore.getMsg() == 0) {
//					return fischerScore;
//				}
//				// 获取加速减速的开始和结束坐标
//				ReturnMsg fischerLocation = getFischerLocation(recordFile, usFile);
//				if (fischerLocation.getMsg() == 0) {
//					return fischerLocation;
//				}
//				log.info("fischerLocation.getMsg():"+fischerLocation.getMsg());
//				// 获取基线数组
//				ReturnMsg fischerBaseline = getFischerBaseline();
//				if (fischerBaseline.getMsg() == 0) {
//					return fischerBaseline;
//				}
//
//				log.info("fischerBaseline.getMsg():"+fischerBaseline.getMsg());
//				VOFhrInfo info = new VOFhrInfo();
//				info.setBaselines((int[]) fischerBaseline.getData());
//				info.setVocFhrLocation((List<VOCFhrLocation>) fischerLocation.getData());
//				info.setVoFhrScore((VOFhrScore) fischerScore.getData());
//				List<VOFhrInfo> list = new ArrayList<>();
//				list.add(info);
//				return new ReturnMsg(ReturnMsg.SUCCESS, "获取胎心自动评分信息成功!", list);
//			} catch (Exception e) {
//				log.error("获取胎心自动评分信息失败!", e);
//				return new ReturnMsg(ReturnMsg.FAIL, "获取胎心自动评分信息失败!");
//			}
//		}
//	}
//
	
	/**
	 * 获取胎心和宫缩数据
	 * 
	 * @param record
	 * @param end
	 * @param start
	 * @return
	 */
	private static ReturnMsg getRecordAndUsFile(MonitorHeartrateRecord record, Integer start, Integer end) {
		if (StringUtils.isEmpty(record.getRecordFiles()) || StringUtils.isEmpty(record.getUterusRecord())) {
			return new ReturnMsg<>(ReturnMsg.FAIL, "胎心文件和宫缩文件必须都不为空!");
		}
		String recordUrl = record.getRecordFiles();
		int[] recordFile = getRecordFile(recordUrl, start, end);
		String usUrl = record.getUterusRecord();
		int[] usFile = getRecordFile(usUrl, start, end);
		if (recordFile == null || usFile == null) {
			return new ReturnMsg<>(ReturnMsg.FAIL, "胎心文件和宫缩文件必须都不为空!");
		}
		Map<String, int[]> map = new HashMap<>();
		map.put("recordFile", recordFile);
		map.put("usFile", usFile);

		return new ReturnMsg<>(ReturnMsg.SUCCESS, "获取胎心、宫缩文件成功!", map);
	}

	/**
	 * 获取改良fischer评分
	 * 
	 * @param recordFile
	 *            胎心文件
	 * @param usFile
	 *            宫缩文件
	 *            胎动字符串
	 *            开始点
	 *            结束点
	 * @return
	 */
	private static ReturnMsg getFischerScore(int[] recordFile, int[] usFile,int[] fm, int fetalMoveNum) {
		if (recordFile == null || usFile == null) {
			return new ReturnMsg<>(ReturnMsg.FAIL, "胎心文件和宫缩文件必须都不为空!");
		}

		if (recordFile.length != usFile.length) {
			System.err.println("胎心文件和宫缩文件长度不一致!recordFile:" + recordFile.length + ",usFile:" + usFile.length);
			return new ReturnMsg<>(ReturnMsg.FAIL, "胎心文件和宫缩文件长度不一致!");
		}

		// 检查胎心文件
		ReturnMsg checkMsg = checkRecordFile(recordFile);
		if (checkMsg.getMsg() == 0) {
			return checkMsg;
		}
		// 替换文件中不符合要求的数据
		recordFile = replaceRecordFile(recordFile);

		if (recordFile.length < 1200) {
			return new ReturnMsg<>(ReturnMsg.FAIL, "胎监数据小于10分钟,不支持自动评分!");
		}
		VOFhrParameterNew setParams = new VOFhrParameterNew();
		//setParams.setNumFetalMove(fetalMoveNum);
		ReturnMsg msg = setParameter(setParams);
		if (msg.getMsg() == 0) {
			return msg;
		}
		FhrScoreNew calFhrFischer2 = null;
		try {
			calFhrFischer2 = JniDllNew.Instance.JP1_calFhrFischer2(recordFile, usFile, fm ,recordFile.length, 2);
		} catch (Exception e) {
			log.error("获取改良Fischer评分失败!", e);
			return new ReturnMsg<>(ReturnMsg.FAIL, "获取改良Fischer评分失败!");
		}
		VOFhrScore voFhrScore = new VOFhrScore();
		voFhrScore.setBasal(calFhrFischer2.basal);
		voFhrScore.setBasalScore(calFhrFischer2.basal_score);
		voFhrScore.setBpmVar((int) calFhrFischer2.bpm_var);
		voFhrScore.setBpmVScore(calFhrFischer2.bpm_v_score);
		
		voFhrScore.setFreqVar((int) calFhrFischer2.freq_var);
		int freqVScore=0;
		if((int) calFhrFischer2.freq_var<2){
			freqVScore=0;
		}else if((int) calFhrFischer2.freq_var==2){
			freqVScore=1;
		}else if((int) calFhrFischer2.freq_var>=3){
			freqVScore=2;
		}
		voFhrScore.setFreqVScore(freqVScore);
		voFhrScore.setNumAcc(calFhrFischer2.num_acc);
		voFhrScore.setNumAccScore(calFhrFischer2.num_acc_score);
		voFhrScore.setNumDecScore(calFhrFischer2.num_dec_score);
		voFhrScore.setTotalScore(calFhrFischer2.basal_score+calFhrFischer2.bpm_v_score+freqVScore+calFhrFischer2.num_acc_score+calFhrFischer2.num_dec_score);

		int numDec = calFhrFischer2.num_dec;
		int severity = calFhrFischer2.severity;
		//是否有宫缩（0=无；1=有）
		int contractionsFlag = calFhrFischer2.acog_list16;
		System.out.println("contractionsFlag : " + contractionsFlag);
		voFhrScore.setContractionsFlag(contractionsFlag);
		if (calFhrFischer2.num_pd > 0) {// 如果有pd 就是0分
			severity = 3;
		}
		
		switch (voFhrScore.getNumDecScore()) {
		case 0:
			numDec = 2;
			break;
		case 1:
			numDec = 1;
			break;
		case 2:
			numDec = 0;
			break;
		default:
			numDec = 0;
			break;
		}
		// switch (severity) {
		// // 减速类型判断（偶发减速=0, 早发减速ED=1，轻度变异MVD=2，迟发减速LD =3，重度变异SVD=4，）
		// case 1:
		// case 2:
		// if (calFhrFischer2.vdLen > 2 || calFhrFischer2.edLen > 2) {
		// numDec = 1;
		// } else {
		// numDec = 0;
		// }
		// break;
		// case 3:
		// case 4:
		// numDec = 2;
		// break;
		// default:
		// numDec = 0;
		// break;
		// }
		voFhrScore.setNumDec(numDec);
		voFhrScore.setNumFetalMove(fetalMoveNum);
		return new ReturnMsg<>(ReturnMsg.SUCCESS, "成功!", voFhrScore);
	}

	/**
	 * 把不符合条件的数据替换成相对符合的数据
	 * 
	 * @param recordFile
	 * @return
	 */
	private static int[] replaceRecordFile(int[] recordFile) {
		if (recordFile[0] < 60 || recordFile[0] > 210) {
			for (int j = 0; j < 10; j++) {
				if (recordFile[j] > 60 && recordFile[j] < 210) {
					for (int j2 = 0; j2 < j; j2++) {
						recordFile[j2] = recordFile[j];
					}
					break;
				}
			}
		}
		int length = recordFile.length;
		if (recordFile[length - 1] < 60 || recordFile[length - 1] > 210) {
			for (int j = 1; j < 10; j++) {
				if (recordFile[length - j] > 60 && recordFile[length - j] < 210) {
					for (int j2 = 1; j2 < j; j2++) {
						recordFile[length - j2] = recordFile[length - j];
					}
					break;
				}
			}
		}
		int temp;
		for (int i = 1; i < recordFile.length; i++) {
			if (recordFile[i] < 60 || recordFile[i] > 210) {
				temp = recordFile[i - 1];
				for (int j = 0; j < 10; j++) {
					if (recordFile[i + j] > 60 && recordFile[i + j] < 210) {
						for (int j2 = i; j2 < i + j; j2++) {
							recordFile[j2] = (temp + recordFile[i + j]) / 2;
						}
						break;
					}
				}
			}
		}
		return recordFile;
	}

	/**
	 * 获取胎动数
	 * 
	 * @param fetalMoveValue
	 *            胎动字符串
	 * @return
	 */
	private static int getFetalMoveNum(String fetalMoveValue, Integer start, Integer end) {
		int fetalMoveNum = 0;
		if (!org.springframework.util.StringUtils.isEmpty(fetalMoveValue) && !"[]".equals(fetalMoveValue)) {
			ObjectMapper mapper = new ObjectMapper();
			List<TimeClass> list = new ArrayList<>();
			try {
				list = mapper.readValue(fetalMoveValue,
						TypeFactory.defaultInstance().constructCollectionType(List.class, TimeClass.class));
			} catch (IOException e) {
				System.err.println("getFetalMoveNum:解析胎动数据失败!");
				e.printStackTrace();
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
			Date[] dates = new Date[list.size()];
			for (int i = 0; i < list.size(); i++) {
				try {
					dates[i] = dateFormat.parse(list.get(i).getTime());
				} catch (ParseException e) {
					System.err.println("格式化字符串为时间失败!");
					e.printStackTrace();
				}
			}
			Arrays.sort(dates);
			Calendar calendar = Calendar.getInstance();
			Date tempDate = dates[0];
			Date startDate = null;
			Date endDate = null;
			if (start != null && end != null && start < end) {
				try {
					startDate = dateFormat.parse(start / 2 / 60 + ":" + (start / 2 % 60));
					endDate = dateFormat.parse(end / 2 / 60 + ":" + (end / 2 % 60));
				} catch (ParseException e) {
					System.err.println("getFetalMoveNum:格式化开始,结束时间失败!");
					e.printStackTrace();
				}
			}
			for (int i = 0; i < dates.length; i++) {
				if (tempDate.compareTo(dates[i]) <= 0 && ((startDate == null && endDate == null)
						|| (startDate.compareTo(dates[i]) <= 0 && endDate.compareTo(dates[i]) >= 0))) {
					fetalMoveNum++;
					calendar.setTime(dates[i]);
					calendar.add(Calendar.MINUTE, 3);
					tempDate = calendar.getTime();
				}
			}
		}
		return fetalMoveNum;
	}

	/**
	 * 获取加速减速的开始和结束坐标
	 * 
	 *            调用过getCalFhrScore后该参数不用填,如果单独调用必填
	 * @param recordFile
	 *            如果url为null必填,如果不为null不用填
	 *            结束点
	 *            开始点
	 * @return
	 */
	public static ReturnMsg getFischerLocation(int[] recordFile, int[] usFile) {
		// 获取加速减速的开始和结束坐标
		CFhrLocation location = JniDllNew.Instance.JP1_getFischer2Location();
		log.info("getFischerLocation=====>recordFile_length:"+recordFile.length+"||===>usFile_length:"+usFile.length);
		List<VOCFhrLocation> list = getLocation(recordFile, location);

		return new ReturnMsg(ReturnMsg.SUCCESS, "成功!", list);
	}

	private static List<VOCFhrLocation> getLocation(int[] recordFile, CFhrLocation location) {
		int[] accBeginX = location.acc_begin.getIntArray(0, location.acc_begin_len);
		int[] accEndX = location.acc_end.getIntArray(0, location.acc_end_len);
		int[] decBeginX = location.dec_begin.getIntArray(0, location.dec_begin_len);
		int[] decEndX = location.dec_end.getIntArray(0, location.dec_end_len);
		List<VOCFhrLocation> list = new ArrayList<>();
		int x, y;
		VOCFhrLocation vo;
		if (accBeginX != null && accEndX != null && accBeginX.length == accEndX.length) {
			for (int i = 0; i < accBeginX.length; i++) {
				x = (accBeginX[i] + accEndX[i]) / 2;
				y = recordFile[x];
				vo = new VOCFhrLocation();
				vo.setX(x / 2);
				vo.setY(y);
				vo.setFlag(true);
				list.add(vo);
			}
		}
		if (decBeginX != null && decEndX != null && decBeginX.length == decEndX.length) {
			for (int i = 0; i < decBeginX.length; i++) {
				x = (decBeginX[i] + decEndX[i]) / 2;
				y = recordFile[x];
				vo = new VOCFhrLocation();
				vo.setX(x / 2);
				vo.setY(y);
				vo.setFlag(false);
				list.add(vo);
			}
		}
		return list;
	}

	/**
	 * 获取基线数组
	 * 
	 *            宫缩文件
	 *            开始点
	 *            结束点
	 * @return
	 */
	public static ReturnMsg getFischerBaseline() {
		CFhrBaseline baseline = JniDllNew.Instance.JP1_getFischer2Baseline();
		log.info("getFischerBaseline=====>");
		int[] baselines = getBaselines(baseline);
		return new ReturnMsg(ReturnMsg.SUCCESS, "成功!", baselines);
	}

	/**
	 * 获取自动评分(Krebs)
	 * 
	 * @param record
	 * @param start
	 *            开始点
	 * @param end
	 *            结束点
	 * @return
	 */

	public static ReturnMsg getKrebsFhrInfo(MonitorHeartrateRecord record, Integer start, Integer end) {
		synchronized (JNIUtilsNew.class) {
			try {
				String url = record.getRecordFiles();
				int[] recordFile = getRecordFile(url, start, end);
				if (recordFile == null) {
					return new ReturnMsg(ReturnMsg.FAIL, "胎心文件不正确!");
				}
				
				// 获取胎心次数
				int fetalMoveNum = getFetalMoveNum(record.getFetalMoveValue(), start, end);
				// 获取胎心所有评分
				ReturnMsg krebsScore = getKrebsScore(recordFile,new int[recordFile.length], fetalMoveNum);
				if (krebsScore.getMsg() == 0) {
					return krebsScore;
				}

				// 获取加速减速的开始和结束坐标
				ReturnMsg krebsLocation = getKrebsLocation(recordFile);
				if (krebsLocation.getMsg() == 0) {
					return krebsLocation;
				}

				// 获取基线数组
				ReturnMsg KrebsBaseline = getKrebsBaseline();
				if (KrebsBaseline.getMsg() == 0) {
					return KrebsBaseline;
				}
				VOFhrInfo info = new VOFhrInfo();
				info.setBaselines((int[]) KrebsBaseline.getData());
				info.setVocFhrLocation((List<VOCFhrLocation>) krebsLocation.getData());
				info.setVoFhrScore((VOFhrScore) krebsScore.getData());
				List<VOFhrInfo> list = new ArrayList<>();
				list.add(info);
				return new ReturnMsg(ReturnMsg.SUCCESS, "获取胎心自动评分信息成功!", JSON.toJSONString(list));
			} catch (Exception e) {
				log.error("获取胎心自动评分信息失败!", e);
				return new ReturnMsg(ReturnMsg.FAIL, "获取胎心自动评分信息失败!");
			}finally {

			}
		}
	}

	/**
	 * 获取基线数组
	 * 
	 */
	public static ReturnMsg getKrebsBaseline() {
		// 获取基线数组
		CFhrBaseline baseline = JniDllNew.Instance.JP1_getKrebsBaseline();
		int[] baselines = getBaselines(baseline);
		return new ReturnMsg(ReturnMsg.SUCCESS, "成功!", baselines);
	}

	private static int[] getBaselines(CFhrBaseline baseline) {
		double[] doubleArray = baseline.fhrBas.getDoubleArray(0, baseline.len);
		int[] baselines = new int[doubleArray.length];
		for (int i = 0; i < doubleArray.length; i++) {
			baselines[i] = (int) doubleArray[i];
		}
		return baselines;
	}

	/**
	 * 查找加速减速的开始和结束坐标
	 * 
	 *            调用过getCalFhrScore后该参数不用填,如果单独调用必填
	 * @param recordFile
	 *            如果url为null必填,如果不为null不用填
	 * @return
	 */
	public static ReturnMsg getKrebsLocation(int[] recordFile) {

		// 获取加速减速的开始和结束坐标
		CFhrLocation location = JniDllNew.Instance.JP1_getKrebsLocation();
		List<VOCFhrLocation> list = getLocation(recordFile, location);

		return new ReturnMsg(ReturnMsg.SUCCESS, "成功!", list);
	}

	/**
	 * 获取胎心率的所有评分
	 * 
	 *
	 * @return
	 */
	public static ReturnMsg getKrebsScore(int[] recordFile,int[] fm, int fetalMoveNum) {

		// 检查胎心文件
		ReturnMsg checkMsg = checkRecordFile(recordFile);
		if (checkMsg.getMsg() == 0) {
			return checkMsg;
		}
		// 替换不符合要求的数据
		recordFile = replaceRecordFile(recordFile);

		if (recordFile.length < 1200) {
			return new ReturnMsg<>(ReturnMsg.FAIL, "胎监数据小于10分钟,不支持自动评分!");
		}

		VOFhrParameterNew setParams = new VOFhrParameterNew();
		//setParams.setNumFetalMove(fetalMoveNum);
		ReturnMsg msg = setParameter(setParams);
		if (msg.getMsg() == 0) {
			return msg;
		}
		FhrScoreNew calFhrScore = null;
		try {
			int size = recordFile.length + 1024 * 512;
			Pointer out = new Memory(size);
			long peer = Pointer.nativeValue(out);
			calFhrScore = JniDllNew.Instance.JP1_calFhrKrebs(recordFile,fm, recordFile.length, 2);
			Native.free(peer);
			Pointer.nativeValue(out, 0);
		} catch (Exception e) {
			log.error("获取Krebs评分失败!", e);
			return new ReturnMsg<>(ReturnMsg.FAIL, "获取Krebs评分失败!");
		}
		VOFhrScore voFhrScore = new VOFhrScore();
		voFhrScore.setBasal(calFhrScore.basal);
		voFhrScore.setBasalScore(calFhrScore.basal_score);
		voFhrScore.setBpmVar((int) calFhrScore.bpm_var);
		voFhrScore.setBpmVScore(calFhrScore.bpm_v_score);
		voFhrScore.setFreqVar((int) calFhrScore.freq_var);
		voFhrScore.setFreqVScore(calFhrScore.freq_v_score);
		voFhrScore.setNumAcc(calFhrScore.num_acc);
		voFhrScore.setNumAccScore(calFhrScore.num_acc_score);
		voFhrScore.setNumDec(calFhrScore.num_dec);
		voFhrScore.setNumDecScore(calFhrScore.num_dec_score);
		//voFhrScore.setNumFetalMoveScore(calFhrScore.num_fetal_move_score);
		voFhrScore.setTotalScore(calFhrScore.total_score);
		//voFhrScore.setNumFetalMove(fetalMoveNum);

		if (voFhrScore.getNumDec() > 2) {
			voFhrScore.setNumDec(2);
		}
		return new ReturnMsg<>(ReturnMsg.SUCCESS, "成功!", voFhrScore);
	}

	/**
	 * 检查胎心文件(每5分钟断点时长不能超过5秒)
	 * 
	 * @param recordFile
	 * @return
	 */
	private static ReturnMsg checkRecordFile(int[] recordFile) {
		int length = recordFile.length;
		if (length >= 600) {
			length = 601;
		}
		int count = 0;
		for (int i = 0; i < recordFile.length; i++) {
			int k = recordFile[i];
			if (k > 210 || k < 60) {
				count++;
			}
			if (i % (length - 1) == 0) {
				if (count > 10) {
					return new ReturnMsg<>(ReturnMsg.FAIL, "胎监曲线中断过多，不支持自动评分！");
				} else {
					count = 0;
				}
			}
		}
		return new ReturnMsg<>(ReturnMsg.SUCCESS, "检查完成!");
	}

	private static int[] getRecordFile(String url, Integer start, Integer end) {
		String jsonFileStr="";
		if(url.contains("monitor_oss/") || url.contains("yjk_oss/")){
			jsonFileStr = JsonUtils.getJsonFileStr(ConfigProUtils.get("MONITOR_OSS_FILE_URL") + url);
		}else{
			jsonFileStr = JsonUtils.getJsonFileStr(ConfigProUtils.get("BASE_FILE_URL") + url);
		}
		String replace = jsonFileStr.replace("[", "").replace("]", "").replaceAll("\\s", "");
		if (StringUtils.isEmpty(replace)) {
			return null;
		}
		String[] split = replace.split(",");
		int length = split.length;
		int[] recordFile = new int[length];
		try {
			for (int i = 0; i < length; i++) {
				int parseInt = Integer.parseInt(split[i].trim());
				recordFile[i] = parseInt;
			}
			if (start != null && end != null && start < end) {
				if (start < 0) {
					start = 0;
				}
				if (end > recordFile.length) {
					end = recordFile.length;
				}
				int[] temp = new int[end - start];

				for (int i = 0; i < temp.length; i++) {
					temp[i] = recordFile[start + i];
				}
				recordFile = temp;
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		log.info("recordFile:"+recordFile);
		return recordFile;
	}

	public static VOFhrScoreNew getDecelerationFischerFhrInfo(int[] data, int[] uc) {
		/*VOFhrParameter setParams = new VOFhrParameter();
		//setParams.setNumFetalMove(fetalMoveNum);
		ReturnMsg msg = setParameter(setParams);
		if (msg.getMsg() == 0) {
			return msg;
		}*/
		FhrScoreNew calFhrFischer2 = null;
		try {
			calFhrFischer2 = JniDllNew.Instance.JP1_calFhrFischer2(data, uc, new int[data.length] ,data.length, 2);
		} catch (Exception e) {
			log.error("获取改良Fischer评分失败!", e);
			return null;
		}
		VOFhrScoreNew voFhrScore = new VOFhrScoreNew();
		voFhrScore.setBasal(calFhrFischer2.basal);
		voFhrScore.setBasalScore(calFhrFischer2.basal_score);
		voFhrScore.setBpmVar((int) calFhrFischer2.bpm_var);
		voFhrScore.setBpmVScore(calFhrFischer2.bpm_v_score);
		
		voFhrScore.setFreqVar((int) calFhrFischer2.freq_var);
		int freqVScore=0;
		if((int) calFhrFischer2.freq_var<2){
			freqVScore=0;
		}else if((int) calFhrFischer2.freq_var==2){
			freqVScore=1;
		}else if((int) calFhrFischer2.freq_var>=3){
			freqVScore=2;
		}
		voFhrScore.setFreqVScore(freqVScore);
		voFhrScore.setNumAcc(calFhrFischer2.num_acc);
		voFhrScore.setNumAccScore(calFhrFischer2.num_acc_score);
		voFhrScore.setNumDecScore(calFhrFischer2.num_dec_score);
		voFhrScore.setTotalScore(calFhrFischer2.basal_score+calFhrFischer2.bpm_v_score+freqVScore+calFhrFischer2.num_acc_score+calFhrFischer2.num_dec_score);

		int numDec = calFhrFischer2.num_dec;
		int severity = calFhrFischer2.severity;
		if (calFhrFischer2.num_pd > 0) {// 如果有pd 就是0分
			severity = 3;
		}
		switch (voFhrScore.getNumDecScore()) {
		case 0:
			numDec = 2;
			break;
		case 1:
			numDec = 1;
			break;
		case 2:
			numDec = 0;
			break;
		default:
			numDec = 0;
			break;
		}
		voFhrScore.setNumDec(numDec);
		//减速
		int decloc = calFhrFischer2.dec_loc;
		voFhrScore.setDecLoc(decloc);
		//是否有宫缩（0=无；1=有）
		int contractionsFlag = calFhrFischer2.acog_list16;
		System.out.println("contractionsFlag : " + contractionsFlag);
		voFhrScore.setContractionsFlag(contractionsFlag);
		return voFhrScore;
	}


	
	/**
	 * 获取胎动数
	 * 
	 * @param fetalMoveValue
	 *            胎动字符串
	 * @return
	 */
	private static int getFetalMoveNum(String fetalMoveValue,String fetalMoveAutoValue, Integer start, Integer end) {
		int fetalMoveNum = 0;
		if (!org.springframework.util.StringUtils.isEmpty(fetalMoveValue) && !"[]".equals(fetalMoveValue)) {
			ObjectMapper mapper = new ObjectMapper();
			List<TimeClass> list = new ArrayList<>();
			try {
				list = mapper.readValue(fetalMoveValue,
						TypeFactory.defaultInstance().constructCollectionType(List.class, TimeClass.class));
				if (!org.springframework.util.StringUtils.isEmpty(fetalMoveAutoValue) && !"[]".equals(fetalMoveAutoValue)) {
					List<TimeClass> list2 = new ArrayList<>();
					list2 = mapper.readValue(fetalMoveAutoValue,
							TypeFactory.defaultInstance().constructCollectionType(List.class, TimeClass.class));
					list.addAll(list2);
				}
			} catch (IOException e) {
				System.err.println("getFetalMoveNum:解析胎动数据失败!");
				e.printStackTrace();
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
			Date[] dates = new Date[list.size()];
			for (int i = 0; i < list.size(); i++) {
				try {
					dates[i] = dateFormat.parse(list.get(i).getTime());
				} catch (ParseException e) {
					System.err.println("格式化字符串为时间失败!");
					e.printStackTrace();
				}
			}
			Arrays.sort(dates);
			Calendar calendar = Calendar.getInstance();
			Date tempDate = dates[0];
			Date startDate = null;
			Date endDate = null;
			if (start != null && end != null && start < end) {
				try {
					startDate = dateFormat.parse(start / 2 / 60 + ":" + (start / 2 % 60));
					endDate = dateFormat.parse(end / 2 / 60 + ":" + (end / 2 % 60));
				} catch (ParseException e) {
					System.err.println("getFetalMoveNum:格式化开始,结束时间失败!");
					e.printStackTrace();
				}
			}
			for (int i = 0; i < dates.length; i++) {
				if (tempDate.compareTo(dates[i]) <= 0 && ((startDate == null && endDate == null)
						|| (startDate.compareTo(dates[i]) <= 0 && endDate.compareTo(dates[i]) >= 0))) {
					fetalMoveNum++;
					calendar.setTime(dates[i]);
					calendar.add(Calendar.MINUTE, 3);
					tempDate = calendar.getTime();
				}
			}
		}
		return fetalMoveNum;
	}

}
