package com.brilliance.po;
/**
 * 
 * @author mx801343
 * 省份信息
 */
public class ProvincesInfo {
	private Integer id;       //ID
	private String provinceId;//省份编号
	private String province;  //省份名称
	
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
}
/*
DROP TABLE IF EXISTS `provinces_info`;
CREATE TABLE `provinces_info` (
  `ID` Integer(11) NOT NULL auto_increment,
  `PROVINCEID` varchar(20) NOT NULL,
  `PROVINCE` varchar(50) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='省份信息表' AUTO_INCREMENT=35;
*/