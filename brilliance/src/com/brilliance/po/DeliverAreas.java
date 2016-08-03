/**
 * ============================================================
 * File : DeliverAreas.java
 * Description : 
 * 
 * Package : com.brilliance.po
 * Author : Michael
 * Last Edited By :
 * Version : 1.0
 * Created on : 2014-3-19
 * History
 * Modified By : Initial Release
 * Classification : Brilliance Confidential
 * Copyright (C) 2014 Brilliance Team. All rights reserved
 *
 * ============================================================
 */

package com.brilliance.po;

import java.util.Date;

/*******************************************************************************
 *
 * @Author 		: Michael
 * @Version 	: 1.0
 * @Date Created: 2014-3-19
 * @Date Modified : 
 * @Modified By : 
 * @Contact 	:
 * @Description : 
 * @History		:
 *
 ******************************************************************************/
public class DeliverAreas {
	private Integer id;               //ID
	private String expressCode;       //快递公司代号
	private String provinceId;        //省份ID
	private String province;          //省份名称
	private String cityId;            //城市ID
	private String city;              //城市名
	private String areaId;            //市县ID
	private String area;              //市县名
	private String townId;            //区镇ID
	private String town;              //区镇
	private String officeNumber;      //办公电话
	private String subExpressName;    //子公司名称
	private String subExpressAddress; //子公司名称
	private String mobile;            //移动电话
	private String deliverAreas;      //配送地点
	private String nonDeliverAreas;   //不配送地点
    private Date createTime;          //创建时间
    private Date lastUpdateTime;      //上次更新世间
    private Integer status;               //状态,0/1  默认 1
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
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTownId() {
		return townId;
	}
	public void setTownId(String townId) {
		this.townId = townId;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getOfficeNumber() {
		return officeNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	public String getSubExpressName() {
		return subExpressName;
	}
	public void setSubExpressName(String subExpressName) {
		this.subExpressName = subExpressName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDeliverAreas() {
		return deliverAreas;
	}
	public void setDeliverAreas(String deliverAreas) {
		this.deliverAreas = deliverAreas;
	}
	public String getNonDeliverAreas() {
		return nonDeliverAreas;
	}
	public void setNonDeliverAreas(String nonDeliverAreas) {
		this.nonDeliverAreas = nonDeliverAreas;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getSubExpressAddress() {
		return subExpressAddress;
	}
	public void setSubExpressAddress(String subExpressAddress) {
		this.subExpressAddress = subExpressAddress;
	}
	
	
    
}
/*
DROP TABLE IF EXISTS `deliver_areas`;
CREATE TABLE `deliver_areas` (
  `ID` INT(11) unsigned NOT NULL AUTO_INCREMENT,
  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
  `CITYID` varchar(20) DEFAULT NULL COMMENT '城市ID',
  `CITY` varchar(100) DEFAULT NULL COMMENT '城市名',
  `AREAID` varchar(20) DEFAULT NULL COMMENT '市县id',
  `AREA` varchar(100) DEFAULT NULL COMMENT '市县名',
  `TOWNID` VARCHAR(20) NULL DEFAULT NULL COMMENT '区镇ID',
  `TOWN` VARCHAR(100) NULL DEFAULT NULL COMMENT '区镇',
  `OFFICENUMBER` VARCHAR(20) NULL DEFAULT NULL COMMENT '办公电话',
  `SUBEXPRESSNAME` VARCHAR(200) NULL DEFAULT NULL COMMENT '子公司名称',
  `MOBILE` VARCHAR(20) NULL DEFAULT NULL COMMENT '移动电话',
  `DELIVERAREAS` VARCHAR(1000) NULL DEFAULT NULL COMMENT '配送地点',
  `NONDELIVERAREAS` VARCHAR(1000) NULL DEFAULT NULL COMMENT '不派送地点',
  `CREATETIME` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `LASTUPDATETIME` TIMESTAMP NULL DEFAULT NULL COMMENT '上次更新时间',
  `STATUS` INT(11) DEFAULT '1' COMMENT '状态,0/1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递公司派送范围明细';
*/
