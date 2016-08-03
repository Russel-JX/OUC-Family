/**
 * ============================================================
 * File : ExpressEvalution.java
 * Description : 
 * 
 * Package : com.brilliance.po
 * Author : Michael
 * Last Edited By :
 * Version : 1.0
 * Created on : 2014-3-12
 * History
 * Modified By : Initial Release
 * Classification : Personality
 * Copyright (C) 2014 Michael. All rights reserved
 *
 * ============================================================
 */

package com.brilliance.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity(name="expressEvalution")
@Table(name = "EXPRESS_EVALUATION")
public class ExpressEvalution implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6217805062716509896L;
	private Integer id;            //ID
	private String addressId;
	private String imageUrl;
	private String expressCode;    //快递公司代号
	private String userId;         //用户ID
	private String orderCode;      //订单id
	private String score;      //评价分数
	private String extraInfo;      //备注信息
	private Date createTime;       //创建时间
	private String createTimeStr;
	private String expressName;
	private String logoPath;
	private String userName;
	private String mobileNo;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getExpressCode() {
		return expressCode;
	}
	
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getOrderCode() {
		return orderCode;
	}
	
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	public String getScore() {
		return score;
	}
	
	public void setScore(String score) {
		this.score = score;
	}
	
	public String getExtraInfo() {
		return extraInfo;
	}
	
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Transient
	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	@Transient
	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	@Transient
	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Transient
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
}
/*
CREATE TABLE `express_evalution` (
		  `ID` Integer(11) unsigned NOT NULL AUTO_INCREMENT,
		  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
		  `USERID` varchar(20) DEFAULT NULL COMMENT '用户id',
		  `ORDERCODE` varchar(20) DEFAULT NULL COMMENT '订单id',
		  `SCORE` decimal(20,0) DEFAULT NULL COMMENT '评价分数',
		  `EXTRAINFO` varchar(2000) DEFAULT NULL COMMENT '备注信息',
		  `CREATETIME` timestamp NULL DEFAULT NULL,
		  PRIMARY KEY (`ID`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;
*/