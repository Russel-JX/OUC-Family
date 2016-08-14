/**
 * 
 */
package com.brilliance.po;

import java.util.Date;

/**
 * @author mx801343
 * 
 */
public class UserAddressInfo {
	private Integer id;             //ID
	private String userId;          //用户ID
	private String fullAddressCode; //完整地址编码串
	private String tailAddress;     //末尾地址信息
	private String addressDetail;   //完整地址信息
	private Date createTime;        //创建时间
	private Date lastUpdateTime;    //最后更新时间
	private Integer status;             //0（不可用）/1(可用)
	
	public UserAddressInfo(){
		
	}

	public UserAddressInfo(String userId, String fullAddressCode,
			String tailAddress, String addressDetail, Date createTime,
			Date lastUpdateTime, Integer status) {
		this.userId = userId;
		this.fullAddressCode = fullAddressCode;
		this.tailAddress = tailAddress;
		this.addressDetail = addressDetail;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getFullAddressCode() {
		return fullAddressCode;
	}

	public void setFullAddressCode(String fullAddressCode) {
		this.fullAddressCode = fullAddressCode;
	}

	public String getTailAddress() {
		return tailAddress;
	}

	public void setTailAddress(String tailAddress) {
		this.tailAddress = tailAddress;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
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

}
/*
CREATE TABLE `user_address_info` (
		  `ID` Integer(11) NOT NULL,
		  `USERID` varchar(20) DEFAULT NULL COMMENT '用户id',
		  `Fulladdresscode` varchar(200) DEFAULT NULL COMMENT '完整地址编码串',
		  `Tailaddress` varchar(200) DEFAULT NULL COMMENT '末尾地址信息',
		  `Addressdetail` varchar(20) DEFAULT NULL COMMENT '完整的地址信息',
		  `CREATETIME` timestamp NULL DEFAULT NULL,
		  `LASTUPDATETIME` timestamp NULL DEFAULT NULL,
		  `STATUS` Integer(11) DEFAULT '1' COMMENT '0（不可用）/1(可用)',
		  PRIMARY KEY (`ID`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址';
*/