package com.brilliance.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brilliance.base.BaseController;
import com.brilliance.base.BriException;
import com.brilliance.base.ControllerReturns;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.AddressSuggInfo;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.po.FavouriteAddress;
import com.brilliance.po.SearchCriteria;
import com.brilliance.service.CommonService;
import com.brilliance.service.CustSuggAddrReviewService;
import com.brilliance.service.CustomizationAddressService;
import com.brilliance.service.ExpressInfoService;
import com.brilliance.service.FavouriteAddressService;
import com.brilliance.utils.CacheUtils;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

@Controller
@Scope("request")
public class FavouriteAddressController extends BaseController{

	@Resource
	private FavouriteAddressService favouriteAddressService;
	
	@Resource
	private CustomizationAddressService customizationAddressService;
	
	@Resource
	private CustSuggAddrReviewService custSuggAddrReviewService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private ExpressInfoService expressInfoService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addNewFav.json", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String addNewFav(HttpServletRequest request) {
		ControllerReturns returns = new ControllerReturns();
		try {
			//List<CustomizationAddressInfo> listAddressInfo = new ArrayList<CustomizationAddressInfo>();
			FavouriteAddress favouriteAddress = new FavouriteAddress();
			AddressSuggInfo addressSuggInfo = new AddressSuggInfo();
			
			String userId = request.getParameter("userId");
			String contactName = request.getParameter("contactName");
			String hotName = request.getParameter("hotName");
			String pre_address = request.getParameter("address");
			String addressIds = request.getParameter("addressIds");
			String expressCode = request.getParameter("expressCode");
			String expressName = request.getParameter("expressName");
			String expressURL = request.getParameter("expressURL");
			String tailAddress = request.getParameter("tailAddress");
			String officeNo = request.getParameter("officeNo");
			String flag = request.getParameter("flag");
			String mobile = request.getParameter("mobile");
			//String longitude = request.getParameter("longitude");
			//String latitude = request.getParameter("latitude");
			String addressInfoId = Tools.getTimeStr();
			String fullAddr = pre_address+" "+tailAddress;
			String memo = request.getParameter("memo");
			
			
			favouriteAddress.setUserId(userId);
			favouriteAddress.setSource(Constants.FAV_ADD);
			favouriteAddress.setAddressId(addressInfoId);
			favouriteAddress.setCreateTime(Tools.getTime());
			
			
			//listAddressInfo.add(favouriteAddress);
			String[] areas = StringUtils.split(pre_address, " ");
			
			//地址定位
			if("1".equals(flag)){
				//fullAddr=addressInfo;
				
				if(!ArrayUtils.isEmpty(areas)){
					String provinceId = commonService.findProvinceIdByName(areas[0]);
					String cityId = commonService.findCityIdByName(areas[1]);
					String areaId = commonService.findAreaIdByName(areas[2]);
					
					addressSuggInfo.setProvinceId(provinceId);
					addressSuggInfo.setCityId(cityId);
					addressSuggInfo.setAreaId(areaId);
				}
			}
			if("0".equals(flag)){
				if(!StringUtils.isEmpty(addressIds)){
					String[] tmp1 = addressIds.split(",");
					addressSuggInfo.setProvinceId(tmp1[0]);
					addressSuggInfo.setCityId(tmp1[1]);
					addressSuggInfo.setAreaId(tmp1[2]);
				}
			}
			String tm11 = areas[0]+areas[1]+areas[2]+tailAddress.trim();
			Map map = Tools.addrValidation(tm11, areas[1]);
			int status = (int) Float.parseFloat(map.get("status")
					.toString());
			
			String latitude = "";
			String longitude = "";
			//Got the location info.
			if (0 == status) {
				Map locationMap = (Map) ((Map) map.get("result"))
						.get("location");
				latitude = locationMap.get("lat").toString();
				longitude = locationMap.get("lng").toString();
			}
			addressSuggInfo.setCreateTime(Tools.getTime());
			addressSuggInfo.setTailAddress(tailAddress);
			addressSuggInfo.setAddressDetail(fullAddr);
			addressSuggInfo.setAddressId(addressInfoId);
			addressSuggInfo.setContactName(contactName);
			addressSuggInfo.setOfficeNo(officeNo);
			addressSuggInfo.setMobile(mobile);
			addressSuggInfo.setExpressCode(expressCode);
			addressSuggInfo.setHotName(hotName);
			addressSuggInfo.setCreatedBy(userId);
			addressSuggInfo.setLongitude(longitude);
			addressSuggInfo.setLatitude(latitude);
			addressSuggInfo.setSource(Constants.SOURCE_MOBILE);
			addressSuggInfo.setDataType(Constants.DATATYPE_REGION);
			
			addressSuggInfo.setStatus(Constants.REVIEW_UNSTART);
			addressSuggInfo.setDeleteFlag(Constants.ADDRESS_ACTIVE);
			addressSuggInfo.setMemo(memo);
			
			//save address which created by user
			custSuggAddrReviewService.saveCustSuggAddr(addressSuggInfo);
			
			//save my favourite
			favouriteAddressService.save(favouriteAddress);
			
			//用户前台评论展示
			addressSuggInfo.setExpressName(expressName);
			addressSuggInfo.setLogoUrl(expressURL);
			returns.putData("addressInfo", addressSuggInfo);
			
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	
	//电脑端关键字验证重复
	@RequestMapping(value = "/favouriteAddress/validateHotName", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String validateHotName(HttpServletRequest request) {
		ControllerReturns returns = new ControllerReturns();
		String expressCode = request.getParameter("expressCode");
		String[] hotNameArray = request.getParameterValues("hotNameArray[]");
		String[] hotNameIdentifyArray = request.getParameterValues("hotNameIdentifyArray[]");
//		try{
			returns = customizationAddressService.validateHotName(expressCode, hotNameArray,hotNameIdentifyArray);
//		}catch(Exception e){
//			returns = Tools.getExceptionControllerRetruns(e);
//		}
		//System.out.println(returns.generateJsonData());
		return returns.generateJsonData();
	}
	
	//电脑端增加favoriteAddress(门店和配送范围)
	@RequestMapping(value = "/favouriteAddress/addCustAddr", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String addPCNewFav(HttpServletRequest request) {
		ControllerReturns returns = new ControllerReturns();
		try {
			List<CustomizationAddressInfo> customizationAddressInfos = new ArrayList<CustomizationAddressInfo>();
			
			//HttpSession session = request.getSession();
			//AdminInfo admin = (AdminInfo) session.getAttribute("adminInfo");
			AdminInfo admin = CacheUtils.getAdmInfo(request);
			String adminId = admin.getAccountId().toString();
			
			String flag = request.getParameter("flag");
			
//			String[] provinceIdArray = request.getParameterValues("provinceIdArray[]");
//			String[] cityIdArray = request.getParameterValues("cityIdArray[]");
//			String[] areaIdArray = request.getParameterValues("areaIdArray[]");
			String[] suggestionAddrArray = request.getParameterValues("suggestionAddrArray[]");
			String[] longArray = request.getParameterValues("longArray[]");
			String[] latiArray = request.getParameterValues("latiArray[]");
			String[] hotNameArray = request.getParameterValues("hotNameArray[]");
//			String[] addrInfoArray = request.getParameterValues("addrInfoArray[]");
			
			String storeAddressHotName_new = request.getParameter("storeAddressHotName_new");
			String contactName = request.getParameter("contactName");
			String expressCode = request.getParameter("expressCode");
			String officeNo = request.getParameter("officeNo");
			String mobile = request.getParameter("mobile");
			String storeAddressPCA = request.getParameter("storeAddressPCA");
			String storeAddressProvinceId = request.getParameter("storeAddressProvinceId");
			String storeAddressCityId = request.getParameter("storeAddressCityId");
			String storeAddressAreaId = request.getParameter("storeAddressAreaId");
			String tailAddress_new = request.getParameter("tailAddress_new");
			String storeTailAddress_longt = request.getParameter("storeTailAddress_longt");
			String storeTailAddress_lati = request.getParameter("storeTailAddress_lati");
			
			String addressInfoId = Tools.getTimeStr();
			
			String id = request.getParameter("id");
			
			CustomizationAddressInfo storeAddr = new CustomizationAddressInfo();
			storeAddr.setHotName(storeAddressHotName_new);
			storeAddr.setExpressCode(expressCode);
			storeAddr.setContactName(contactName);
			storeAddr.setMobile(mobile);
			storeAddr.setOfficeNo(officeNo);
			storeAddr.setProvinceId(storeAddressProvinceId);
			storeAddr.setCityId(storeAddressCityId);
			storeAddr.setAreaId(storeAddressAreaId);
			storeAddr.setTailAddress(tailAddress_new);
			storeAddr.setAddressDetail(storeAddressPCA+" "+tailAddress_new);
			storeAddr.setCreateTime(Tools.getTime());
			
			storeAddr.setAddressId(addressInfoId);
			
			storeAddr.setCreatedBy(adminId);
			storeAddr.setLongitude(storeTailAddress_longt);
			storeAddr.setLatitude(storeTailAddress_lati);
			storeAddr.setSource(Constants.SOURCE_PC);
			storeAddr.setArchiveFlag(Constants.ARCHIVED);
			storeAddr.setDataType(Constants.DATATYPE_STORE);
			if(flag.equals(0)){
				customizationAddressService.save(storeAddr);
			}else if(flag.equals("1")){
				customizationAddressService.save(storeAddr);
				if(longArray!=null){
					for(int i=0;i<longArray.length;i++){
						CustomizationAddressInfo customizationAddressInfo = new CustomizationAddressInfo();
						customizationAddressInfo.setCreateTime(Tools.getTime());
						customizationAddressInfo.setTailAddress(suggestionAddrArray[i]);
						
//						String fullAddr = addrInfoArray[i]+" "+suggestionAddrArray[i];
						String fullAddr = storeAddressPCA+" "+suggestionAddrArray[i];
						customizationAddressInfo.setAddressDetail(fullAddr);
						
						customizationAddressInfo.setAddressId(Tools.getTimeStr());
						customizationAddressInfo.setParentId(addressInfoId);
						customizationAddressInfo.setContactName(contactName);
						customizationAddressInfo.setOfficeNo(officeNo);
						customizationAddressInfo.setMobile(mobile);
						customizationAddressInfo.setExpressCode(expressCode);
						customizationAddressInfo.setHotName(hotNameArray[i]);
						customizationAddressInfo.setCreatedBy(adminId);
						customizationAddressInfo.setLongitude(longArray[i]);
						customizationAddressInfo.setLatitude(latiArray[i]);
//						customizationAddressInfo.setProvinceId(provinceIdArray[i]);
//						customizationAddressInfo.setCityId(cityIdArray[i]);
//						customizationAddressInfo.setAreaId(areaIdArray[i]);
						customizationAddressInfo.setProvinceId(storeAddressProvinceId);
						customizationAddressInfo.setCityId(storeAddressCityId);
						customizationAddressInfo.setAreaId(storeAddressAreaId);
						//System.out.println(longArray[i]+"|"+latiArray[i]+"|"+hotNameArray[i]);
						customizationAddressInfo.setSource(Constants.SOURCE_PC);
						customizationAddressInfo.setArchiveFlag(Constants.ARCHIVED);
						customizationAddressInfo.setDataType(Constants.DATATYPE_REGION);
						
						customizationAddressInfos.add(customizationAddressInfo);
					}
				}
				customizationAddressService.saveBulk(customizationAddressInfos);
			}
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	//电脑端增加favoriteAddress(配送范围)
	@RequestMapping(value = "/favouriteAddress/addCustAddrRegion", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String addPCNewFavRegion(HttpServletRequest request) {
		ControllerReturns returns = new ControllerReturns();
		try {
			List<CustomizationAddressInfo> customizationAddressInfos = new ArrayList<CustomizationAddressInfo>();
			
			//HttpSession session = request.getSession();
			//AdminInfo admin = (AdminInfo) session.getAttribute("adminInfo");
			AdminInfo admin = CacheUtils.getAdmInfo(request);
			String adminId = admin.getAccountId().toString();
			
			//String storeAddressId = request.getParameter("storeAddressId");
			
			String flag = request.getParameter("flag");
			String regionAddressHotName_new = request.getParameter("regionAddressHotName_new");
			String regionContactName = request.getParameter("regionContactName");
			String regionExpressCode = request.getParameter("regionExpressCode");
			String regionOfficeNo = request.getParameter("regionOfficeNo");
			String regionMobile = request.getParameter("regionMobile");
			String regionAddressPCA = request.getParameter("regionAddressPCA");
			String regionAddressProvinceId = request.getParameter("regionAddressProvinceId");
			String regionAddressCityId = request.getParameter("regionAddressCityId");
			String regionAddressAreaId = request.getParameter("regionAddressAreaId");
			String tailAddress_newRegion = request.getParameter("tailAddress_newRegion");
			String regionTailAddress_longt = request.getParameter("regionTailAddress_longt");
			String regionTailAddress_lati = request.getParameter("regionTailAddress_lati");
			
			String addressInfoId = Tools.getTimeStr();
			
			String id = request.getParameter("id");
			
			CustomizationAddressInfo regionAddr = new CustomizationAddressInfo();
			regionAddr.setHotName(regionAddressHotName_new);
			regionAddr.setExpressCode(regionExpressCode);
			regionAddr.setContactName(regionContactName);
			regionAddr.setMobile(regionMobile);
			regionAddr.setOfficeNo(regionOfficeNo);
			regionAddr.setProvinceId(regionAddressProvinceId);
			regionAddr.setCityId(regionAddressCityId);
			regionAddr.setAreaId(regionAddressAreaId);
			regionAddr.setTailAddress(tailAddress_newRegion);
			regionAddr.setAddressDetail(regionAddressPCA+" "+tailAddress_newRegion);
			regionAddr.setCreateTime(Tools.getTime());
			
			regionAddr.setAddressId(addressInfoId);
			
			regionAddr.setCreatedBy(adminId);
			regionAddr.setLongitude(regionTailAddress_longt);
			regionAddr.setLatitude(regionTailAddress_lati);
			regionAddr.setSource(Constants.SOURCE_PC);
			regionAddr.setArchiveFlag(Constants.ARCHIVED);
			regionAddr.setDataType(Constants.DATATYPE_REGION);
			if(flag.equals("0")){
				customizationAddressService.save(regionAddr);
			}else if(flag.equals("1")){
				
			}
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	//电脑端修改favoriteAddress（处理单个修改派送范围）
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/favouriteAddress/editCustAddrRegion", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String editPCNewFav(HttpServletRequest request) {
		//ControllerReturns returns = new ControllerReturns();
		try {
			CustomizationAddressInfo customizationAddressInfo = new CustomizationAddressInfo();
			
			//HttpSession session = request.getSession();
			//AdminInfo admin = (AdminInfo) session.getAttribute("adminInfo");
			AdminInfo admin = CacheUtils.getAdmInfo(request);
			String adminId = admin.getAccountId().toString();
			
			String dataType =  request.getParameter("dataType");//上/下一条门店或配送范围
			String nextOperation =  request.getParameter("nextOperation");//上/下一个操作标识
			String id =  request.getParameter("serialId");
			String addressId = request.getParameter("regionAddressId");
			String parentId = request.getParameter("regionParentId");
			
			String contactName = request.getParameter("regionContactName");
			String hotName = request.getParameter("regionAddressHotName_new");
			String expressCode = request.getParameter("regionExpressCode");
			String tailAddress = request.getParameter("tailAddress_newRegion");
			String officeNo = request.getParameter("regionOfficeNo");
			String provCityArea = request.getParameter("regionAddressPCA");
			String mobile = request.getParameter("regionMobile");
			String longitude = request.getParameter("regionTailAddress_longt");
			String latitude = request.getParameter("regionTailAddress_lati");
			String fullAddr = provCityArea+" "+tailAddress;
			String provinceId = request.getParameter("regionAddressProvinceId");
			String cityId = request.getParameter("regionAddressCityId");
			String areaId = request.getParameter("regionAddressAreaId");
			
			customizationAddressInfo.setId(Integer.parseInt(id));
			customizationAddressInfo.setLastUpdateTime(Tools.getTime());
			customizationAddressInfo.setCreateTime(Tools.getTime());
			customizationAddressInfo.setTailAddress(tailAddress);
			customizationAddressInfo.setAddressDetail(fullAddr);
			customizationAddressInfo.setAddressId(addressId);
			customizationAddressInfo.setParentId(parentId);
			customizationAddressInfo.setContactName(contactName);
			customizationAddressInfo.setOfficeNo(officeNo);
			customizationAddressInfo.setMobile(mobile);
			customizationAddressInfo.setExpressCode(expressCode);
			customizationAddressInfo.setHotName(hotName);
			customizationAddressInfo.setCreatedBy(adminId);
			customizationAddressInfo.setLongitude(longitude);
			customizationAddressInfo.setLatitude(latitude);
			customizationAddressInfo.setProvinceId(provinceId);
			customizationAddressInfo.setCityId(cityId);
			customizationAddressInfo.setAreaId(areaId);
			customizationAddressInfo.setSource(Constants.SOURCE_PC);
			customizationAddressInfo.setDataType(Constants.DATATYPE_REGION);
			customizationAddressInfo.setArchiveFlag(Constants.ARCHIVED);
			
			returns = customizationAddressService
					.update(customizationAddressInfo);
			List<CustomizationAddressInfo> lstAddr = new ArrayList<CustomizationAddressInfo>();
			if (returns.isSuccess()) {
				List<CustomizationAddressInfo> list = (List<CustomizationAddressInfo>) request
						.getSession().getAttribute("lstAddr");
				if (!CollectionUtils.isEmpty(list)) {
					
					CustomizationAddressInfo nextVal = null;
					boolean flag = true;
					int cnt = 0;
					int index = 0;
					boolean isEnd = false;
					boolean isTop = false;
					if(Constants.LASTRECORD.equals(nextOperation)){
						Collections.reverse(list);
					}
					
					int storeCnt = getCntOfStore(list);
					Iterator<CustomizationAddressInfo> itor = list.iterator();
					while (itor.hasNext()) {
						CustomizationAddressInfo cust = itor.next();
						index++;
						if(cust.getDataType().equals(Constants.DATATYPE_REGION)){
							if(!flag){
								nextVal = cust;
								
								if((list.size() - storeCnt - 1) == cnt){
									if(Constants.LASTRECORD.equals(nextOperation)){
										isTop = true;
									}else{
										isEnd = true;
									}
								}
									
								/*if(Constants.LASTRECORD.equals(nextOperation)){
									if((list.size() - storeCnt - 1) == cnt){
										isTop = true;
									}
								}else{
									if((list.size() - storeCnt - 1) == cnt){
										isEnd = true;
									}
								}*/
								
								break;
							}
							//indentify index of the list
							if(Integer.parseInt(id) == cust.getId().intValue()){
								//update self
								list.set(index-1, customizationAddressInfo);
								flag = false;
							}
							cnt++;
						}
						
					}
					if(null != nextVal){
						lstAddr.add(nextVal);
						returns.getData().put("lstAddr", lstAddr);
						returns.getData().put("isEnd", isEnd);
						returns.getData().put("isTop", isTop);
					}
					
					//revert back list order when finished for action on "save and last"
					if(Constants.LASTRECORD.equals(nextOperation)){
						Collections.reverse(list);
					}
				}
			}else{
				returns.setMessage("30003", "warning");
			}
			//returns = customizationAddressService.update(customizationAddressInfo,nextOperation, 1,adminId);
			
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	//处理修改门店和派送范围
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/favouriteAddress/editCustAddrpacked", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String editCustAddrpacked(HttpServletRequest request) {
		ControllerReturns returns = new ControllerReturns();
		try {
			List<CustomizationAddressInfo> lstAddress = new ArrayList<CustomizationAddressInfo>();
			
			AdminInfo admin = CacheUtils.getAdmInfo(request);
			String adminId = admin.getAccountId().toString();
//			String editFlag = request.getParameter("editFlag");
			
			String flag = request.getParameter("flag");
			
//			String[] provinceIdArray = request.getParameterValues("provinceIdArray[]");
//			String[] cityIdArray = request.getParameterValues("cityIdArray[]");
//			String[] areaIdArray = request.getParameterValues("areaIdArray[]");
//			String[] addrInfoArray = request.getParameterValues("addrInfoArray[]");
			
			String storeAddressHotName_new = request.getParameter("storeAddressHotName_new");
			String contactName = request.getParameter("contactName");
			String expressCode = request.getParameter("expressCode");
			String officeNo = request.getParameter("officeNo");
			String mobile = request.getParameter("mobile");
			String storeAddressPCA = request.getParameter("storeAddressPCA");
			String storeAddressProvinceId = request.getParameter("storeAddressProvinceId");
			String storeAddressCityId = request.getParameter("storeAddressCityId");
			String storeAddressAreaId = request.getParameter("storeAddressAreaId");
			String tailAddress_new = request.getParameter("tailAddress_new");
			String storeTailAddress_longt = request.getParameter("storeTailAddress_longt");
			String storeTailAddress_lati = request.getParameter("storeTailAddress_lati");
			String id = "";
			//String storeAddressId = Tools.getNanoTimeStr();
			
			
			CustomizationAddressInfo storeAddr = new CustomizationAddressInfo();
			storeAddr.setHotName(storeAddressHotName_new);
			storeAddr.setExpressCode(expressCode);
			storeAddr.setContactName(contactName);
			storeAddr.setMobile(mobile);
			storeAddr.setOfficeNo(officeNo);
			storeAddr.setProvinceId(storeAddressProvinceId);
			storeAddr.setCityId(storeAddressCityId);
			storeAddr.setAreaId(storeAddressAreaId);
			storeAddr.setTailAddress(tailAddress_new);
			storeAddr.setAddressDetail(storeAddressPCA+" "+tailAddress_new);
			storeAddr.setCreateTime(Tools.getTime());
			
			
			String addressInfoId = "";
			
			//update
			if(flag.equals("update") || Constants.LASTRECORD.equals(flag) || Constants.NEXTRECORD.equals(flag)){
				addressInfoId = request.getParameter("addressInfoId");
				id = request.getParameter("id");
				
				storeAddr.setAddressId(addressInfoId);
				storeAddr.setId(Integer.parseInt(id));
				
			}else if (flag.equals("add")){//only newly record need these values
				addressInfoId = Tools.getTimeStr();
			}
			
			storeAddr.setAddressId(addressInfoId);
			storeAddr.setCreatedBy(adminId);
			storeAddr.setLongitude(storeTailAddress_longt);
			storeAddr.setLatitude(storeTailAddress_lati);
			storeAddr.setSource(Constants.SOURCE_PC);
			storeAddr.setArchiveFlag(Constants.ARCHIVED);
			storeAddr.setDataType(Constants.DATATYPE_STORE);
			
			lstAddress.add(storeAddr);
			
			
			//assemble region addressinfo data
			String[] idArray = request.getParameterValues("idArray[]");
			String[] suggestionAddrArray = request.getParameterValues("suggestionAddrArray[]");
			String[] longArray = request.getParameterValues("longArray[]");
			String[] latiArray = request.getParameterValues("latiArray[]");
			String[] hotNameArray = request.getParameterValues("hotNameArray[]");
			String[] addressIdArray = request.getParameterValues("addressIdArray[]");
			
			if(longArray!=null){
//				String tmpAddressId = "";
				int len = longArray.length;
				for(int i=0;i<len;i++){
//					tmpAddressId = addressIdArray[i];
					
					
					
					CustomizationAddressInfo custAddr = new CustomizationAddressInfo();
					custAddr.setCreateTime(Tools.getTime());
					custAddr.setTailAddress(suggestionAddrArray[i]);
					
//					String fullAddr = addrInfoArray[i]+" "+suggestionAddrArray[i];
					String fullAddr = storeAddressPCA+" "+suggestionAddrArray[i];
					custAddr.setAddressDetail(fullAddr);
					
					if (flag.equals("update")
							|| Constants.NEXTRECORD.equals(flag)
							|| Constants.LASTRECORD.equals(flag)) {// 修改（addressIdArray不为null）
						if (StringUtils.isEmpty(idArray[i])) {
							custAddr.setAddressId(Tools.getTimeStr());
						} else {// 不是要新增的派送范-->有id和addressId
							custAddr.setId(Integer.parseInt(idArray[i]));
							custAddr.setAddressId(addressIdArray[i]);
						}
					} else if (flag.equals("add")) {
						custAddr.setAddressId(Tools.getTimeStr());
					}
//					
//					if(!StringUtils.isEmpty(tmpAddressId)){
//						custAddr.setAddressId(tmpAddressId);
//					}
					
					custAddr.setParentId(addressInfoId);
					custAddr.setContactName(contactName);
					custAddr.setOfficeNo(officeNo);
					custAddr.setMobile(mobile);
					custAddr.setExpressCode(expressCode);
					custAddr.setHotName(hotNameArray[i]);
					custAddr.setCreatedBy(adminId);
					custAddr.setLongitude(longArray[i]);
					custAddr.setLatitude(latiArray[i]);
//					customizationAddressInfo.setProvinceId(provinceIdArray[i]);
//					customizationAddressInfo.setCityId(cityIdArray[i]);
//					customizationAddressInfo.setAreaId(areaIdArray[i]);
					
					custAddr.setProvinceId(storeAddressProvinceId);
					custAddr.setCityId(storeAddressCityId);
					custAddr.setAreaId(storeAddressAreaId);
					custAddr.setSource(Constants.SOURCE_PC);
					custAddr.setArchiveFlag(Constants.ARCHIVED);
					custAddr.setDataType(Constants.DATATYPE_REGION);
					
					lstAddress.add(custAddr);
				}
			}
			
			if ("update".equals(flag) || Constants.LASTRECORD.equals(flag) || Constants.NEXTRECORD.equals(flag)) {
				List<CustomizationAddressInfo> lstOriginal = (List<CustomizationAddressInfo>) request
						.getSession().getAttribute("lstAddrBak");
				// 组装成List集合
				Map<String, List<CustomizationAddressInfo>> map = getMixdData(
						lstOriginal, lstAddress);

				List<CustomizationAddressInfo> lstAdd = map.get("toInsertList");
				List<CustomizationAddressInfo> lstDel = map.get("toDeleteList");
				List<CustomizationAddressInfo> lstUpd = map.get("toUpdatList");

				customizationAddressService.updateBulk(lstUpd);
				customizationAddressService.saveAll(lstAdd);
				customizationAddressService.deleteAll(lstDel);
				
				//for save next and save previous
				if(Constants.LASTRECORD.equals(flag) || Constants.NEXTRECORD.equals(flag)){
					List<CustomizationAddressInfo> lstAll = (List<CustomizationAddressInfo>)request.getSession().getAttribute("lstAddr");
					fetchNext(returns,lstAll,id,flag,lstAddress,request);
				}
			}else if("add".equals(flag)){
				customizationAddressService.saveAll(lstAddress);
			}
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	
	/**
	 * 这个方法的作用是获取下一条门店数据，但同时更新需要将最新的数据更新回来
	 * (由于在前台页面中，用户可以点击上一条和下一条进行批量修改数据。
	 * 如果此时回到上一条数据（由于我们是从session中的list获取数据，而不是每次查询数据库，降低数据库的IO操作，从而提高性能）
	 * ,就需要原来session的数据同步更新，否0.zxa0x.
	 * .
	 * .X会出现老数据)
	 * 
	 * 
	 * .
	 * 
	 *  
	 *  xaz	 * @param returns
	 * @param lstAll
	 * @param id
	 * @param action
	 * @param lstAddress
	 */
	private void fetchNext(ControllerReturns returns,List<CustomizationAddressInfo> lstAll,String id,String action,
			List<CustomizationAddressInfo> lstAddress,HttpServletRequest request){
		if(!CollectionUtils.isEmpty(lstAll)){
			
			List<CustomizationAddressInfo> lstAddr = new ArrayList<CustomizationAddressInfo>();
			boolean flag = true;
			CustomizationAddressInfo nextVal = null;
			String oldAddressId = null;
			//如果是上一条
			if(Constants.LASTRECORD.equals(action)){
				Collections.reverse(lstAll);
			}
			
			int cnt = 0;
			boolean isEnd = false;
			boolean isTop = false;
			Iterator<CustomizationAddressInfo> itor = lstAll.iterator();
			int storeCnt = getCntOfStore(lstAll);
			int index = 0;
			//search next record base on current
			while (itor.hasNext()){
				CustomizationAddressInfo cust = itor.next();
				index++;
				if(cust.getDataType().equals(Constants.DATATYPE_STORE)){
					if(!flag){
						nextVal = cust;
						
						/*if(0 == cnt){
							isTop = true;
						}*/
						if((storeCnt - 1) == cnt){
							if(Constants.LASTRECORD.equals(action)){
								isTop = true;
							}else{
								isEnd = true;
							}
							
						}
						/*if(Constants.LASTRECORD.equals(action)){
							if((storeCnt - 1) == cnt){
								isTop = true;
							}
						}
						*/
						break;
					}
					//indentify index of the list
					if(Integer.parseInt(id) == cust.getId().intValue()){
						oldAddressId = cust.getAddressId();
						//lstAddress对象的第一条数据是更新过的门店信息，需要更新session对象的list
						//避免出现用户点击保存上一个时出现的老数据
						lstAll.set(index-1, lstAddress.get(0));
						flag = false;
					}
					cnt++;
				}
			}
			lstAddr.add(nextVal);
			
			//把顺序还原 
			if(Constants.LASTRECORD.equals(action)){
				Collections.reverse(lstAll);
			}
			if(null != nextVal){
				//查询门店下的配送范围
				Iterator<CustomizationAddressInfo> itor1 = lstAll.iterator();
				//search region address info in the list for store address info
				String addressId = nextVal.getAddressId();
				while (itor1.hasNext()){
					CustomizationAddressInfo cust = itor1.next();
					if(cust.getDataType().equals(Constants.DATATYPE_REGION)){
						if(addressId.equals(cust.getParentId())){
							lstAddr.add(cust);
						}
					}
				}
			}
			
			if(!StringUtils.isEmpty(oldAddressId)){
				//更新上一条数据门店的配送范围(当用户点击下一条时),用最新的替换全部
				Iterator<CustomizationAddressInfo> itor2 = lstAll.iterator();
				//search region address info in the list for store address info
				while (itor2.hasNext()){
					CustomizationAddressInfo cust = itor2.next();
					if(cust.getDataType().equals(Constants.DATATYPE_REGION)){
						if(oldAddressId.equals(cust.getParentId())){
							itor2.remove();
						}
					}
				}
				//用最新的配送范围信息覆盖原有 
				lstAddress.remove(0);
				lstAll.addAll(lstAddress);
			}
			returns.getData().put("lstAddr", lstAddr);
			//put next value in the session
			request.getSession().setAttribute("lstAddrBak", lstAddr);
			returns.getData().put("isEnd", isEnd);
			returns.getData().put("isTop", isTop);
		}
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/favouriteAddress/getCustAddrlist", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String getCustAddrlist(HttpServletRequest request) {
		try {
			CustomizationAddressInfo info = new CustomizationAddressInfo();
			AdminInfo adminInfo = CacheUtils.getAdmInfo(request);
			//String isAdmin = request.getSession().getAttribute("isAdmin").toString();
			
			String expressCode = request.getParameter("expressCode");
			String hotName = request.getParameter("hotName");
			String tailAddress = request.getParameter("tailAddress");
			String provinceId = request.getParameter("provinceId");
			String cityId = request.getParameter("cityId");
			String areaId = request.getParameter("areaId");
			String dataType = request.getParameter("dataType");
			String createdBy = request.getParameter("createdBy");
			
			info.setExpressCode(expressCode);
			info.setHotName(hotName);
			info.setTailAddress(tailAddress);
			info.setProvinceId(provinceId);
			info.setCityId(cityId);
			info.setAreaId(areaId);
			info.setDataType(dataType);
			info.setCreatedBy(createdBy);
			
			//if("1".equals(isAdmin)){
				String archiveFlag = request.getParameter("archiveflag");
				String source = request.getParameter("source");
				info.setArchiveFlag(archiveFlag);
				info.setSource(source);
				
				/*String createdId = request.getParameter("createdId");
				
				info.setArchiveFlag(archiveFlag);
				info.setSource(source);
				if(!StringUtils.isEmpty(createdId)){
					info.setCreatedBy(createdId);
				}*/
			//}
			
			returns = customizationAddressService.getCustomAddrList(info,adminInfo);
			List<CustomizationAddressInfo> lstCust = (List<CustomizationAddressInfo>)returns.getData().get("lstAddr");
			request.getSession().setAttribute("lstAddr", lstCust);
		}
		catch(BriException e){
			returns = Tools.getExceptionControllerRetruns(e);
		} catch (ParseException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "favouriteAddress/getCustAddrInfo.json", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String getCustAddrInfo(HttpServletRequest request) {
		returns = new ServiceReturns();

		String addressId = request.getParameter("addressId");

		// returns = favouriteAddressService.getCusAddrInfo(addressId);
		boolean isEnd = false;
		boolean isTop = false;
		List<CustomizationAddressInfo> lstCust = new ArrayList<CustomizationAddressInfo>();
		List<CustomizationAddressInfo> lstAll = (List<CustomizationAddressInfo>) request
				.getSession().getAttribute("lstAddr");
		if (!CollectionUtils.isEmpty(lstAll)) {
			Iterator<CustomizationAddressInfo> itor = lstAll.iterator();
			int cnt = 0;
			int storeCnt = getCntOfStore(lstAll);
			while (itor.hasNext()) {
				CustomizationAddressInfo cust = itor.next();
				if (Constants.DATATYPE_REGION.equals(cust.getDataType())) {
					if (addressId.equals(cust.getAddressId())) {
						lstCust.add(cust);
						if (0 == cnt) {
							isTop = true;
						}
						if ((lstAll.size() - storeCnt - 1) == cnt) {
							isEnd = true;
						}
						break;
					}
					cnt++;
				}
			}
		}
		returns.putData("lstAddr", lstCust);
		returns.putData("isEnd", isEnd);
		returns.putData("isTop", isTop);
		/*
		 * } catch (BriException e) { returns =
		 * Tools.getExceptionControllerRetruns(e); }
		 */
		return returns.generateJsonData();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "favouriteAddress/getPackedCustAddrInfo.json", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String getPackedCustAddrInfo(HttpServletRequest request) throws Exception {
		returns = new ServiceReturns();

		String addressId = request.getParameter("addressId");
		// String dataType = request.getParameter("dataType");

		// returns =
		// customizationAddressService.getPackedCustAddrInfo(addressId);
		// List<CustomizationAddressInfo> lstCust =
		// (List<CustomizationAddressInfo>)returns.getData().get("lstAddr");
		List<CustomizationAddressInfo> lstCust = new ArrayList<CustomizationAddressInfo>();
		List<CustomizationAddressInfo> lstAll = (List<CustomizationAddressInfo>) request
				.getSession().getAttribute("lstAddr");
		boolean isEnd = false;
		boolean isTop = false;
		if (!CollectionUtils.isEmpty(lstAll)) {
			int cnt = 0;
			int storeCnt = getCntOfStore(lstAll);
			Iterator<CustomizationAddressInfo> itor = lstAll.iterator();
			while (itor.hasNext()) {
				CustomizationAddressInfo cust = itor.next();
				
				if (addressId.equals(cust.getAddressId())) {
					lstCust.add(cust);
					if(0 == cnt){
						isTop = true;
					}
					if((storeCnt-1) == cnt){
						isEnd = true;
					}
				}

				if (addressId.equals(cust.getParentId())) {
					lstCust.add(cust);
				}
				if(Constants.DATATYPE_STORE.equals(cust.getDataType())){
					cnt++;
				}
			}
		}
		returns.putData("lstAddr", lstCust);
		returns.putData("isTop", isTop);
		returns.putData("isEnd", isEnd);
		
		request.getSession().setAttribute("lstAddrBak", lstCust);
		
		return returns.generateJsonData();
	}
	
	private int getCntOfStore(List<CustomizationAddressInfo> lstAll){
		int cnt = 0;
		if (!CollectionUtils.isEmpty(lstAll)) {
			Iterator<CustomizationAddressInfo> itor = lstAll.iterator();
			while (itor.hasNext()) {
				CustomizationAddressInfo cust = itor.next();
				if(Constants.DATATYPE_STORE.equals(cust.getDataType())){
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	@RequestMapping(value = "/favouriteAddress/getSelfFavtAddr.json", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String getSelfFavtAddr(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String currentPage = request.getParameter("page");
		try {
			//UserInfo user = CacheUtils.getLUserInfo_MB(request, sessionId);
			if (!StringUtils.isEmpty(userId)) {
				// userId = user.getUserId();
				FavouriteAddress fav = new FavouriteAddress();
				fav.setUserId(userId);

				if(!StringUtils.isEmpty(currentPage)){
					returns = favouriteAddressService.getMyselfFavAddrInfo(fav,Integer.parseInt(currentPage));
				}
			} else {
				return Tools.getErrorsRetruns(Constants.ERR_NOT_LOGIN)
						.generateJsonData();
			}

		} catch (BriException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		/*try {
			FavouriteAddress fav = new FavouriteAddress();
			fav.setUserId("200002");

			returns = favouriteAddressService.getMyselfFavAddrInfo(fav);

			} catch (BriException e) {
				returns = Tools.getExceptionControllerRetruns(e);
			}*/
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/favouriteAddress/getCommFavtAddr.json", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String getCommFavtAddr(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String currentPage = request.getParameter("page");
		String address = request.getParameter("address");
		String criterias = request.getParameter("criterias");
		try {
			//UserInfo user = CacheUtils.getLUserInfo_MB(request, sessionId);
			if (!StringUtils.isEmpty(userId)) {
				// userId = user.getUserId();
				SearchCriteria criteria = new SearchCriteria();
				criteria.setUserId(userId);
				criteria.setAddressInfo(address);
				//注意，如果查询条件包含":",说明是按地址查询
				if(-1 != criterias.indexOf(",")){
					String []tmp = StringUtils.split(criterias,",");
					criteria.setProvinceId(tmp[0]);
					criteria.setCityId(tmp[1]);
					criteria.setAreaId(tmp[2]);
				}else{
					criteria.setHotName(criterias);
				}

				if(!StringUtils.isEmpty(currentPage)){
					criteria.setCurrentPage(Integer.parseInt(currentPage));
					returns = favouriteAddressService.getCommCusddrs(criteria);
				}
			} else {
				return Tools.getErrorsRetruns(Constants.ERR_NOT_LOGIN)
						.generateJsonData();
			}

		} catch (BriException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		/*try {
		returns = favouriteAddressService.getCommCusddrs("200003");

		} catch (BriException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}*/
		return returns.generateJsonData();
	}
	
	
	@RequestMapping(value = "/favouriteAddress/getCommFavtAddrNoLimit.json", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String getCommFavtAddrNoLimit(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String address = request.getParameter("address");
		try {
			//UserInfo user = CacheUtils.getLUserInfo_MB(request, sessionId);
			if (!StringUtils.isEmpty(userId)) {
				// userId = user.getUserId();
				SearchCriteria criteria = new SearchCriteria();
				criteria.setUserId(userId);
				criteria.setAddressInfo(address);
				criteria.setLimitaion(false);
                returns = favouriteAddressService.getCommCusddrs(criteria);
			} else {
				return Tools.getErrorsRetruns(Constants.ERR_NOT_LOGIN)
						.generateJsonData();
			}

		} catch (BriException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		/*try {
		returns = favouriteAddressService.getCommCusddrs("200003");

		} catch (BriException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}*/
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/addFavrouite.json", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String addFavrouite(HttpServletRequest request) {
		ControllerReturns returns = new ControllerReturns();

		String userId = request.getParameter("userId");
		String addressId = request.getParameter("addressId");
		try {
			//UserInfo user = CacheUtils.getLUserInfo_MB(request, sessionId);
			if (!StringUtils.isEmpty(userId)) {
				// userId = user.getUserId();
				FavouriteAddress fav = new FavouriteAddress();
				fav.setUserId(userId);
				fav.setAddressId(addressId);
				fav.setSource(Constants.FAV_COL);
				fav.setCreateTime(Tools.getTime());

				FavouriteAddress fav1 = favouriteAddressService.getFavAddr(fav);
				if (null != fav1) {

					favouriteAddressService.deleteFavAddr(fav1);

					returns.getData().put("flag", "0");
				} else {
					favouriteAddressService.save(fav);
					returns.getData().put("flag", "1");
				}

			} else {
				return Tools.getErrorsRetruns(Constants.ERR_NOT_LOGIN)
						.generateJsonData();
			}
		} catch (Exception e) {
			logger.error("数据库异常!", e);
			//e.printStackTrace();
			returns = Tools.getExceptionServiceReturns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/favouriteAddress/removeAddr.json", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String removeAddrInfo(HttpServletRequest request) {
		ControllerReturns returns = new ControllerReturns();
		try {
			String id = request.getParameter("Id");
			FavouriteAddress fav = new FavouriteAddress();
			fav.setId(Integer.parseInt(id));
			
			favouriteAddressService.deleteFavAddr(fav);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/favouriteAddress/publishAddrData.json", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String publishAddrData(HttpServletRequest request) {
		try {
			String ids = request.getParameter("ids");
			String[] addressIds = null;
			if (!StringUtils.isEmpty(ids)) {
				addressIds = StringUtils.split(ids, ",");
			}

			// String[] addressIds = new
			// String[]{"20148713239790","20148801950793","20148801950544"};
			returns = customizationAddressService.publishAddrData(addressIds);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "favouriteAddress/deleteCustAddrInfo.json", produces = "text/html;charset=utf-8")
	@Transactional
	public 	@ResponseBody
	String deleteCustAddrInfo(HttpServletRequest request) {
		try {
			String ids = request.getParameter("ids");
			String deleteType = request.getParameter("deleteType");
			
			AdminInfo adminInfo = CacheUtils.getAdmInfo(request);
			String isAdmin = request.getSession().getAttribute("isAdmin").toString();
			
			String[] addressId = null;
			if(!StringUtils.isEmpty(ids)){
				addressId = StringUtils.split(ids,",");
				if(deleteType.equals(Constants.FORCEDELETE)){
					if(adminInfo.getAccountId().equals(Constants.ADMIN_ID)){
						returns = customizationAddressService.ForceDeleteCustAddr(addressId);
					}else{
						returns.setSuccess(true);
						returns.setMessageType("warning");
						returns.putData("msg", "您没有权限操作此项！");
					}
				}else if(deleteType.equals(Constants.NORMALDELETE)){
					returns = customizationAddressService.delete(addressId);
				}
			}
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	//比较两个集合。取出所有将要新增、修改、删除的对象
	public  Map<String,List<CustomizationAddressInfo>> getMixdData(List<CustomizationAddressInfo> originalList,List<CustomizationAddressInfo> newList){
		Map<String,List<CustomizationAddressInfo>> map = new HashMap<String,List<CustomizationAddressInfo>>();
		
		Map<String,CustomizationAddressInfo> originalMap = new HashMap<String, CustomizationAddressInfo>();
		Map<String,CustomizationAddressInfo> newMap = new HashMap<String, CustomizationAddressInfo>();
		
		List<CustomizationAddressInfo> toDeleteList = new ArrayList<CustomizationAddressInfo>();
		List<CustomizationAddressInfo> toUpdatList = new ArrayList<CustomizationAddressInfo>();
		List<CustomizationAddressInfo> toInsertList = new ArrayList<CustomizationAddressInfo>();
		Iterator<CustomizationAddressInfo> itor = originalList.iterator();
		while(itor.hasNext()){
			CustomizationAddressInfo cust = itor.next();
			originalMap.put(cust.getAddressId(), cust);
		}
		
		itor = newList.iterator();
		Integer id = null;
		while(itor.hasNext()){
			CustomizationAddressInfo cust = itor.next();
			newMap.put(cust.getAddressId(), cust);
			id = cust.getId();
			if(null  == id){
				toInsertList.add(cust);
			}
		}
		
		Set<Map.Entry<String,CustomizationAddressInfo>> set = originalMap.entrySet();
		
		Iterator<Map.Entry<String,CustomizationAddressInfo>> itor1 = set.iterator();
		Entry<String,CustomizationAddressInfo> entry = null;
		String addressIdKey = "";
		CustomizationAddressInfo originalValue = null;
		CustomizationAddressInfo newMapValue = null;
		while(itor1.hasNext()){
			entry = itor1.next();
			addressIdKey = entry.getKey();
			originalValue = entry.getValue();
			
			id = originalValue.getId();
			if(newMap.containsKey(addressIdKey)){
				newMapValue = newMap.get(addressIdKey);
				if(!(newMapValue).equals(originalValue)){
					toUpdatList.add(newMapValue);
				}
			}else{
				toDeleteList.add(originalValue);
			}
			
		}
		
		map.put("toDeleteList", toDeleteList);
		map.put("toUpdatList", toUpdatList);
		map.put("toInsertList", toInsertList);
		
		return map;
	}
	
	
	
	@RequestMapping(value = "favouriteAddress/getCustAddrInfo_M.json", produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody
	String getCustAddrInfo_M(HttpServletRequest request) {
		returns = new ServiceReturns();
		try {
			String addressId = request.getParameter("addressId");

			returns = favouriteAddressService.getCusAddrInfo(addressId);

		} catch (BriException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
}
