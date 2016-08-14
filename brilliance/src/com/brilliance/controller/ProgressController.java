package com.brilliance.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brilliance.base.BaseController;
import com.brilliance.po.OrderProgress;
import com.brilliance.po.RewardScore;
import com.brilliance.po.UserInfo;
import com.brilliance.service.CommonService;
import com.brilliance.service.OrderProgressService;
import com.brilliance.service.RewardScoreService;
import com.brilliance.service.UserInfoService;
import com.brilliance.utils.CacheUtils;
import com.brilliance.utils.Constants;
import com.brilliance.utils.GsonSingleton;
import com.brilliance.utils.Tools;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.apache.commons.lang3.StringUtils;

@Controller
@Scope("request")
public class ProgressController extends BaseController{
	private static String BAIDUURL="http://baidu.kuaidi100.com/query?";
	private static String AIKUAIDI="http://www.kuaidiapi.cn/rest/?uid=22300&key=636efb77a7864c47a5ea2a6c72c93c6b&ord=desc";
	
	@Resource
	private OrderProgressService orderProgressService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private UserInfoService userInfoService;
	
	@Resource
	private RewardScoreService rewardScoreService;
	
	@RequestMapping(value = "progress", produces = "text/html;charset=utf-8")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView view = new ModelAndView("progress/index");
		return view;
	}
	
	@RequestMapping(value = "admin_progress", produces = "text/html;charset=utf-8")
	public ModelAndView adminIndex(HttpServletRequest request){
		ModelAndView view = new ModelAndView("progress/admin_progress");
		return view;
	}
	
	@RequestMapping(value = "/progress/detail.json", produces = "text/json;charset=utf-8")
	public @ResponseBody
	String detail (HttpServletRequest request) {
		try {
			String codeStr = request.getParameter("code");
			String postid = request.getParameter("postid");
			String userId = "";
			String flag = request.getParameter("source");
			String source = "";
			if("PC".equals(flag)){
				source = String.valueOf(Constants.SOURCE_PC);
				UserInfo user = CacheUtils.getUserInfo_PC(request);
				if(null != user){
					userId = user.getUserId();
				}
			}else if ("MB".equals(flag)){
				source = String.valueOf(Constants.SOURCE_MOBILE);
				//UserInfo user = CacheUtils.getLUserInfo_MB(request, request.getParameter("sessionId"));
				userId = request.getParameter("userId");
				if(StringUtils.isEmpty(userId)){
					return Tools.getErrorsRetruns(Constants.ERR_NOT_LOGIN).generateJsonData();
				}
			}
			/*if("shunfeng".equals(type)){
				expressCode = "1001";
			}else if ("yuantong".equals(type)){
				expressCode = "1002";
			}else if ("shentong".equals(type)){
				expressCode = "1003";
			}else if ("zhongtong".equals(type)){
				expressCode = "1005";
			}else if ("yunda".equals(type)){
				expressCode = "1004";
			}*/
			String type = "";
			String expressCode = "";
			if(!StringUtils.isEmpty(codeStr)){
				String[] tmp = StringUtils.split(codeStr, "|");
				type = tmp[0];
				expressCode = tmp[1];
			}
			
			String str = fetchExpressData(postid, type);
			
			String info = getStatusMsg(str);
			
			String state = getState(str);
			
			//同一个单号只能增加一次积分（即，谁先扫描，谁得积分）
			if (!orderProgressService.isExist(postid)) {
				// save scan orders
				OrderProgress order = new OrderProgress();
				order.setUserId(userId);
				order.setExpressCode(expressCode);
				order.setExpressNo(postid);
				order.setSource(source);
				order.setLatestStatusInfo(info);
				order.setCreationDate(Tools.getTime());
				saveOrders(order);
				
				
				RewardScore reward = new RewardScore();
				
				reward.setUserId(userId);
				reward.setScore(Constants.REWARD_DEFAULT_SCORE);
				reward.setRewardType(Constants.REWARD_TYPE_SCAN);
				reward.setCreationTime(Tools.getTime());
				rewardScoreService.save(reward);
				
				//增加积分,
				userInfoService.updateCredits(new String[]{userId}, Integer.parseInt(Constants.REWARD_DEFAULT_SCORE));
				
			}else{
				if(!state.equals(Constants.DELIVER_COMPLETED)){
					//update progress status info
					OrderProgress order = new OrderProgress();
					order.setExpressNo(postid);
					order.setLatestStatusInfo(info);
					orderProgressService.updateStatusInfo(order);
				}
			}
			return str;
		} catch (Exception e) {
			return Tools.getErrorsRetrunsMsg("系统错误,请重试!").generateJsonData();
			//return "{message:'系统错误,请重试!'}";
		}
	}

	private String fetchExpressData(String postid, String type)
			throws MalformedURLException, IOException, XPatherException {
		String pageUrl = AIKUAIDI+"&order="+postid+"&id="+type;
		
		HtmlCleaner cleaner = new HtmlCleaner();
		URL url = new URL(pageUrl);
		TagNode node = cleaner.clean(url, "utf-8");
		TagNode tagNode = ((TagNode)(node.evaluateXPath("//body")[0]));
		String str = tagNode.getText().toString();
		return str;
	}
	
	@RequestMapping(value = "/progress/hisOrderInfo.json", produces = "text/json;charset=utf-8")
	public @ResponseBody
	String hisOrderInfo (HttpServletRequest request) {
		try {
			String type = request.getParameter("type");
			String postid = request.getParameter("expressNo");
			
			String str = fetchExpressData(postid, type);
			
			String info = getStatusMsg(str);
			
			String state = getState(str);
			
			if(!StringUtils.isEmpty(info)){
				if(!state.equals(Constants.DELIVER_COMPLETED)){
					OrderProgress order = new OrderProgress();
					order.setExpressNo(postid);
					order.setLatestStatusInfo(info);
					orderProgressService.updateStatusInfo(order);
				}
			}
			
			return str;
		} catch (Exception e) {
			return "{message:'系统错误,请重试!'}";
		}
	}
	
	
	private String getStatusMsg(String jsonStr){
		String str = "";
		JsonObject json = GsonSingleton.getJsonObject(jsonStr);
		if(null != json){
			JsonArray jsonArray = json.getAsJsonArray("data");
			if(null != jsonArray && jsonArray.size() >0){
				str =  jsonArray.get(0).toString();
			}
			
		}
		return str;
	}
	
	private String getState(String jsonStr){
		String str = "";
		JsonObject json = GsonSingleton.getJsonObject(jsonStr);
		if(null != json){
			str = json.get("status").toString();
		}
		return str;
	}
	
	private void saveOrders (OrderProgress order) {
		orderProgressService.merge(order);
	}
	
	@RequestMapping(value = "/progress/getScanOrders.json", produces = "text/json;charset=utf-8")
	public @ResponseBody
	String getScanOrders (HttpServletRequest request) {
		try {
			//String userId = "";
			String userId = request.getParameter("userId");
			//UserInfo user = CacheUtils.getLUserInfo_MB(request, sessionId);
			if(StringUtils.isEmpty(userId)){
				return Tools.getErrorsRetruns(Constants.ERR_NOT_LOGIN).generateJsonData();
			}
			//userId="200002";
			OrderProgress order = new OrderProgress();
			order.setUserId(userId);
			returns = orderProgressService.getAll(order);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/progress/deleteOrder.json", produces = "text/json;charset=utf-8")
	public @ResponseBody
	String deleteOrder (HttpServletRequest request) {
		try {
			//String userId = "";
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id)){
				OrderProgress order = new OrderProgress();
				order.setId(Integer.parseInt(id));
				returns = orderProgressService.deleteHistoryOrder(order);
			}
			
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}

}
