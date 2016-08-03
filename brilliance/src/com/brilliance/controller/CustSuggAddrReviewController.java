package com.brilliance.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brilliance.base.BaseController;
import com.brilliance.base.BriException;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.AddressSuggInfo;
import com.brilliance.po.AddressReviewInfo;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.po.ExpressInfo;
import com.brilliance.po.RewardScore;
import com.brilliance.service.AdminInfoService;
import com.brilliance.service.CustSuggAddrReviewService;
import com.brilliance.service.CustomizationAddressService;
import com.brilliance.service.ExpressInfoService;
import com.brilliance.service.RewardScoreService;
import com.brilliance.service.UserInfoService;
import com.brilliance.utils.CacheUtils;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

@Controller
@Scope("request")
public class CustSuggAddrReviewController extends BaseController{

	@Resource
	private AdminInfoService adminInfoService;
	
	@Resource
	private ExpressInfoService expressInfoService;
	
	@Resource
	private CustSuggAddrReviewService custSuggAddrReviewService;
	
	@Resource
	private CustomizationAddressService customizationAddressService;
	

	@Resource
	private RewardScoreService rewardScoreService;
	
	
	@Resource
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/forwardToCustSuggAddrReview", produces = "text/html;charset=utf-8")
	@Transactional
	public ModelAndView forwardToCustSuggAddrReview(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/express/custSuggAddrReview");
		List<ExpressInfo> expresslst;
		try {
			expresslst = expressInfoService.getAllExpress();
//			List<AdminInfo> adminlst = adminInfoService.getAdminLst();
			view.getModel().put("expresslst", expresslst);
//			view.getModel().put("adminlst", adminlst);
		} catch (BriException e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value = "/custSuggestAddrReivew/showAllCustSuggAddr", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody String showAllCustSuggAddr(HttpServletRequest request) {
		AddressSuggInfo info = new AddressSuggInfo();
//		AdminInfo adminInfo = CacheUtils.getAdmInfo(request);
		
		String expressCode = request.getParameter("expressCode");
		String hotName = request.getParameter("hotName");
		String status = request.getParameter("status");
//		String createdBy = request.getParameter("createdBy");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String areaId = request.getParameter("areaId");
		
		info.setExpressCode(expressCode);
		info.setHotName(hotName);
		info.setStatus(status);
		info.setProvinceId(provinceId);
		info.setCityId(cityId);
		info.setAreaId(areaId);
		
		try {
			returns = custSuggAddrReviewService.getCustSuggAddrList(info);
			//查询的结果保存在session
			request.getSession().setAttribute("addressReviewInfoSessionList",returns.getData().get("lstAddr"));
		} catch (BriException e) {
			logger.error("手机端用户推荐附近快递，查询失败！");
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/custSuggestAddrReivew/reviewCustSuggAddr", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody String rejectCustSuggAddr(HttpServletRequest request) {
		List<AddressReviewInfo> list = new ArrayList<AddressReviewInfo>();
		AdminInfo adminInfo = CacheUtils.getAdmInfo(request);
		
		int credits  = 0;
		
		String reviewType = request.getParameter("reviewType");
		String suggestId = request.getParameter("suggestId");
		String memo = request.getParameter("memo");
		
		String[] suggestIds = suggestId.split(",");
		
		AddressReviewInfo info;
		for(int i=0;i<suggestIds.length;i++){
			info = new AddressReviewInfo();
			info.setSuggestId(suggestIds[i]);
			info.setReviewer(adminInfo.getAccountId());
			info.setReviewTime(Tools.getTime());
			
			if(reviewType.equals(Constants.REVIEW_TYPE_PASS)){
				info.setStatus(Constants.REVIEW_PASS);
			}else{
				info.setStatus(Constants.REVIEW_REJECT);
				info.setReviewMemo(memo);
			}
			list.add(info);
		}
		
		try {
			returns = custSuggAddrReviewService.reviewCustSuggAddr(list);
			
			//审核通过信息入库、用户加分
			if(reviewType.equals(Constants.REVIEW_TYPE_PASS)){
				credits = Constants.SHARE_ADDRESS_SUCCESS;
				
				publishToCustomizationAddress(request,suggestIds);
			}else{
				credits = Constants.SHARE_ADDRESS_REJECT;
			}
			
			creditBySharingExpressInfo(request,suggestIds,credits);
		} catch (BriException e) {
			logger.error("手机端用户推荐附近快递，审核失败！");
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return returns.generateJsonData();
	}
	
	//获取session中查询的快递信息
	@SuppressWarnings("unchecked")
	public static List<AddressSuggInfo> getaddressReviewInfoFromSession(HttpServletRequest request,String[] suggestIds){
		List<AddressSuggInfo> addressReviewInfoList = new ArrayList<AddressSuggInfo>();
		List<AddressSuggInfo> addressReviewInfoSessionList = (List<AddressSuggInfo>)request.getSession().getAttribute("addressReviewInfoSessionList");
		Map<String,AddressSuggInfo> map = new HashMap<String,AddressSuggInfo>();
		AddressSuggInfo addressReviewInfo;
		
		for(int i=0;i<addressReviewInfoSessionList.size();i++){
			addressReviewInfo = new AddressSuggInfo();
			addressReviewInfo = addressReviewInfoSessionList.get(i);
			
			map.put(addressReviewInfo.getId().toString(), addressReviewInfo);
		}
		
		for(String suggestId:suggestIds){
			if(map.containsKey(suggestId)){
				addressReviewInfoList.add(map.get(suggestId));
			}
		}
		return addressReviewInfoList;
	}
	
	//发布审核通过的快递
	public void publishToCustomizationAddress(HttpServletRequest request,String[] suggestIds){
		
		AdminInfo adminInfo = CacheUtils.getAdmInfo(request);
		List<AddressSuggInfo> addressReviewInfoList = getaddressReviewInfoFromSession(request,suggestIds);
		List<CustomizationAddressInfo> customizationAddressInfos = new ArrayList<CustomizationAddressInfo>();
		
		try {
			CustomizationAddressInfo customizationAddressInfo;
			for(int i=0;i<addressReviewInfoList.size();i++){
				customizationAddressInfo = new CustomizationAddressInfo();
				AddressSuggInfo addressReviewInfo = addressReviewInfoList.get(i);
				
				//customizationAddressInfo.setAddressId(Tools.getTimeStr());
				customizationAddressInfo.setAddressId(addressReviewInfo.getAddressId());
//				customizationAddressInfo.setParentId(parentId);
				customizationAddressInfo.setExpressCode(addressReviewInfo.getExpressCode());
				customizationAddressInfo.setArchiveFlag(Constants.UNARCHIVED);
				customizationAddressInfo.setDataType(Constants.DATATYPE_REGION);
				customizationAddressInfo.setHotName(addressReviewInfo.getHotName());
				customizationAddressInfo.setContactName(addressReviewInfo.getContactName());
				customizationAddressInfo.setMobile(addressReviewInfo.getMobile());
				customizationAddressInfo.setOfficeNo(addressReviewInfo.getOfficeNo());
				customizationAddressInfo.setCreatedBy(adminInfo.getAccountId());
				customizationAddressInfo.setMemo(addressReviewInfo.getMemo());
				customizationAddressInfo.setSource(Constants.SOURCE_MOBILE);
				customizationAddressInfo.setProvinceId(addressReviewInfo.getProvinceId());
				customizationAddressInfo.setCityId(addressReviewInfo.getCityId());
				customizationAddressInfo.setAreaId(addressReviewInfo.getAreaId());
				customizationAddressInfo.setTailAddress(addressReviewInfo.getTailAddress());
				customizationAddressInfo.setAddressDetail(addressReviewInfo.getAddressDetail());
				customizationAddressInfo.setCreateTime(Tools.getTime());
				customizationAddressInfo.setLongitude(addressReviewInfo.getLongitude());
				customizationAddressInfo.setLatitude(addressReviewInfo.getLatitude());
				
				customizationAddressInfos.add(customizationAddressInfo);
			}
			customizationAddressService.saveBulk(customizationAddressInfos);
		} catch (BriException e) {
			logger.error("手机端用户推荐附近快递，快递信息入库失败！");
			e.printStackTrace();
		}
		
	}
	
	//审核通过的快递，给用户积分
	public void creditBySharingExpressInfo(HttpServletRequest request,String[] suggestIds,int credits){
		List<AddressSuggInfo> addressReviewInfoList = getaddressReviewInfoFromSession(request,suggestIds);
		//String[] userIds = new String[suggestIds.length];
		Map<String,AddressSuggInfo> map = new HashMap<String,AddressSuggInfo>();
		AddressSuggInfo addressReviewInfo;
		
		for(int i=0;i<addressReviewInfoList.size();i++){
			addressReviewInfo = new AddressSuggInfo();
			addressReviewInfo = addressReviewInfoList.get(i);
			
			map.put(addressReviewInfo.getId().toString(), addressReviewInfo);
		}
		
		List<RewardScore> list = new ArrayList<RewardScore>();
		
		
		for(int k=0;k<suggestIds.length;k++){
			RewardScore reward = new RewardScore();
			
			
			reward.setScore(Constants.REWARD_DEFAULT_SCORE);
			reward.setRewardType(Constants.REWARD_SUBMIT_ADDRESS);
			reward.setCreationTime(Tools.getTime());
			
			if(map.containsKey(suggestIds[k])){
				//userIds[k] = ((AddressSuggInfo)map.get(suggestIds[k])).getCreatedBy();
				reward.setUserId(((AddressSuggInfo)map.get(suggestIds[k])).getCreatedBy());
			}
			
			list.add(reward);
		}
		try {
			//userInfoService.updateCredits(userIds, credits);
			rewardScoreService.saveBulk(list);
		} catch (BriException e) {
			logger.error("手机端用户推荐附近快递，加分失败！");
			e.printStackTrace();
		}
		
		
	}
}
