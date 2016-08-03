/**
 * 
 */
package com.brilliance.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mx801343
 *
 */
public class ExpressInfo {
	
	private Integer id;          //ID
	private String name;         //快递公司名称
	private String expressCode;  //快递公司代号
	private String postId;  //快递公司查询代号
	private String logoUrl;  //快递公司图标路径
	private String telephone;    //座机
	private String mobile;       //手机
    private String serviceLine;  //客服电话
    private BigDecimal evaluation;       //评分
    private Date createTime;     //创建时间
    private Date lasetUpdateTime;//最后更新时间
    private Integer updateBy;        //操作人
    private String charge;       //费用
    private String deliverDays;  //快递天数
    private Integer status;          //DEFAULT '1',状态,0/1
    private String subExpressMobile;
    private String subExpressName;
    private String officeNo;
    private String deliverAreas;
    private String nonDeliverAreas;
    private String subExpressAddress;
    
    private String f_provinceId;    //始发地
    private String f_cityId;
    private String f_areaId;
    private String t_provinceId;   //目的地
    private String t_cityId;
    private String t_areaId;
    
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
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getServiceLine() {
		return serviceLine;
	}
	public void setServiceLine(String serviceLine) {
		this.serviceLine = serviceLine;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLasetUpdateTime() {
		return lasetUpdateTime;
	}
	public void setLasetUpdateTime(Date lasetUpdateTime) {
		this.lasetUpdateTime = lasetUpdateTime;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getF_provinceId() {
		return f_provinceId;
	}
	public void setF_provinceId(String f_provinceId) {
		this.f_provinceId = f_provinceId;
	}
	public String getF_cityId() {
		return f_cityId;
	}
	public void setF_cityId(String f_cityId) {
		this.f_cityId = f_cityId;
	}
	public String getF_areaId() {
		return f_areaId;
	}
	public void setF_areaId(String f_areaId) {
		this.f_areaId = f_areaId;
	}
	public String getT_provinceId() {
		return t_provinceId;
	}
	public void setT_provinceId(String t_provinceId) {
		this.t_provinceId = t_provinceId;
	}
	public String getT_cityId() {
		return t_cityId;
	}
	public void setT_cityId(String t_cityId) {
		this.t_cityId = t_cityId;
	}
	public String getT_areaId() {
		return t_areaId;
	}
	public void setT_areaId(String t_areaId) {
		this.t_areaId = t_areaId;
	}
	public BigDecimal getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(BigDecimal evaluation) {
		this.evaluation = evaluation;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getDeliverDays() {
		return deliverDays;
	}
	public void setDeliverDays(String deliverDays) {
		this.deliverDays = deliverDays;
	}
	public String getSubExpressMobile() {
		return subExpressMobile;
	}
	public void setSubExpressMobile(String subExpressMobile) {
		this.subExpressMobile = subExpressMobile;
	}
	public String getSubExpressName() {
		return subExpressName;
	}
	public void setSubExpressName(String subExpressName) {
		this.subExpressName = subExpressName;
	}
	public String getOfficeNo() {
		return officeNo;
	}
	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
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
	public String getSubExpressAddress() {
		return subExpressAddress;
	}
	public void setSubExpressAddress(String subExpressAddress) {
		this.subExpressAddress = subExpressAddress;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
    
}
/*
CREATE TABLE `EXPRESS_INFO` (
  `ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(50) DEFAULT NULL COMMENT '名字',
  `EXPRESSCODE` VARCHAR(20) DEFAULT NULL COMMENT '快递公司代号',
  `POSTID` VARCHAR(20) DEFAULT NULL COMMENT '快递公司查询代号',
  `LOGOURL` VARCHAR(200) DEFAULT NULL COMMENT '快递公司logo地址',
  `TELEPHONE` VARCHAR(20) DEFAULT NULL COMMENT '座机',
  `MOBILE` VARCHAR(20) DEFAULT NULL COMMENT '手机',
  `SERVICELINE` VARCHAR(20) DEFAULT NULL COMMENT '客服电话',
  `EVALUATION` FLOAT DEFAULT NULL COMMENT '评分',
  `CREATETIME` DATETIME DEFAULT NULL,
  `LASTUPDATETIME` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATEBY` VARCHAR(50) DEFAULT NULL COMMENT '操作人',
  `STATUS` INT(11) DEFAULT '1' COMMENT '状态,0/1',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=15 DEFAULT CHARSET=UTF8 COMMENT='快递公司基本信息';

*/