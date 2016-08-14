/**
 * ============================================================
 * File : ExpressDeliveryInfo.java
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
 * @Description : 快递数据派送省份信息 
 * @History		:
 *
 ******************************************************************************/
public class ExpressDeliverInfo {
	private Integer id;          //ID
	private String deliverCode; //编码ID，便于被其他表引用
    private String from;         //始发地
    private String to;           //目的地
    private Date createTime;     //创建时间
    private Date lastUpdateTime; //上次更新时间
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
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
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
}
/*
DROP TABLE IF EXISTS `express_deliver_info`;
CREATE TABLE `express_deliver_info` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DELIVERCODE` VARCHAR(20) NOT NULL COMMENT '编码id，便于被其他表引用',
  `FROM` VARCHAR(50) NOT NULL COMMENT '始发地',
  `TO` VARCHAR(50) NOT NULL COMMENT '目的地',
  `CREATETIME` TIMESTAMP NOT NULL COMMENT '创建时间',
  `LASTUPDATETIME` TIMESTAMP NOT NULL COMMENT '上次更新时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='快递数据派送省份信息';
*/