/**
 * ============================================================
 * File : AdminInfo.java
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

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/*******************************************************************************
 *
 * @Author 		: Michael
 * @Version 	: 1.0
 * @Date Created: 2014-3-12
 * @Date Modified : 2014-09-05
 * @Modified By : Flash
 * @Contact 	:
 * @Description : added accountId field
 * @History		:
 *
 ******************************************************************************/
@Entity(name="adminInfo")
@Table(name="ADMIN_INFO")
public class AdminInfo {
	private Integer id;           //ID
	private String accountId;
	private String name;          //名字
	private String password;      //密码
	private Date createTime;      //创建时间
	private Date lastUpdateTime;  //最后更新时间
	private String updateBy;         //操作人
	private Integer status;           //状态
	
	@Id 
	@GeneratedValue(strategy=IDENTITY)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
}
/*
CREATE TABLE `admin_info` (
		  `ID` Integer(11) unsigned NOT NULL AUTO_INCREMENT,
		  `NAME` varchar(50) DEFAULT NULL COMMENT '名字',
		  `ACCOUNTID` varchar(10) DEFAULT NULL COMMENT '账号id',
		  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '密码',
		  `CREATETIME` timestamp NULL DEFAULT NULL,
		  `LASTUPDATETIME` timestamp NULL DEFAULT NULL,
		  `UPDATEBY` Integer(11) DEFAULT NULL COMMENT '操作人',
		  `STATUS` Integer(11) DEFAULT '1' COMMENT '状态,0/1',
		  PRIMARY KEY (`ID`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 管理员信息表';
*/