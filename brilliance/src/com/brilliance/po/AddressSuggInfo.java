package com.brilliance.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="AddressSuggInfo")
@Table(name="ADDRESS_SUGG_INFO")
public class AddressSuggInfo implements Serializable {

	private static final long serialVersionUID = 2352741951450579123L;
	
	private Integer id;
	private String addressId;
	private String parentId;
	private String expressCode;
	
	

	private String hotName;
	
	private String contactName;
	private String mobile;
	private String officeNo;
	private String createdBy;
	private String memo;
	
	private String status;//审核状态:0.未开始;1.决绝;2.通过;
	private String deleteFlag;//记录是否激活:0.无效;1.有效;
	private String dataType;
	private String source;
	
	private String provinceId;
	private String cityId;
	private String areaId;
	private String tailAddress;
	private String addressDetail;
	private Date createTime;
	private Date lastUpdateTime;
	private String longitude;
	private String latitude;
	
	private String expressName;//快递公司名称
	private String creatorName;//用户名
	private String creatorMobile;//用户手机
	private String reviewer;//审核人id
	private String reviewerName;//审核人姓名
	private String reviewMemo;//审核理由
	private String logoUrl;
	
	public AddressSuggInfo() {
		
	}
	
	public AddressSuggInfo(Integer id, String addressId, String parentId,
			String expressCode, String hotName, String contactName,
			String mobile, String officeNo, String createdBy, String memo,
			String status, String deleteFlag, String dataType, String source,
			String provinceId, String cityId, String areaId,
			String tailAddress, String addressDetail, Date createTime,
			Date lastUpdateTime, String longitude, String latitude,
			String expressName, String creatorName, String creatorMobile, String reviewer,String reviewerName,String reviewMemo) {
		this.id = id;
		this.addressId = addressId;
		this.parentId = parentId;
		this.expressCode = expressCode;
		this.hotName = hotName;
		this.contactName = contactName;
		this.mobile = mobile;
		this.officeNo = officeNo;
		this.createdBy = createdBy;
		this.memo = memo;
		this.status = status;
		this.deleteFlag = deleteFlag;
		this.dataType = dataType;
		this.source = source;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.areaId = areaId;
		this.tailAddress = tailAddress;
		this.addressDetail = addressDetail;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.expressName = expressName;
		this.creatorName = creatorName;
		this.creatorMobile = creatorMobile;
		this.reviewer = reviewer;
		this.reviewerName = reviewerName;
		this.reviewMemo = reviewMemo;
	}



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
	public String getHotName() {
		return hotName;
	}
	public void setHotName(String hotName) {
		this.hotName = hotName;
	}
	@Column(name = "contact")
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	@Transient
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@Transient
	public String getCreatorMobile() {
		return creatorMobile;
	}

	public void setCreatorMobile(String creatorMobile) {
		this.creatorMobile = creatorMobile;
	}

	@Transient
	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	@Transient
	public String getReviewMemo() {
		return reviewMemo;
	}

	public void setReviewMemo(String reviewMemo) {
		this.reviewMemo = reviewMemo;
	}

	@Transient
	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	@Transient
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	
	
	/*
CREATE TABLE `ADDRESS_SUGG_INFO` (
  `ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ADDRESSID` VARCHAR(20) DEFAULT NULL COMMENT '自定义地址ID',
  `PARENTID` VARCHAR(20) DEFAULT NULL COMMENT '所属的门店地址ID',
  `EXPRESSCODE` VARCHAR(20) DEFAULT NULL COMMENT '快递公司代号',
  `HOTNAME` VARCHAR(50) DEFAULT NULL COMMENT '关键词',
  `CONTACT` VARCHAR(20) DEFAULT NULL COMMENT '联系人',
  `MOBILE` VARCHAR(20) DEFAULT NULL COMMENT '手机号码',
  `OFFICENO` VARCHAR(100) DEFAULT NULL COMMENT '办公电话',
  `PROVINCEID` VARCHAR(20) DEFAULT NULL COMMENT '省份ID',
  `CITYID` VARCHAR(20) DEFAULT NULL COMMENT '城市ID',
  `AREAID` VARCHAR(20) DEFAULT NULL COMMENT '区县ID',
  `STATUS` Integer(11) DEFAULT NULL COMMENT '审核状态:0.未开始;1.决绝;2.通过;',
  `DELETEFLAG` Integer(11) DEFAULT NULL COMMENT '记录是否激活:0.无效;1.有效;',
  `SOURCE` INT DEFAULT NULL COMMENT '数据源：1.电脑端;2.手机端;3,数据库导入;4,文件导入',
  `DATATYPE` INT DEFAULT NULL COMMENT '数据类型：1.门店地址信息;2.配送范围地址信息',
  `TAILADDRESS` VARCHAR(200) DEFAULT NULL COMMENT '末尾地址信息',
  `ADDRESSDETAIL` VARCHAR(500) DEFAULT NULL COMMENT '完整地址信息',
  `LONGITUDE` VARCHAR(500) DEFAULT NULL COMMENT '地图经度坐标值',
  `LATITUDE` VARCHAR(500) DEFAULT NULL COMMENT '地图纬度坐标值',
  `CREATEDBY` VARCHAR(20) DEFAULT NULL COMMENT '创建者ID',
  `MEMO` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `CREATETIME` DATETIME DEFAULT NULL,
  `LASTUPDATETIME` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='用户推荐快递地址信息表';
	 */
	
}
