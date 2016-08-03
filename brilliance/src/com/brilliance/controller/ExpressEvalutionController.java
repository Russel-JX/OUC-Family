package com.brilliance.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brilliance.base.BaseController;
import com.brilliance.base.BriException;
import com.brilliance.po.ExpressEvalution;
import com.brilliance.po.RewardScore;
import com.brilliance.po.UserInfo;
import com.brilliance.service.ExpressEvalutionService;
import com.brilliance.service.RewardScoreService;
import com.brilliance.service.UserInfoService;
import com.brilliance.utils.CacheUtils;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;
@Scope("request")
@Controller
public class ExpressEvalutionController extends BaseController {
	@Resource
	private ExpressEvalutionService expressEvalutionService;
	
	@Resource
	private RewardScoreService rewardScoreService;
	
	@Resource
	private UserInfoService userInfoService;

	@RequestMapping(value = "/evalution/add.json", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String evalution(HttpServletRequest request) {
		try {
			String expressCode = request.getParameter("expressCode");//快递公司编号
			String orderCode = request.getParameter("orderCode");//订单ID
			String userId = request.getParameter("userId");//用户编号
			String extraInfo = request.getParameter("extraInfo");//备注信息
			String score = request.getParameter("score");//评分
			
			System.out.println(expressCode+"|"+orderCode+"|"+userId+"|"+extraInfo+"|"+score);
			//userid为空表示请求来自于移动端
			if(Tools.isEmpty(userId)){
				UserInfo user = CacheUtils.getUserInfo_PC(request);
				userId = user.getUserId();
			}
			
			ExpressEvalution evalution = new ExpressEvalution();
			evalution.setExpressCode(expressCode);
			evalution.setOrderCode(orderCode);
			evalution.setUserId(userId);
			evalution.setScore(score);
			evalution.setExtraInfo(extraInfo);
			
			returns = expressEvalutionService.evalution(evalution);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	
	@RequestMapping(value = "/evalution/addComments_M", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String addComments(HttpServletRequest request) {
		try {
			String expressCode = request.getParameter("expressCode");//快递公司编号
			String addressId = request.getParameter("addressId");//订单ID
			String userId = request.getParameter("userId");//用户编号
			String extraInfo = request.getParameter("extraInfo");//备注信息
			String score = request.getParameter("score");//评分
			
			//System.out.println(expressCode+"|"+addressId+"|"+userId+"|"+extraInfo+"|"+score);
			
			ExpressEvalution evalution = new ExpressEvalution();
			evalution.setExpressCode(expressCode);
			evalution.setAddressId(addressId);
			//evalution.setOrderCode(orderCode);
			evalution.setUserId(userId);
			evalution.setScore(score);
			evalution.setExtraInfo(extraInfo);
			evalution.setCreateTime(Tools.getTime());
			
			returns = expressEvalutionService.saveEvalution(evalution);
			
			RewardScore reward = new RewardScore();
			
			reward.setUserId(userId);
			reward.setScore(Constants.REWARD_DEFAULT_SCORE);
			reward.setRewardType(Constants.REWARD_TYPE_COMMENTS);
			reward.setCreationTime(Tools.getTime());
			rewardScoreService.save(reward);
			
			userInfoService.updateCredits(new String[]{userId}, Integer.parseInt(Constants.REWARD_DEFAULT_SCORE));
			
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	
	@RequestMapping(value = "/evalution/getCommentsInfo", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String getCommentsInfo(HttpServletRequest request) {
		String addressId = request.getParameter("addressId");
		String currentPage = request.getParameter("page");
		try {
			if(!StringUtils.isEmpty(currentPage)){
				returns = expressEvalutionService.getlstEvaluation(addressId,Integer.parseInt(currentPage));
			}
		} catch (BriException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		} catch (ParseException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/evalution/getMoreCommentsInfo", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String getMoreCommentsInfo(HttpServletRequest request) {
		String addressId = request.getParameter("addressId");
		String currentPage = request.getParameter("page");
		try {
			if(!StringUtils.isEmpty(currentPage)){
				returns = expressEvalutionService.getMoreCommentsInfo(addressId,Integer.parseInt(currentPage));
			}
		} catch (BriException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		} catch (ParseException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
}
