/**
 * 
 */
package com.brilliance.po;

import java.util.Date;

/**
 * @author Russel
 * 
 */
public class DeliverAddressInfo {	//收件地址信息
	private Integer id;             //ID
	private String expressCode;     //快递公司代号
	private String userId;          //用户ID
	private String deliverName;     //收件人姓名
	private String companyName;     //公司/个人名称
	private String province;        //省份
	private String city;          	//城市
	private String area;          	//地区
	private String streetName;      //街道名
	private String mobile;          //手机号码
	private String telephone;       //固定电话
	private String tailAddress;     //末尾地址信息
	private String addressDetail;   //完整地址信息
	private Date createTime;        //创建时间
	private Date lastUpdateTime;    //最后更新时间
	private Integer status;             //0（不可用）/1(可用)
	
	public DeliverAddressInfo(){
		
	}

	

	public DeliverAddressInfo(Integer id,String expressCode, String userId, String deliverName,
			String companyName, String province, String city, String area,
			String streetName, String mobile, String telephone, String tailAddress,
			String addressDetail, Date createTime, Date lastUpdateTime,
			Integer status) {
		this.id = id;
		this.expressCode = expressCode;
		this.userId = userId;
		this.deliverName = deliverName;
		this.companyName = companyName;
		this.province = province;
		this.city = city;
		this.area = area;
		this.streetName = streetName;
		this.mobile = mobile;
		this.telephone = telephone;
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

	

	public String getDeliverName() {
		return deliverName;
	}



	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}



	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
CREATE TABLE `POST_ADDRESS_INFO` (
  `ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USERID` VARCHAR(20) DEFAULT NULL COMMENT '用户ID',
  `POSTNAME` VARCHAR(50) DEFAULT NULL COMMENT '发件人姓名',
  `COMPANYNAME` VARCHAR(200) DEFAULT NULL COMMENT '公司/个人名称',
  `PROVINCE` VARCHAR(20) DEFAULT NULL COMMENT '省份',
  `CITY` VARCHAR(20) DEFAULT NULL COMMENT '城市',
  `AREA` VARCHAR(20) DEFAULT NULL COMMENT '地区',
  `STREETNAME` VARCHAR(100) DEFAULT NULL COMMENT '街道名',
  `MOBILE` VARCHAR(20) DEFAULT NULL COMMENT '手机号码',
  `TAILADDRESS` VARCHAR(200) DEFAULT NULL COMMENT '末尾地址信息',
  `ADDRESSDETAIL` VARCHAR(500) DEFAULT NULL COMMENT '完整的地址信息',
  `CREATETIME` DATETIME DEFAULT NULL,
  `LASTUPDATETIME` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `STATUS` INT(11) DEFAULT '1' COMMENT '0（不可用）/1(可用)',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='发件地址信息表';
*/