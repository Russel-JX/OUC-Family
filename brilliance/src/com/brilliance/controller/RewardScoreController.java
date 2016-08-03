package com.brilliance.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brilliance.base.BaseController;
import com.brilliance.base.ControllerReturns;
import com.brilliance.service.RewardScoreService;
import com.brilliance.utils.Tools;

@Controller
@Scope("request")
public class RewardScoreController extends BaseController{
	
	@Resource
	private RewardScoreService rewardScoreService;
	
	@RequestMapping(value = "/rewardScore/getUserRewardInfo", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String getUserRewardInfo(HttpServletRequest request) {
		ControllerReturns returns = new ControllerReturns();
		String userId = request.getParameter("userId");
		try{
			returns = rewardScoreService.getUserRewardInfo(userId);
		}catch(Exception e){
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
}
