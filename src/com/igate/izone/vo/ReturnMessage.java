package com.igate.izone.vo;

public class ReturnMessage {
	private String type;
	private Object value;
	public ReturnMessage(){}
	
	public ReturnMessage(String type, Object value) {
		super();
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
