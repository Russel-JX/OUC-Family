package com.igate.izone.dto;

import java.sql.Date;
/**
 * 
 * @author Yicheng Zhou
 *
 */
public class ActivityDTO {
	private int id;
	private String activityName;
	private int activityType;
	private String creator;
	private Date createdDate;
	private String description;
	
	public ActivityDTO(){}
	
	public ActivityDTO(int id, String activityName, Integer activityType,String creator,
			Date createdDate, String description) {
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getActivityType() {
		return activityType;
	}

	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}

	@Override
	public String toString() {
		return "ActivityDTO [id=" + id + ", activityName=" + activityName
				+ ", activityType=" + activityType + ", creator=" + creator
				+ ", createdDate=" + createdDate + ", description="
				+ description + "]"+"\n";
	}

	
}
