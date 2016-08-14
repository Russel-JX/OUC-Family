/**
 * ============================================================
 * File : UserInfo.java
 * Description : 
 * 
 * Package : com.brilliance.po
 * Author : Michael
 * Last Edited By :
 * Version : 1.0
 * Created on : 2014-3-11
 * History
 * Modified By : Initial Release
 * Classification : Personality
 * Copyright (C) 2014 Michael. All rights reserved
 *
 * ============================================================
 */

package com.brilliance.po;

import java.util.Date;


/*******************************************************************************
 *
 * @Author 		: Michael
 * @Version 	: 1.0
 * @Date Created: 2014-3-11
 * @Date Modified : 
 * @Modified By : 
 * @Contact 	:
 * @Description : 用户表信息
 * @History		:
 *
 ******************************************************************************/
public class UserInfo {
	
	private Integer id;          //ID
	private String userId;       //用户ID
	private String name;         //名字
	private String mobileNO;     //手机号码
	private String email;        //邮件地址
	private String provinceId;      //省份
	private String cityId;      //城市
	private String areaId;      //区县
	private String address;      //地址
	private String password;     //密码
	private Integer loginTimes;      //登录次数
	private String longitude;
	private String latitude;
	private Date createTime;     //创建时间
	private Integer credits;         //积分
	private Date lastUpdateTime; //上一次更新日期
	private Date birthDay;       //出生日期
	private String gender;       //性别
	private Integer updateBy;        //操作人
	private Integer status;          //DEFAULT '1',状态,0/1
	
	
	public UserInfo(){
		
	}
	
	public UserInfo(String userId, String name, String mobileNO, String provinceId, String cityId, String areaId,String email,
			String address, String password, Integer loginTimes, Date createTime,
			Integer credits, Date lastUpdateTime, Date birthDay, String gender,
			Integer updateBy, Integer status) {
        
		this.userId = userId;
		this.name = name;
		this.mobileNO = mobileNO;
		this.email = email;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.areaId = areaId;
		this.address = address;
		this.password = password;
		this.loginTimes = loginTimes;
		this.createTime = createTime;
		this.credits = credits;
		this.lastUpdateTime = lastUpdateTime;
		this.birthDay = birthDay;
		this.gender = gender;
		this.updateBy = updateBy;
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the mobileNO
	 */
	public String getMobileNO() {
		return mobileNO;
	}
	
	/**
	 * @param mobileNO the mobileNO to set
	 */
	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the loginTimes
	 */
	public Integer getLoginTimes() {
		return loginTimes;
	}
	
	/**
	 * @param loginTimes the loginTimes to set
	 */
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * @return the credits
	 */
	public Integer getCredits() {
		return credits;
	}
	
	/**
	 * @param credits the credits to set
	 */
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	
	/**
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	/**
	 * @return the birthDay
	 */
	public Date getBirthDay() {
		return birthDay;
	}
	
	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @return the updateBy
	 */
	public Integer getUpdateBy() {
		return updateBy;
	}
	
	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
}
/*
CREATE TABLE `user_info` (
		  `ID` Integer(11) NOT NULL,
		  `USERID` varchar(20) NOT NULL,
		  `NAME` varchar(50) DEFAULT NULL COMMENT '名字',
		  `MOBILENO` varchar(20) DEFAULT NULL COMMENT '手机号码',
		  `PROVINCEID` VARCHAR(20) DEFAULT NULL COMMENT '省份ID',
		  `CITYID` VARCHAR(20) DEFAULT NULL COMMENT '城市ID',
		  `AREAID` VARCHAR(20) DEFAULT NULL COMMENT '区县ID',
		  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮件地址',
		  `ADDRESS` varchar(200) DEFAULT NULL COMMENT '地址',
		  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '密码',
		  `LOGTIMES` Integer(11) DEFAULT NULL COMMENT '登陆次数',
		  `CREATETIME` timestamp NULL DEFAULT NULL,
		  `CREDITS` Integer(11) DEFAULT NULL COMMENT '积分',
		  `LASTUPDATETIME` timestamp NULL DEFAULT NULL,
		  `BIRTHDAY` date DEFAULT NULL,
		  `GENDER` varchar(2) DEFAULT NULL,
		  `UPDATEBY` Integer(11) DEFAULT NULL COMMENT '操作人',
		  `STATUS` Integer(11) DEFAULT '1' COMMENT '状态,0/1',
		  `LOGINTIMES` Integer(11) DEFAULT NULL,
		  `LONGITUDE` VARCHAR(50) NULL,
		  `LATITUDE` VARCHAR(50) NULL;
		  PRIMARY KEY (`ID`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
*/


