package com.brilliance.po;
/**
 * 
 * @author mx801343
 * 城市信息
 */
public class CitiesInfo {
	private Integer id;       //ID
	private String cityId;    //城市ID
	private String city;      //城市名称
	private String provinceId;//省ID，逻辑外键
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
    
}
/*
DROP TABLE IF EXISTS `cities_info`;
CREATE TABLE `cities_info` (
  `ID` Integer(11) NOT NULL auto_increment,
  `CITYID` varchar(20) NOT NULL,
  `CITY` varchar(50) NOT NULL,
  `PROVINCEID` varchar(20) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='行政区域地州市信息表' AUTO_INCREMENT=346;
*/