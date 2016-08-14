package com.brilliance.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Russel
 *自定义快递地址临时信息
 */
@Entity(name="customizationTempAddressInfo")
@Table(name="CUSTOMIZATION_TEMP_ADDRESSINFO")
public class CustomizationTempAddressInfo implements Serializable {
	
	private static final long serialVersionUID = 3920807634665964388L;
	
	
	private Integer id;
	private String addressId;//自定义地址ID,作为另外表的外键
	private String expressCode;//快递公司代号

	private String hotName;//关键词,用户自定义快速搜索用
//	private String expressName;//快递公司名称
	private String contact;//联系人
	private String mobile;//手机号码
	private String officeNo;//办公电话
	private String tailAddress;//末尾地址信息
	private String addressDetail;//完整地址信息
	private String createdBy;//创建者ID,导入Excel或进行批量验证操作的管理员
	private String memo;//备注

	private Date createTime;
	private Date lastUpdateTime;
	
	public CustomizationTempAddressInfo() {
		
	}
	
	public CustomizationTempAddressInfo(Integer id, String addressId,
			String expressCode, String hotName, String contact,
			String mobile, String officeNo, String tailAddress,
			String addressDetail, String createdBy, String memo,
			Date createTime, Date lastUpdateTime) {
		this.id = id;
		this.addressId = addressId;
		this.expressCode = expressCode;
		this.hotName = hotName;
		this.contact = contact;
		this.mobile = mobile;
		this.officeNo = officeNo;
		this.tailAddress = tailAddress;
		this.addressDetail = addressDetail;
		this.createdBy = createdBy;
		this.memo = memo;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
	}
	
	@Id 
	@GeneratedValue(strategy=IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public String getHotName() {
		return hotName;
	}
	public void setHotName(String hotName) {
		this.hotName = hotName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOfficeNo() {
		return officeNo;
	}
	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/*CREATE TABLE `CUSTOMIZATION_TEMP_ADDRESSINFO` (
			  `ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
			  `ADDRESSID` VARCHAR(20) DEFAULT NULL COMMENT '自定义地址ID,作为另外表的外键',
			  `EXPRESSCODE` VARCHAR(20) DEFAULT NULL COMMENT '快递公司代号',
			  `HOTNAME` VARCHAR(50) DEFAULT NULL COMMENT '关键词,用户自定义快速搜索用',
			  `CONTACT` VARCHAR(20) DEFAULT NULL COMMENT '联系人',
			  `MOBILE` VARCHAR(20) DEFAULT NULL COMMENT '手机号码',
			  `OFFICENO` VARCHAR(100) DEFAULT NULL COMMENT '办公电话',
			  `TAILADDRESS` VARCHAR(200) DEFAULT NULL COMMENT '末尾地址信息',
			  `ADDRESSDETAIL` VARCHAR(500) DEFAULT NULL COMMENT '完整地址信息',
			  `CREATEDBY` VARCHAR(20) DEFAULT NULL COMMENT '创建者ID,导入Excel或进行批量验证操作的管理员',
			  `MEMO` VARCHAR(200) DEFAULT NULL COMMENT '备注',
			  `CREATETIME` DATETIME DEFAULT NULL,
			  `LASTUPDATETIME` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
			  PRIMARY KEY (`ID`)
			) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='自定义快递地址临时信息';*/
	
}
