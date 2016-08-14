package com.brilliance.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="RECOMMEND_INFO")
public class RecommendInfo {
	private Integer id;
	private String userId;//推荐人ID
	private String nomineeId;//被推荐人ID
	private Integer source;//推荐来源,1,新浪微博，2,qq空间3,其他
	private Date completeDate;//推荐完成时间
	public RecommendInfo() {
	}
	public RecommendInfo(Integer id, String userId, String nomineeId,Integer source,
			Date completeDate) {
		this.id = id;
		this.userId = userId;
		this.nomineeId = nomineeId;
		this.completeDate = completeDate;
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
	public String getNomineeId() {
		return nomineeId;
	}
	public void setNomineeId(String nomineeId) {
		this.nomineeId = nomineeId;
	}
	
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	
	
}
/*
DROP TABLE IF EXISTS `URL_INFO`;
CREATE TABLE `RECOMMEND_INFO` (
  `ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USERID` VARCHAR(20) DEFAULT NULL COMMENT '推荐人ID',
  `NOMINEEID` VARCHAR(20) DEFAULT NULL COMMENT '被推荐人ID',
  `COMPLETETIME` DATETIME DEFAULT NULL COMMENT '推荐完成时间',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='推荐信息表';
*/