/**
 * ============================================================
 * File : ExpressDeliveryDetail.java
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
 * @Description : 收费标准及送达天数基本信息表
 * @History		:
 *
 ******************************************************************************/
public class ExpressDeliverDetail {
	private Integer id;           //ID
	private String deliverCode;  //编码id，从express_deliver_info获取
	private String expressCode;   //快递公司ID
	private String charge;        //费用
	private Integer deliverDays;      //送达天数
	private String desc;           //描述，deliverCode地址对应描述
	private Date createTime;      //创建时间
	private Date lastUpdateTime;  //上次更新世间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeliverCode() {
		return deliverCode;
	}
	public void setDeliverCode(String deliverCode) {
		this.deliverCode = deliverCode;
	}
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public Integer getDeliverDays() {
		return deliverDays;
	}
	public void setDeliverDays(Integer deliverDays) {
		this.deliverDays = deliverDays;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
/*
DROP TABLE IF EXISTS `express_deliver_detail`;
CREATE TABLE `express_deliver_detail` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DELIVERCODE` VARCHAR(20) NOT NULL COMMENT '编码id，便于被其他表引用',
  `EXPRESSCODE` VARCHAR(20) NOT NULL COMMENT '快递公司ID',
  `CHARGE` VARCHAR(50) NOT NULL COMMENT '费用',
  `DELIVERDAYS` INT NOT NULL COMMENT '送达天数',
  `CREATETIME` TIMESTAMP NOT NULL COMMENT '创建时间',
  `LASTUPDATETIME` TIMESTAMP NOT NULL COMMENT '上次更新时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='收费标准及送达天数基本信息表';
*/
