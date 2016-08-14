package com.brilliance.po;

public class AreasInfo {
	private Integer id;
	private String areaId;
	private String area;
	private String cityId;
	
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

}
/*
DROP TABLE IF EXISTS `areas_info`;
CREATE TABLE `areas_info` (
  `ID` Integer(11) NOT NULL auto_increment,
  `AREAID` varchar(20) NOT NULL,
  `AREA` varchar(50) NOT NULL,
  `CITYID` varchar(20) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='行政区域县区信息表' AUTO_INCREMENT=3145;
*/