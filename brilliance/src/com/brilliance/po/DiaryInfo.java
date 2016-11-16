package com.brilliance.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="DiaryInfo")
@Table(name="DIARY_INFO")
/**
 * @author Jiang Xun
 * @version 1.0
 * createDate:2014-11-29
 * 戴宗日记实体类
 */
public class DiaryInfo implements Serializable {

	private static final long serialVersionUID = 1025306088455640722L;
	
	private Integer id;
	private String diaryType;
	private String deleteFlag;
	private String diaryTitle;
	private String source;
	private String author;
	private Date diaryDate;
	private String content;
	private String love;
	private String dislove;
	private String memo;
	private String createdBy;
	private String creatorName;
	private Date createTime;
	private String lastUpdatedBy;
	private String lastUpdaterName;
	private Date lastUpdateTime;
	
	private Date diaryStartDate;
	private Date diaryEndDate;
	
	public DiaryInfo() {
	}
	public DiaryInfo(
			String diaryTitle, String author, String content,Date diaryDate,
			Date createTime,Date lastUpdateTime) {
		this.diaryTitle = diaryTitle;
		this.author = author;
		this.diaryDate = diaryDate;
		this.content = content;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public DiaryInfo(Integer id, String diaryType, String deleteFlag,
			String diaryTitle, String source, String author,Date diaryDate, String content,String love,String dislove,
			String memo, String createdBy, String creatorName, Date createTime,String lastUpdatedBy,String lastUpdaterName, Date lastUpdateTime) {
		this.id = id;
		this.diaryType = diaryType;
		this.deleteFlag = deleteFlag;
		this.diaryTitle = diaryTitle;
		this.source = source;
		this.author = author;
		this.diaryDate = diaryDate;
		this.content = content;
		this.love = love;
		this.dislove = dislove;
		this.memo = memo;
		this.createdBy = createdBy;
		this.creatorName = creatorName;
		this.createTime = createTime;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdaterName = lastUpdaterName;
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
	@Transient
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	@Transient
	public String getLastUpdaterName() {
		return lastUpdaterName;
	}

	public void setLastUpdaterName(String lastUpdaterName) {
		this.lastUpdaterName = lastUpdaterName;
	}

	public String getDiaryType() {
		return diaryType;
	}
	public void setDiaryType(String diaryType) {
		this.diaryType = diaryType;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getDiaryTitle() {
		return diaryTitle;
	}
	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Date getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getLove() {
		return love;
	}

	public void setLove(String love) {
		this.love = love;
	}

	public String getDislove() {
		return dislove;
	}

	public void setDislove(String dislove) {
		this.dislove = dislove;
	}

	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
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
	@Transient
	public Date getDiaryStartDate() {
		return diaryStartDate;
	}

	public void setDiaryStartDate(Date diaryStartDate) {
		this.diaryStartDate = diaryStartDate;
	}
	@Transient
	public Date getDiaryEndDate() {
		return diaryEndDate;
	}

	public void setDiaryEndDate(Date diaryEndDate) {
		this.diaryEndDate = diaryEndDate;
	}
	
	
	/* 
CREATE TABLE `DIARY_INFO` (
  `ID` Integer(11) unsigned NOT NULL AUTO_INCREMENT,
  `DIARYTYPE` Integer(11) DEFAULT NULL COMMENT '日记类型:0.日记;1.新闻;2.漫画;3.其他',
  `DELETEFLAG` Integer(11) DEFAULT NULL COMMENT '记录是否激活:0.无效;1.有效;',
  `DIARYTITLE` varchar(50) DEFAULT NULL COMMENT '标题',
  `SOURCE` varchar(50) DEFAULT NULL COMMENT '来源',
  `AUTHOR` varchar(20) DEFAULT NULL COMMENT '作者',
  `DIARYDATE` DATETIME DEFAULT NULL COMMENT '日记日期',
  `CONTENT` text DEFAULT NULL COMMENT '内容',
  `LOVE` Integer(11) DEFAULT NULL COMMENT '喜欢（次数）',
  `DISLOVE` Integer(11) DEFAULT NULL COMMENT '不喜欢',
  `MEMO` VARCHAR(200) DEFAULT NULL COMMENT '备注或描述',
  `CREATEDBY` VARCHAR(20) DEFAULT NULL COMMENT '创建者ID',
  `CREATETIME` DATETIME DEFAULT NULL COMMENT '创建时间',
  `LASTUPDATEDBY` VARCHAR(20) DEFAULT NULL COMMENT '修改者ID',
  `LASTUPDATETIME` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='戴宗日记';
	 */
	
}
