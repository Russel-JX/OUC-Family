package com.brilliance.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="AddressReviewInfo")
@Table(name="ADDRESS_REVIEW_INFO")
public class AddressReviewInfo implements Serializable {

	private static final long serialVersionUID = -5767567859312303393L;
	
	private Integer id;
	private String suggestId;
	private String reviewer;
	private Date reviewTime;
	
	private String status;
	private String reviewMemo;

	private Date lastUpdateTime;
	
	public AddressReviewInfo() {
		
	}
	
	public AddressReviewInfo(Integer id, String suggestId,
			String reviewer, Date reviewTime, String status,
			String reviewMemo,  Date lastUpdateTime) {
		this.id = id;
		this.suggestId = suggestId;
		this.reviewer = reviewer;
		this.reviewTime = reviewTime;
		this.status = status;
		this.reviewMemo = reviewMemo;
		this.lastUpdateTime = lastUpdateTime;
	}
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSuggestId() {
		return suggestId;
	}
	public void setSuggestId(String suggestId) {
		this.suggestId = suggestId;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReviewMemo() {
		return reviewMemo;
	}
	public void setReviewMemo(String reviewMemo) {
		this.reviewMemo = reviewMemo;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	
	/*
CREATE TABLE `ADDRESS_REVIEW_INFO` (
  `ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `SUGGESTID` VARCHAR(20) DEFAULT NULL COMMENT '用户建议地址ID',
  `REVIEWER` VARCHAR(20) DEFAULT NULL COMMENT '审核人ID',
  `REVIEWTIME` DATETIME DEFAULT NULL COMMENT '审核时间',
  `STATUS` Integer(11) DEFAULT NULL COMMENT '审核状态:1.决绝;2.通过;',
  `REVIEWMEMO` VARCHAR(200) DEFAULT NULL COMMENT '审核理由',
  `LASTUPDATETIME` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='用户推荐快递地址信息审核表';
	 */
	
}
