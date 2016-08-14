/**
 * ============================================================
 * File : AddressExpressMapping.java
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

import java.util.Date;


/*******************************************************************************
 *
 * @Author 		: Michael
 * @Version 	: 1.0
 * @Date Created: 2014-3-12
 * @Date Modified : 
 * @Modified By : 
 * @Contact 	:
 * @Description : 
 * @History		:
 *
 ******************************************************************************/
public class AddressExpressMapping {
	private Integer id;
	private String expressCode;
	private String addressId;
	private String subExpressAddress;
	private String subExpressMobile;
	private String rangeDetail;
	private Date createTime;
	private Date lastDateTime;
	private Integer status;
	
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
	
	public String getAddressId() {
		return addressId;
	}
	
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	public String getSubExpressAddress() {
		return subExpressAddress;
	}
	
	public void setSubExpressAddress(String subExpressAddress) {
		this.subExpressAddress = subExpressAddress;
	}
	
	public String getSubExpressMobile() {
		return subExpressMobile;
	}
	
	public void setSubExpressMobile(String subExpressMobile) {
		this.subExpressMobile = subExpressMobile;
	}
	
	public String getRangeDetail() {
		return rangeDetail;
	}
	
	public void setRangeDetail(String rangeDetail) {
		this.rangeDetail = rangeDetail;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getLastDateTime() {
		return lastDateTime;
	}
	
	public void setLastDateTime(Date lastDateTime) {
		this.lastDateTime = lastDateTime;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
/*
CREATE TABLE `address_express_mapping` (
		  `ID` Integer(11) unsigned NOT NULL AUTO_INCREMENT,
		  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
		  `ADDRESSID` varchar(200) DEFAULT NULL COMMENT '区域组合编号',
		  `SUBEXPRESSADDRESS` varchar(200) DEFAULT NULL COMMENT '当前地理位置的快递公司总部地址',
		  `SUBEXPRESSMOBILE` varchar(200) DEFAULT NULL COMMENT '当前地理位置的快递公司总部电话',
		  `RANGEDETAIL` varchar(500) DEFAULT NULL COMMENT '配送范围明细',
		  `CREATETIME` timestamp NULL DEFAULT NULL,
		  `LASTUPDATETIME` timestamp NULL DEFAULT NULL,
		  `STATUS` Integer(11) DEFAULT '1' COMMENT '状态,0/1',
		  PRIMARY KEY (`ID`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递公司覆盖范围表';
*/