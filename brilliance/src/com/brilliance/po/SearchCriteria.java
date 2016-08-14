package com.brilliance.po;

import java.util.Date;

/**
 * 统一查询条件的公共类(可以自行条件字段)
 * @author Flash
 *
 */
public class SearchCriteria {
	private String userId;
	private String addressInfo;
	private String addressId;
	private Integer currentPage;
	private String hotName;
	private String exressName;
	private String expressCode;
	private String provinceId;
	private String cityId;
	private String areaId;
	private Date date;
	//一个查询关键字，多条件搜索
	private String multipleCriterias;
	private boolean isLimitaion = true;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddressInfo() {
		return addressInfo;
	}
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public String getExressName() {
		return exressName;
	}
	public void setExressName(String exressName) {
		this.exressName = exressName;
	}
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHotName() {
		return hotName;
	}
	public void setHotName(String hotName) {
		this.hotName = hotName;
	}
	
	public String getMultipleCriterias() {
		return multipleCriterias;
	}
	public void setMultipleCriterias(String multipleCriterias) {
		this.multipleCriterias = multipleCriterias;
	}
	public boolean isLimitaion() {
		return isLimitaion;
	}
	public void setLimitaion(boolean isLimitaion) {
		this.isLimitaion = isLimitaion;
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
	
	

}
