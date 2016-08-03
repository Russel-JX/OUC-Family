package com.brilliance.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="URL_INFO")
public class URLInfo {
	private Integer id;
	private String userId;//推荐人ID
	private String url;//推荐的URL
	private String type;//link类型1.推荐2.找回密码3.其他
	private Date startTime;//开始时间
	private Date expireTime;//推荐截止时间
	
	public URLInfo() {
	}
	
	

	public URLInfo(Integer id, String userId, String url, String type,
			Date startTime, Date expireTime) {
		this.id = id;
		this.userId = userId;
		this.url = url;
		this.type = type;
		this.startTime = startTime;
		this.expireTime = expireTime;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public Date getStartTime() {
		return startTime;
	}



	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	public Date getExpireTime() {
		return expireTime;
	}



	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	
}
/*
DROP TABLE IF EXISTS `URL_INFO`;
CREATE TABLE `URL_INFO` (
  `ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USERID` VARCHAR(20) DEFAULT NULL COMMENT '推荐人ID',
  `URL` VARCHAR(500) DEFAULT NULL COMMENT '推荐的URL',
  `STARTTIME` DATETIME DEFAULT NULL COMMENT '推荐开始时间',
  `EXPIRETIME` DATETIME DEFAULT NULL COMMENT '推荐截止时间',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='URL信息表';
*/