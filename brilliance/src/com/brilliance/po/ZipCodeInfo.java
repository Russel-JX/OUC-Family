package com.brilliance.po;

public class ZipCodeInfo {
	private Integer id;
	private String areaId;
	private String zip;
	private String code;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
/*
DROP TABLE IF EXISTS `zipcode_info`;
CREATE TABLE `zipcode_info` (
  `ID` Integer(11) NOT NULL auto_increment,
  `AREAID` varchar(20) NOT NULL,
  `ZIP` varchar(20) NOT NULL,
  `CODE` varchar(20) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=3145;
*/