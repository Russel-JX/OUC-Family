package com.igate.izone.dto;

import java.util.Date;

/**
 * @author Xun Jiang
 * @description 报告数据传输对象
 */
public class ReportDTO {
	private int id;//PK， 序列自增。
	private String reportName;//报告标题
	private int createdBy;//FK，创建者
	private Date createdDate;//创建时间
	private String reportDescription;//报告描述
	
	public ReportDTO() {
		super();
	}
	
	public ReportDTO(int id, String reportName, int createdBy,
			Date createdDate, String reportDescription) {
		super();
		this.id = id;
		this.reportName = reportName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.reportDescription = reportDescription;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getReportDescription() {
		return reportDescription;
	}
	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}
	

}
