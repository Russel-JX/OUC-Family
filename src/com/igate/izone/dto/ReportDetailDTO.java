package com.igate.izone.dto;

import java.util.Date;

/**
 * @author Xun Jiang
 * @description 报告详细数据传输对象
 */
public class ReportDetailDTO {
	private int id;//PK， 序列自增。
	private String columnName;//列名
	private String columnData;//这一列的所有数据，多个单元格数据用“#”间隔（如：abc#123#哈哈）
	private int columnNumber;//第几列（从1开始）
	private int reportID;//FK，报告ID
	
	public ReportDetailDTO() {
		super();
	}
	
	public ReportDetailDTO(int id, String columnName, String columnData,
			int columnNumber, int reportID) {
		super();
		this.id = id;
		this.columnName = columnName;
		this.columnData = columnData;
		this.columnNumber = columnNumber;
		this.reportID = reportID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnData() {
		return columnData;
	}
	public void setColumnData(String columnData) {
		this.columnData = columnData;
	}
	public int getColumnNumber() {
		return columnNumber;
	}
	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}
	public int getReportID() {
		return reportID;
	}
	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	

}
