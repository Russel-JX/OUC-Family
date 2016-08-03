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

@Entity(name="customizationAddressInfo")
@Table(name="CUSTOMIZATION_ADDRESSINFO")
public class CustomizationAddressInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3894906493394707002L;
	
	private Integer id;
	private String addressId;
	private String parentId;
	private String expressCode;
	
	private String archiveFlag;
	private String dataType;

	private String hotName;
	
	private String contactName;
	private String mobile;
	private String officeNo;
	private String createdBy;
	private String memo;
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
	private String expressName;
	private String logoUrl;
	
	//private FavouriteAddress favouriteAddress;
	public boolean equals(Object other){
    	if(this==other){
    		return true;
    	}
    	if(other==null){
    		return false;
    	}
    	if(!(other instanceof CustomizationAddressInfo)){
    		return false;
    	}
    	
    	final CustomizationAddressInfo addr = (CustomizationAddressInfo)other;
    	
    	if(!(this.getAddressId().equals(addr.getAddressId())
    			&& this.getHotName().equals(addr.getHotName())
    			&& this.getExpressCode().equals(addr.getExpressCode())
    			&& this.getMobile().equals(addr.getMobile())
    			&& this.getContactName().equals(addr.getContactName())
    			&& this.getProvinceId().equals(addr.getProvinceId())
    			&& this.getCityId().equals(addr.getCityId())
    			&& this.getAreaId().equals(addr.getAreaId())
    			&& this.getAddressDetail().equals(addr.getAddressDetail()))){
    		return false;
    	}
    	
		return true;
    }
	
	public String getAddressId() {
		return addressId;
	}
	
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	@Id 
	@GeneratedValue(strategy=IDENTITY)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	@Transient
	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	@Transient
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getArchiveFlag() {
		return archiveFlag;
	}

	public void setArchiveFlag(String archiveFlag) {
		this.archiveFlag = archiveFlag;
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

	
	
	/*@Transient
	public FavouriteAddress getFavouriteAddress() {
		return favouriteAddress;
	}
	public void setFavouriteAddress(FavouriteAddress favouriteAddress) {
		this.favouriteAddress = favouriteAddress;
	}*/
	
	/*
CREATE TABLE `CUSTOMIZATION_ADDRESSINFO` (
  `ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ADDRESSID` VARCHAR(20) DEFAULT NULL COMMENT '�Զ����ַID',
  `PARENTID` VARCHAR(20) DEFAULT NULL COMMENT '�������ŵ��ַID',
  `EXPRESSCODE` VARCHAR(20) DEFAULT NULL COMMENT '��ݹ�˾���',
  `HOTNAME` VARCHAR(50) DEFAULT NULL COMMENT '�ؼ��',
  `CONTACT` VARCHAR(20) DEFAULT NULL COMMENT '��ϵ��',
  `MOBILE` VARCHAR(20) DEFAULT NULL COMMENT '�ֻ����',
  `OFFICENO` VARCHAR(100) DEFAULT NULL COMMENT '�칫�绰',
  `PROVINCEID` VARCHAR(20) DEFAULT NULL COMMENT 'ʡ��ID',
  `CITYID` VARCHAR(20) DEFAULT NULL COMMENT '����ID',
  `AREAID` VARCHAR(20) DEFAULT NULL COMMENT '����ID',
  `SOURCE` Integer DEFAULT NULL COMMENT '���Դ��0.���Զ�;1.�ֻ��',
  `DATATYPE` Integer DEFAULT NULL COMMENT '������ͣ�0.�ŵ��ַ��Ϣ;1.���ͷ�Χ��ַ��Ϣ',
  `ARCHIVEFLAG` Integer DEFAULT NULL COMMENT '�Ƿ��ѹ鵵��0.δ�鵵;1.�ѹ鵵',
  `TAILADDRESS` VARCHAR(200) DEFAULT NULL COMMENT 'ĩβ��ַ��Ϣ',
  `ADDRESSDETAIL` VARCHAR(500) DEFAULT NULL COMMENT '�����ַ��Ϣ',
  `LONGITUDE` VARCHAR(500) DEFAULT NULL COMMENT '��ͼ�������ֵ',
  `LATITUDE` VARCHAR(500) DEFAULT NULL COMMENT '��ͼγ�����ֵ',
  `CREATEDBY` VARCHAR(20) DEFAULT NULL COMMENT '������ID',
  `MEMO` VARCHAR(200) DEFAULT NULL COMMENT '��ע',
  `CREATETIME` DATETIME DEFAULT NULL,
  `LASTUPDATETIME` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='�Զ����ݵ�ַ��Ϣ';
	 */
	
}
