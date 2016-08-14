package com.igate.izone.vo;

import java.sql.Date;

public class ActivityRes {
	private int id;
	private String activityName;
	private int activityType;
	private String creator;
	private String createdDate;
	private String description;
	
	public ActivityRes(){}
	
	public ActivityRes(int id, String activityName, int activityType,
			String creator, String createdDate, String description) {
		super();
		this.id = id;
		this.activityName = activityName;
		this.activityType = activityType;
		this.creator = creator;
		this.createdDate = createdDate;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public int getActivityType() {
		return activityType;
	}
	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ActivityRes [id=" + id + ", activityName=" + activityName
				+ ", activityType=" + activityType + ", creator=" + creator
				+ ", createdDate=" + createdDate + ", description="
				+ description + "]";
	}
	
}
