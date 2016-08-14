package com.brilliance.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brilliance.base.BaseController;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.DeliverAddressInfo;
import com.brilliance.po.ExpressInfo;
import com.brilliance.po.OrderInfo;
import com.brilliance.po.PostAddressInfo;
import com.brilliance.po.UserInfo;
import com.brilliance.service.DeliverAddressInfoService;
import com.brilliance.service.ExpressInfoService;
import com.brilliance.service.OrderInfoService;
import com.brilliance.service.PostAddressInfoService;
import com.brilliance.utils.CacheUtils;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;
import com.mysql.jdbc.StringUtils;

@Controller
@Scope("request")
public class OrderController extends BaseController {
	@Resource
	private OrderInfoService orderInfoService;

	@Resource
	private ExpressInfoService expressInfoService;
	
	@Resource
	private PostAddressInfoService postAddressInfoService;
	
	@Resource
	private DeliverAddressInfoService deliverAddressInfoService;

	//返回发快递首页
	@RequestMapping(value = "/returnHome", produces = "text/html;charset=utf-8")
	public ModelAndView returnHome(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("home");
		boolean returnFormOrderFlag = true;
		String f_provinceId = request.getParameter("f_provinceId");
		String f_cityId = request.getParameter("f_cityId");
		String f_areaId = request.getParameter("f_areaId");
		String t_provinceId = request.getParameter("t_provinceId");
		String t_cityId = request.getParameter("t_cityId");
		String t_areaId = request.getParameter("t_areaId");
		
		view.getModel().put("returnFormOrderFlag", returnFormOrderFlag);
		view.getModel().put("f_provinceId", f_provinceId);
		view.getModel().put("f_cityId", f_cityId);
		view.getModel().put("f_areaId", f_areaId);
		view.getModel().put("t_provinceId", t_provinceId);
		view.getModel().put("t_cityId", t_cityId);
		view.getModel().put("t_areaId", t_areaId);
		
//		try {
//			List<ExpressInfo> list = expressInfoService.getAllExpress();
//			view.getModel().put("expresslst", list);
//
//		} catch (BriException e) {
//			e.printStackTrace();
//		}
		return view;// 直接跳转到jsp/home.jsp页面
	}
	
