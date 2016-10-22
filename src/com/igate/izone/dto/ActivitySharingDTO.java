package com.igate.izone.dto;

public class ActivitySharingDTO {
	private int id;
	private int activityID;
	private String sharedTo;
	
	public ActivitySharingDTO(){}
	
	public ActivitySharingDTO(int id, int activityID, String sharedTo) {
		super();
		this.id = id;
		this.activityID = activityID;
		this.sharedTo = sharedTo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActivityID() {
		return activityID;
	}
	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}
	public String getSharedTo() {
		return sharedTo;
	}
	public void setSharedTo(String sharedTo) {
		this.sharedTo = sharedTo;
	}

	@Override
	public String toString() {
		return "ActivitySharingDTO [id=" + id + ", activityID=" + activityID
				+ ", sharedTo=" + sharedTo + "]";
	}
	
	
}
