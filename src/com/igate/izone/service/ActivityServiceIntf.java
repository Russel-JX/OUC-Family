package com.igate.izone.service;

import com.igate.izone.dto.ActivityDTO;

public interface ActivityServiceIntf {
	int createNewActivity(ActivityDTO activityDTO);
	int createNewActivitySharedTo(int activityID,String[] sharedTo);
}
