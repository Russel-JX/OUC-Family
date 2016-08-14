package com.igate.izone.dao;

import com.igate.izone.dto.ActivityDTO;

public interface ActivityDaoIntf {
	ActivityDTO findActivityById(Integer id);
	int createNewActivity(ActivityDTO activityDTO);
	int createNewActivitySharedTo(int activityID,String[] sharedTo);
	int deleteActivityById(Integer id);
	int deleteActivityByBatch(Integer[] ids);
	int updateActivity(ActivityDTO activityDTO);
}