	// 手机端返回JSON数据
	@RequestMapping(value = "/order/detail", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String getOrderInfo(HttpServletRequest request) {
		try {
			String ID = request.getParameter("ID");
			String orderCode = request.getParameter("orderCode");
			String userId = request.getParameter("userId");

			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setId(Integer.valueOf(ID));
			orderInfo.setOrderCode(orderCode);
			orderInfo.setUserId(userId);
			returns = orderInfoService.getOrderInfo(orderInfo);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}

	@RequestMapping(value = "/order", produces = "text/html;charset=utf-8")
	public ModelAndView index(HttpServletRequest request) {
		// 调用service做相关的业务操作
		// 调用Tools.converToModelAndView(returns);将数据存到请求上下文中,供前台页面jstl,或者jsp脚本获取
		ModelAndView view = new ModelAndView("order/order");
		try {
			List<ExpressInfo> list = expressInfoService.getAllExpress();
			view.getModel().put("expresslst", list);

		} catch (BriException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;// 直接跳转到jsp/order/index.jsp页面
	}
	
	@RequestMapping(value = "/admin_order", produces = "text/html;charset=utf-8")
	public ModelAndView adminIndex(HttpServletRequest request) {
		// 调用service做相关的业务操作
		// 调用Tools.converToModelAndView(returns);将数据存到请求上下文中,供前台页面jstl,或者jsp脚本获取
		ModelAndView view = new ModelAndView("order/admin_order");
		try {
			List<ExpressInfo> list = expressInfoService.getAllExpress();
			view.getModel().put("expresslst", list);

		} catch (BriException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;// 直接跳转到jsp/order/index.jsp页面
	}

	@RequestMapping(value = "/newOrder", produces = "text/html;charset=utf-8")
	public ModelAndView newOrder(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("order/newOrder");
		OrderInfo order = new OrderInfo();
		
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String userId = user.getUserId();

		String expressName = request.getParameter("expressName");
		String expressCode = request.getParameter("expressCode");
		String fromAddr = request.getParameter("fromAddr");
		String toAddr = request.getParameter("toAddr");
		String f_provinceId = request.getParameter("f_provinceId");
		String f_cityId = request.getParameter("f_cityId");
		String f_areaId = request.getParameter("f_areaId");
		String t_provinceId = request.getParameter("t_provinceId");
		String t_cityId = request.getParameter("t_cityId");
		String t_areaId = request.getParameter("t_areaId");
		/*String charge = Tools.isNullAndEmpty(request.getParameter("charge"),
				"0");*/
		String deliverDays = Tools.isNullAndEmpty(
				request.getParameter("deliverDays"), "0");

		order.setExpressCode(expressCode);
		order.setExpressName(expressName);
		order.setFrom_addr(fromAddr);
		order.setTo_addr(toAddr);
		order.setF_provinceId(f_provinceId);
		order.setF_cityId(f_cityId);
		order.setF_areaId(f_areaId);
		order.setT_provinceId(t_provinceId);
		order.setT_cityId(t_cityId);
		order.setT_areaId(t_areaId);
		//order.setAmount(Float.parseFloat(charge));
		order.setDeliverDays(Integer.parseInt(deliverDays));
		view.getModel().put("orderInfo", order);
		
		try {
			List<PostAddressInfo> postAddressInfos = postAddressInfoService.getPostAddressesByUserRoute(f_provinceId,f_cityId,f_areaId,expressCode,userId);
			List<DeliverAddressInfo> deliverAddressInfos = deliverAddressInfoService.getDeliverAddressesByUserRoute(t_provinceId,t_cityId,t_areaId,expressCode, userId);
			view.getModel().put("postAddressInfos", postAddressInfos);
			view.getModel().put("deliverAddressInfos", deliverAddressInfos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}
	
//	@RequestMapping(value = "/address/showPostAddresses", produces = "text/html;charset=utf-8")
//	public @ResponseBody
//	String showPostAddresses(HttpServletRequest request) {
//		try {
//			HttpSession session = request.getSession();
//			UserInfo user = (UserInfo) session.getAttribute("userInfo");
//			String userId = user.getUserId();
//			
//			String expressCode = request.getParameter("expressCode");
//
//			returns = postAddressInfoService.getPostAddressesByUserIdExpressCode2(expressCode, userId);
//		} catch (Exception e) {
//			returns = Tools.getExceptionControllerRetruns(e);
//		}
//		return returns.generateJsonData();
//	}
	
	@RequestMapping(value = "/address/savePostAddress", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String savePostAddress(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			String userId = user.getUserId();
			
			String expressCode = request.getParameter("expressCode");
			String f_Contact = request.getParameter("f_Contact");
			String f_companyName = request.getParameter("f_companyName");
			String fromAddr = request.getParameter("fromAddr");
			String province =  request.getParameter("f_provinceId");//fromAddr.split(",")[0];
			String city =  request.getParameter("f_cityId");//fromAddr.split(",")[1];
			String area =  request.getParameter("f_areaId");//fromAddr.split(",")[2];
			
			String f_streetName = request.getParameter("f_streetName");
			String f_houseNo = request.getParameter("f_houseNo");
			String f_mobileNo = request.getParameter("f_mobileNo");
			

			PostAddressInfo postAddressInfo = new PostAddressInfo();
			postAddressInfo.setExpressCode(expressCode);
			postAddressInfo.setUserId(userId);
			postAddressInfo.setPostName(f_Contact);
			postAddressInfo.setCompanyName(f_companyName);
			postAddressInfo.setProvince(province);
			postAddressInfo.setCity(city);
			postAddressInfo.setArea(area);
			postAddressInfo.setStreetName(f_streetName);
			postAddressInfo.setMobile(f_mobileNo);
			postAddressInfo.setTailAddress(f_houseNo);
			postAddressInfo.setAddressDetail(fromAddr+","+f_streetName+","+f_houseNo);
			postAddressInfo.setCreateTime(Tools.getTime());
			postAddressInfo.setStatus(1);
			
			System.out.println("expressCode="+expressCode+" userId="+postAddressInfo.getUserId()+" f_Contact="+f_Contact+" f_companyName="+f_companyName+" province="+
					province+" city="+city+" area="+area+" f_streetName="+f_streetName+" f_mobileNo="+f_mobileNo+" f_houseNo="+f_houseNo+
					" AddressDetail="+(fromAddr+f_streetName+f_houseNo)+" CreateTime="+Tools.getTime()+" status="+postAddressInfo.getStatus());

			returns = postAddressInfoService.saveAddressInfo(postAddressInfo);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/address/updateAddress", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String updateAddress(HttpServletRequest request) {
		System.out.println("..............");
		try {
			HttpSession session = request.getSession();
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			String userId = user.getUserId();
			
			String id = request.getParameter("id");
			String expressCode = request.getParameter("expressCode");
			String f_Contact = request.getParameter("f_Contact");
			String f_companyName = request.getParameter("f_companyName");
			String fromAddr = request.getParameter("fromAddr");
//			String province = fromAddr.split(",")[0];
//			String city = fromAddr.split(",")[1];
//			String area = fromAddr.split(",")[2];
			String province =  request.getParameter("f_provinceId");
			String city =  request.getParameter("f_cityId");
			String area =  request.getParameter("f_areaId");
			
			String f_streetName = request.getParameter("f_streetName");
			String f_houseNo = request.getParameter("f_houseNo");
			String f_mobileNo = request.getParameter("f_mobileNo");
			

			PostAddressInfo postAddressInfo = new PostAddressInfo();
			postAddressInfo.setId(Integer.parseInt(id));
			postAddressInfo.setExpressCode(expressCode);
			postAddressInfo.setUserId(userId);
			postAddressInfo.setPostName(f_Contact);
			postAddressInfo.setCompanyName(f_companyName);
			postAddressInfo.setProvince(province);
			postAddressInfo.setCity(city);
			postAddressInfo.setArea(area);
			postAddressInfo.setStreetName(f_streetName);
			postAddressInfo.setMobile(f_mobileNo);
			postAddressInfo.setTailAddress(f_houseNo);
			postAddressInfo.setAddressDetail(fromAddr+","+f_streetName+","+f_houseNo);
			postAddressInfo.setCreateTime(Tools.getTime());
			postAddressInfo.setStatus(1);
			
			System.out.println("expressCode="+postAddressInfo.getExpressCode()+" userId="+postAddressInfo.getUserId()+" f_Contact="+postAddressInfo.getPostName()+" f_companyName="+postAddressInfo.getCompanyName()+" province="+
					postAddressInfo.getProvince()+" city="+postAddressInfo.getCity()+" area="+postAddressInfo.getArea()+" f_streetName="+postAddressInfo.getStreetName()+" f_mobileNo="+postAddressInfo.getMobile()+" f_houseNo="+postAddressInfo.getTailAddress()+
					" AddressDetail="+(fromAddr+f_streetName+f_houseNo)+" CreateTime="+Tools.getTime()+" status="+postAddressInfo.getStatus());

			returns = postAddressInfoService.updateAddressInfo(postAddressInfo);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/address/deletePostAddress", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String deletePostAddress(HttpServletRequest request) {
		try {
			String addressCode = request.getParameter("addressCode");

			PostAddressInfo postAddressInfo = new PostAddressInfo();
			postAddressInfo.setId(Integer.parseInt(addressCode));
			
			returns = postAddressInfoService.deleteAddressInfo(postAddressInfo);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
//	@RequestMapping(value = "/address/showDeliverAddresses", produces = "text/html;charset=utf-8")
//	public @ResponseBody
//	String showDeliverAddresses(HttpServletRequest request) {
//		try {
//			HttpSession session = request.getSession();
////			UserInfo user = (UserInfo) session.getAttribute("userInfo");
////			String userId = user.getUserId();
//			
//			String expressCode = request.getParameter("expressCode");
//
//			returns = deliverAddressInfoService.getDeliverAddressesByUserIdExpressCode(expressCode, "200007");//userId
//		} catch (Exception e) {
//			returns = Tools.getExceptionControllerRetruns(e);
//		}
//		return returns.generateJsonData();
//	}
	
	String saveDeliverAddress(DeliverAddressInfo deliverAddressInfo) {
		try {
			
			
//			System.out.println("expressCode="+expressCode+" userId="+deliverAddressInfo.getUserId()+" f_Contact="+f_Contact+" f_companyName="+f_companyName+" province="+
//					province+" city="+city+" area="+area+" f_streetName="+f_streetName+" f_mobileNo="+f_mobileNo+" f_houseNo="+f_houseNo+
//					" AddressDetail="+(fromAddr+f_streetName+f_houseNo)+" CreateTime="+Tools.getTime()+" status="+deliverAddressInfo.getStatus());

			deliverAddressInfoService.saveAddressInfo(deliverAddressInfo);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/address/updateDeliverAddress", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String updateDeliverAddress(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			String userId = user.getUserId();
			
			String expressCode = request.getParameter("expressCode");
			String f_Contact = request.getParameter("f_Contact");
			String f_companyName = request.getParameter("f_companyName");
			String fromAddr = request.getParameter("fromAddr");
			String province = fromAddr.split(",")[0];
			String city = fromAddr.split(",")[1];
			String area = fromAddr.split(",")[2];
			
			String f_telephone = request.getParameter("f_telephone");
			String f_streetName = request.getParameter("f_streetName");
			String f_houseNo = request.getParameter("f_houseNo");
			String f_mobileNo = request.getParameter("f_mobileNo");
			

			DeliverAddressInfo deliverAddressInfo = new DeliverAddressInfo();
			deliverAddressInfo.setExpressCode(expressCode);
			deliverAddressInfo.setUserId(userId);
//			deliverAddressInfo.setUserId("200007");//Russel的用户id
			deliverAddressInfo.setDeliverName(f_Contact);
			deliverAddressInfo.setCompanyName(f_companyName);
			deliverAddressInfo.setProvince(province);
			deliverAddressInfo.setCity(city);
			deliverAddressInfo.setArea(area);
			deliverAddressInfo.setStreetName(f_streetName);
			deliverAddressInfo.setMobile(f_mobileNo);
			deliverAddressInfo.setTelephone(f_telephone);
			deliverAddressInfo.setTailAddress(f_houseNo);
			deliverAddressInfo.setAddressDetail(fromAddr+","+f_streetName+","+f_houseNo);
			deliverAddressInfo.setCreateTime(Tools.getTime());
			deliverAddressInfo.setStatus(1);
			
			System.out.println("expressCode="+expressCode+" userId="+deliverAddressInfo.getUserId()+" f_Contact="+f_Contact+" f_companyName="+f_companyName+" province="+
					province+" city="+city+" area="+area+" f_streetName="+f_streetName+" f_mobileNo="+f_mobileNo+" f_houseNo="+f_houseNo+
					" AddressDetail="+(fromAddr+f_streetName+f_houseNo)+" CreateTime="+Tools.getTime()+" status="+deliverAddressInfo.getStatus());

			returns = deliverAddressInfoService.updateAddressInfo(deliverAddressInfo);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/address/deleteDeliverAddress", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String deleteDeliverAddress(HttpServletRequest request) {
		try {
			String addressCode = request.getParameter("addressCode");

			DeliverAddressInfo deliverAddressInfo = new DeliverAddressInfo();
			deliverAddressInfo.setId(Integer.parseInt(addressCode));
			
			returns = deliverAddressInfoService.deleteAddressInfo(deliverAddressInfo);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/saveOrder", produces = "text/html;charset=utf-8")
	public ModelAndView saveOrder(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("order/order");
		OrderInfo order = new OrderInfo();

		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("userInfo");

		String expressName = request.getParameter("expressName");
		String expressCode = request.getParameter("expressCode");
		String fromAddr = request.getParameter("fromAddr");
		String toAddr = request.getParameter("toAddr");
		
		String newDeliverAddressFlag = request.getParameter("newDeliverAddressFlag");

		String charge = Tools.isNullAndEmpty(request.getParameter("charge"),
				"0");
		String deliverDays = Tools.isNullAndEmpty(
				request.getParameter("deliverDays"), "0");

//		String f_provinceId = request.getParameter("f_provinceId");
//		String f_cityId = request.getParameter("f_cityId");
//		String f_areaId = request.getParameter("f_areaId");
//		String f_contact = request.getParameter("f_Contact");
//		String f_companyName = request.getParameter("f_companyName");
//		String f_houseNo = request.getParameter("f_houseNo");
//		String f_streetName = request.getParameter("f_streetName");
//		String f_mobileNo = request.getParameter("f_mobileNo");
		String f_provinceId = request.getParameter("f_provinceId");
		String f_cityId = request.getParameter("f_cityId");
		String f_areaId = request.getParameter("f_areaId");
		String f_contact = request.getParameter("fPostName");
		String f_companyName = request.getParameter("fCompanyName");
		String f_houseNo = request.getParameter("fHouseNo");
		String f_streetName = request.getParameter("fStreetName");
		String f_mobileNo = request.getParameter("fMobileNo");
		String t_contact = request.getParameter("t_Contact");
		String t_companyName = request.getParameter("t_companyName");
		String t_houseNo = request.getParameter("t_houseNo");
		String t_streetName = request.getParameter("t_streetName");
		String t_mobileNo = request.getParameter("t_mobileNo");
		String t_provinceId = request.getParameter("t_provinceId");
		String t_cityId = request.getParameter("t_cityId");
		String t_areaId = request.getParameter("t_areaId");

		String extendNo = request.getParameter("extendNo");
		String officeNo = request.getParameter("officeNo");
		String zipCode = request.getParameter("zipCode");
		String cargoName = request.getParameter("cargoName");
		String comment = request.getParameter("comment");
		String t_officeNo = "";
		if (!Tools.isEmpty(zipCode)) {
			t_officeNo = zipCode;
		}
		if (!Tools.isEmpty(officeNo)) {
			t_officeNo += "-" + officeNo;
		}else{
			t_officeNo += "-" + "";
		}
		if (!Tools.isEmpty(extendNo)) {
			t_officeNo += "-" + extendNo;
		}else{
			t_officeNo += "-" + "";
		}

		DeliverAddressInfo deliverAddressInfo = new DeliverAddressInfo();
		deliverAddressInfo.setExpressCode(expressCode);
		deliverAddressInfo.setUserId(user.getUserId());
		deliverAddressInfo.setDeliverName(t_contact);
		deliverAddressInfo.setCompanyName(t_companyName);
		deliverAddressInfo.setProvince(t_provinceId);
		deliverAddressInfo.setCity(t_cityId);
		deliverAddressInfo.setArea(t_areaId);
		deliverAddressInfo.setStreetName(t_streetName);
		deliverAddressInfo.setMobile(t_mobileNo);
		deliverAddressInfo.setTelephone(t_officeNo);
		deliverAddressInfo.setTailAddress(t_houseNo);
		deliverAddressInfo.setAddressDetail(toAddr+","+t_streetName+","+t_houseNo);
		deliverAddressInfo.setCreateTime(Tools.getTime());
		deliverAddressInfo.setStatus(1);
		
		order.setExpressCode(expressCode);

		order.setT_provinceId(t_provinceId);
		order.setT_cityId(t_cityId);
		order.setT_areaId(t_areaId);

		order.setExpressName(expressName);
		order.setF_provinceId(f_provinceId);
		order.setF_cityId(f_cityId);
		order.setF_areaId(f_areaId);
		order.setF_contact(f_contact);
		order.setF_companyName(f_companyName);
		order.setF_mobileNo(f_mobileNo);
		order.setF_streetName(f_streetName);
		order.setF_houseNo(f_houseNo);
		order.setT_provinceId(t_provinceId);
		order.setT_cityId(t_cityId);
		order.setT_areaId(t_areaId);
		order.setT_contact(t_contact);
		order.setT_companyName(t_companyName);
		order.setT_streetName(t_streetName);
		order.setT_houseNo(t_houseNo);
		order.setT_mobileNo(t_mobileNo);
		order.setT_officeNo(t_officeNo);
		order.setCargoName(cargoName);
		order.setComment(comment);
		order.setFrom_addr(fromAddr);
		order.setTo_addr(toAddr);
		order.setSource(Constants.ORDER_SOURCE_SYS);
		order.setStatus(Constants.ORDER_STATUS_NEW);
		order.setAmount(Float.parseFloat(charge));
		order.setUserId(user.getUserId());
		order.setCreateTime(Tools.getTime());
		order.setDeliverDays(Integer.parseInt(deliverDays));
		order.setOrderCode(Tools.getTimeStr());

		try {
			if(Boolean.parseBoolean(newDeliverAddressFlag)==true){
				deliverAddressInfoService.saveAddressInfo(deliverAddressInfo);
			}
			orderInfoService.saveOrderInfo(order);
			
			List<ExpressInfo> list = expressInfoService.getAllExpress();
			view.getModel().put("expresslst", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		view.getModel().put("orderInfo", order);

		return view;
	}

	
	
	
	// 展示订单信息
	@RequestMapping(value = "/showOrders", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String showOrders(HttpServletRequest request) {
		try {
//			UserInfo user = CacheUtils.getUserInfo_PC(request);
			AdminInfo user = CacheUtils.getAdmInfo(request);

			String expressCode = request.getParameter("expressCode");
			String t_contact = request.getParameter("t_contact");
			String cargoName = request.getParameter("cargoName");

			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setExpressCode(expressCode);
			orderInfo.setT_contact(t_contact);
			orderInfo.setCargoName(cargoName);
//			orderInfo.setUserId(user.getUserId());
			orderInfo.setUserId(user.getAccountId());
			returns = orderInfoService.findOrders(orderInfo);

			return returns.generateJsonData();
		} catch (Exception e) {
			e.printStackTrace();
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
	}

	
	// 展示订单信息
		@RequestMapping(value = "/myOrders", produces = "text/html;charset=utf-8")
		public @ResponseBody
		String showOrdersForMobile(HttpServletRequest request) {
			try {
				//UserInfo user = CacheUtils.getLongUserInfo(request);

				String dvliverStatus = request.getParameter("dvliverStatus");
				String receiver = request.getParameter("receiver");
				//String userId = "";
				String startDt = request.getParameter("startDate");
				String endDt = request.getParameter("endDate");
				String userId = request.getParameter("userId");

				//UserInfo user = CacheUtils.getLUserInfo_MB(request,sessionId);
				if(StringUtils.isNullOrEmpty(userId)){
					return Tools.getErrorsRetruns(Constants.ERR_NOT_LOGIN).generateJsonData();
				}
				logger.debug(" ==== "+dvliverStatus+"|"+receiver+"|"+userId);
				OrderInfo orderInfo = new OrderInfo();
				if(!Tools.isEmpty(dvliverStatus)){
				    orderInfo.setStatus(Integer.parseInt(dvliverStatus));
				}
				if(!Tools.isEmpty(startDt)){
					orderInfo.setStartDt(Tools.parseToDate(startDt));
				}
				
				if(!Tools.isEmpty(endDt)){
					orderInfo.setEndDt(Tools.parseToDate(endDt));
				}
				orderInfo.setT_contact(receiver);
				orderInfo.setUserId(userId);
				returns = orderInfoService.findOrders(orderInfo);

				return returns.generateJsonData();
			} catch (Exception e) {
				return Tools.getExceptionControllerRetruns(e).generateJsonData();
			}
		}
		
	
	@RequestMapping(value = "/orderProcess", produces = "text/html;charset=utf-8")
	public ModelAndView orderProcess(HttpServletRequest request) {
		// 调用service做相关的业务操作
		// 调用Tools.converToModelAndView(returns);将数据存到请求上下文中,供前台页面jstl,或者jsp脚本获取
		ModelAndView view = new ModelAndView("order/orderProcess");
			view.getModel().put("map", Constants.order_STATUS);
		return view;// 直接跳转到jsp/order/index.jsp页面
	}
	
	
	@RequestMapping(value = "/showOrdersForAdmin", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String showOrdersForAdmin(HttpServletRequest request) {
		try {
			String status = request.getParameter("orderStatus");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			OrderInfo orderInfo = new OrderInfo();
			if(!Tools.isEmpty(status)){
			    orderInfo.setStatus(Integer.parseInt(status));
			}
			orderInfo.setStartDate(startDate);
			orderInfo.setEndDate(endDate);
			returns = orderInfoService.findAllOrders(orderInfo);

			return returns.generateJsonData();
		} catch (Exception e) {
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
	}
	
	// 查找订单详细
	@RequestMapping(value = "/order/show.json", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String orderTestModelAndView(HttpServletRequest request) {
		try {
			// String ID = request.getParameter("ID");
			String orderCode = request.getParameter("orderCode");
			String userId = request.getParameter("userId");

			OrderInfo orderInfo = new OrderInfo();
			// orderInfo.setId(Integer.valueOf(ID));
			orderInfo.setOrderCode(orderCode);
			orderInfo.setUserId(userId);// TODO 这个要改成从Session中获取
			returns = orderInfoService.getOrderInfo(orderInfo);
			return returns.generateJsonData();
		} catch (Exception e) {
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
	}

	
	/**
	 * 更新快递单状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/order/updateStatus", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String updateStatus(HttpServletRequest request) {
		returns = new ServiceReturns();
		String status = request.getParameter("status");
		String orderCode =  request.getParameter("orderCode");
		OrderInfo order = new OrderInfo();
		order.setOrderCode(orderCode);
		order.setStatus(Integer.parseInt(status));
		
		orderInfoService.updateStatus(order);
		
		return returns.generateJsonData();

	}

	/**
	 * 更新快递单号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/order/updateExpressSerNo", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String updateExpressSerNo(HttpServletRequest request) {
		returns = new ServiceReturns();
		String expressSerNo = request.getParameter("expressSerNo");
		String orderCode =  request.getParameter("orderCode");
		OrderInfo order = new OrderInfo();
		order.setOrderCode(orderCode);
		order.setExpressSerialNO(expressSerNo);
		
		orderInfoService.updateExpressSerNo(order);
		
		return returns.generateJsonData();
	}

	// 跳转示例二(String方式)
	// @RequestMapping(value = "/order/index", produces =
	// "text/html;charset=utf-8")
	// public
	// String orderIndex(HttpServletRequest request) {
	// try {
	// String ID = request.getParameter("ID");
	// String orderCode = request.getParameter("orderCode");
	// String userId = request.getParameter("userId");
	//
	// OrderInfo orderInfo = new OrderInfo();
	// orderInfo.setId(Integer.valueOf(ID));
	// orderInfo.setOrderCode(orderCode);
	// orderInfo.setUserId(userId);
	//
	// returns = orderInfoService.getOrderInfo(orderInfo);
	// } catch (Exception e) {
	// returns = Tools.getExceptionControllerRetruns(e);
	// }
	// return "index";//跳转到的地址为web.xml中配置的prefix下的index.jsp
	// }

	// 跳转示例三（String方式)
	// @RequestMapping(value = "/order/test", produces =
	// "text/html;charset=utf-8")
	// public
	// String orderTest(HttpServletRequest request) {
	// try {
	// String ID = request.getParameter("ID");
	// String orderCode = request.getParameter("orderCode");
	// String userId = request.getParameter("userId");
	//
	// OrderInfo orderInfo = new OrderInfo();
	// orderInfo.setId(Integer.valueOf(ID));
	// orderInfo.setOrderCode(orderCode);
	// orderInfo.setUserId(userId);
	//
	// returns = orderInfoService.getOrderInfo(orderInfo);
	// } catch (Exception e) {
	// returns = Tools.getExceptionControllerRetruns(e);
	// }
	// request.setAttribute("returns", returns);
	// return "order/index";//跳转到的地址为web.xml中配置的prefix下的order/index.jsp
	// }
}
