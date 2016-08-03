package com.brilliance.po;

public class InvalidRow {
	public Integer rowIndex;
	public Object rowInfo;
	public String problemDesc;
	
	public InvalidRow() {
		super();
	}

	public InvalidRow(Integer rowIndex, Object rowInfo, String problemDesc) {
		super();
		this.rowIndex = rowIndex;
		this.rowInfo = rowInfo;
		this.problemDesc = problemDesc;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	public Object getRowInfo() {
		return rowInfo;
	}

	public void setRowInfo(Object rowInfo) {
		this.rowInfo = rowInfo;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}
	

}
