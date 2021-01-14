package com.rmi.vo;


import java.io.Serializable;
import java.util.Date;

public class MonitorHeartrateRecord implements Serializable {

	private Integer id;

	private Integer userId;

	private String fetalMoveFiles;

	private String recordFiles;

	private Date addTime;

	private String rawFiles;

	private String title;

	/**
	 * 心率类型:0:成人,1婴儿
	 */
	private Integer type;

	/**
	 * 平均心率
	 */
	private Double averageRate;

	private Integer fetalMoveTimes;

	private Integer testTime;

	private String uterusRecord;

	/**
	 * 4.0版本新增主键
	 */
	private Long jid;

	/**
	 * 测量仪器mac地址
	 */
	private String deviceMac;

	/**
	 * 关联user_heartrate_record主键id
	 */
	private Integer userRecordId;

	private String fetalMoveValue;
	
	//自动胎动
	private String autoFetalMoveValue;
	private String autoFetalMoveFiles;
	private Integer autoFetalMoveTimes;
	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return user_id
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return fetal_move_files
	 */
	public String getFetalMoveFiles() {
		return fetalMoveFiles;
	}

	/**
	 * @param fetalMoveFiles
	 */
	public void setFetalMoveFiles(String fetalMoveFiles) {
		this.fetalMoveFiles = fetalMoveFiles;
	}

	/**
	 * @return record_files
	 */
	public String getRecordFiles() {
		return recordFiles;
	}

	/**
	 * @param recordFiles
	 */
	public void setRecordFiles(String recordFiles) {
		this.recordFiles = recordFiles;
	}

	/**
	 * @return add_time
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * @param addTime
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * @return raw_files
	 */
	public String getRawFiles() {
		return rawFiles;
	}

	/**
	 * @param rawFiles
	 */
	public void setRawFiles(String rawFiles) {
		this.rawFiles = rawFiles;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取心率类型:0:成人,1婴儿
	 *
	 * @return type - 心率类型:0:成人,1婴儿
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置心率类型:0:成人,1婴儿
	 *
	 * @param type
	 *            心率类型:0:成人,1婴儿
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取平均心率
	 *
	 * @return average_rate - 平均心率
	 */
	public Double getAverageRate() {
		return averageRate;
	}

	/**
	 * 设置平均心率
	 *
	 * @param averageRate
	 *            平均心率
	 */
	public void setAverageRate(Double averageRate) {
		this.averageRate = averageRate;
	}

	/**
	 * @return fetal_move_times
	 */
	public Integer getFetalMoveTimes() {
		return fetalMoveTimes;
	}

	/**
	 * @param fetalMoveTimes
	 */
	public void setFetalMoveTimes(Integer fetalMoveTimes) {
		this.fetalMoveTimes = fetalMoveTimes;
	}

	/**
	 * @return test_time
	 */
	public Integer getTestTime() {
		return testTime;
	}

	/**
	 * @param testTime
	 */
	public void setTestTime(Integer testTime) {
		this.testTime = testTime;
	}

	/**
	 * @return uterus_record
	 */
	public String getUterusRecord() {
		return uterusRecord;
	}

	/**
	 * @param uterusRecord
	 */
	public void setUterusRecord(String uterusRecord) {
		this.uterusRecord = uterusRecord;
	}

	/**
	 * 获取4.0版本新增主键
	 *
	 * @return jid - 4.0版本新增主键
	 */
	public Long getJid() {
		return jid;
	}

	/**
	 * 设置4.0版本新增主键
	 *
	 * @param jid
	 *            4.0版本新增主键
	 */
	public void setJid(Long jid) {
		this.jid = jid;
	}

	/**
	 * 获取测量仪器mac地址
	 *
	 * @return device_mac - 测量仪器mac地址
	 */
	public String getDeviceMac() {
		return deviceMac;
	}

	/**
	 * 设置测量仪器mac地址
	 *
	 * @param deviceMac
	 *            测量仪器mac地址
	 */
	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	/**
	 * 获取关联user_heartrate_record主键id
	 *
	 * @return user_record_id - 关联user_heartrate_record主键id
	 */
	public Integer getUserRecordId() {
		return userRecordId;
	}

	/**
	 * 设置关联user_heartrate_record主键id
	 *
	 * @param userRecordId
	 *            关联user_heartrate_record主键id
	 */
	public void setUserRecordId(Integer userRecordId) {
		this.userRecordId = userRecordId;
	}

	/**
	 * @return fetal_move_value
	 */
	public String getFetalMoveValue() {
		return fetalMoveValue;
	}

	/**
	 * @param fetalMoveValue
	 */
	public void setFetalMoveValue(String fetalMoveValue) {
		this.fetalMoveValue = fetalMoveValue;
	}

	public String getAutoFetalMoveValue() {
		return autoFetalMoveValue;
	}

	public void setAutoFetalMoveValue(String autoFetalMoveValue) {
		this.autoFetalMoveValue = autoFetalMoveValue;
	}

	public String getAutoFetalMoveFiles() {
		return autoFetalMoveFiles;
	}

	public void setAutoFetalMoveFiles(String autoFetalMoveFiles) {
		this.autoFetalMoveFiles = autoFetalMoveFiles;
	}

	public Integer getAutoFetalMoveTimes() {
		return autoFetalMoveTimes;
	}

	public void setAutoFetalMoveTimes(Integer autoFetalMoveTimes) {
		this.autoFetalMoveTimes = autoFetalMoveTimes;
	}
}