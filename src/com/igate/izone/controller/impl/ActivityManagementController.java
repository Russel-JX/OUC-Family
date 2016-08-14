package com.igate.izone.controller.impl;

import java.sql.Date;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igate.izone.dto.ActivityDTO;
import com.igate.izone.service.ActivityServiceIntf;
/**
 * @author Yicheng Zhou
 *
 */
@Controller
@RequestMapping(value="/activity")
public class ActivityManagementController{

	@Resource
	private ActivityServiceIntf activityService;
	
	@RequestMapping(value="/createNewActivity",method=RequestMethod.POST)
	public @ResponseBody String createNewActivity(HttpServletRequest request) {
		String activityName = request.getParameter("activityName");
		String activityType = request.getParameter("activityType");
		String description = request.getParameter("description");
		String memberIds = request.getParameter("memberIds");
		String[] sharedTo = memberIds.split(",");
		ActivityDTO activityDTO = new ActivityDTO();
		activityDTO.setActivityName(activityName);
		activityDTO.setActivityType(Integer.parseInt(activityType));
		activityDTO.setDescription(description);
		activityDTO.setCreatedDate(new Date(new java.util.Date().getTime()));
		activityDTO.setCreator("yz816343");//这里应该从session中获得employee的empId值
		System.out.println(activityDTO.toString());
		System.out.println(Arrays.toString(sharedTo));
//		int activityID = activityService.createNewActivity(activityDTO);
//		int updateLines = activityService.createNewActivitySharedTo(activityID, sharedTo);
		return "success";
	}

	
}
