package com.igate.izone.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.igate.izone.dao.ActivityDaoIntf;
import com.igate.izone.dto.ActivityDTO;
import com.igate.izone.service.ActivityServiceIntf;

@Service
public class ActivityServiceImpl implements ActivityServiceIntf {

	@Resource
	private ActivityDaoIntf activityDao;
	
	@Override
	public int createNewActivity(ActivityDTO activityDTO) {
		int updateLines = 0;
		activityDao.createNewActivity(activityDTO);
		return 0;
	}

	@Override
	public int createNewActivitySharedTo(int activityID,String[] sharedTo) {
		int key = activityDao.createNewActivitySharedTo(activityID,sharedTo);
		return key;
	}

}
