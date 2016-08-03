package com.brilliance.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.brilliance.base.BaseController;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.ExpressInfo;
import com.brilliance.service.ExpressInfoService;
import com.brilliance.service.OrderInfoService;
import com.brilliance.service.ProvincesInfoService;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

@Controller
@Scope("request")
public class ExpressInfoController extends BaseController{

	@Resource
	private ExpressInfoService expressInfoservice;
	
	@Resource
	private OrderInfoService orderInfoService;
	
	@Resource
    private ProvincesInfoService provincesInfoService;
	
	
	@RequestMapping(value = "/getDateList", produces = "text/html;charset=utf-8")
	public @ResponseBody String getDateList(HttpServletRequest request){
		try {
			String f_provinceId = request.getParameter("f_province");
			String f_cityId = request.getParameter("f_city");
			String f_areaId = request.getParameter("f_area");
			
			String t_provinceId = request.getParameter("t_province");
			String t_cityId = request.getParameter("t_city");
			String t_areaId = request.getParameter("t_area");
			ExpressInfo info = new ExpressInfo();
			info.setF_provinceId(f_provinceId);
			info.setF_cityId(f_cityId);
			info.setF_areaId(f_areaId);
			
			info.setT_provinceId(t_provinceId);
			info.setT_cityId(t_cityId);
			info.setT_areaId(t_areaId);
			returns = expressInfoservice.getExpressInfo(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returns.generateJsonData();
	}
	
	@RequestMapping(value="home")
	public String forward(HttpServletRequest request){
		return "home";
	}
	
	@RequestMapping(value="admin_home")
	public String forwardAdmin(HttpServletRequest request){
		return "admin_home";
	}
	
	@RequestMapping(value="forwardToExpressManage")
	public String forwardToExpressManage(HttpServletRequest request){
		return "express/expressList";
	}
	
	@RequestMapping(value = "/express/getAllExplst", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String getAllExplst() {
		try {
			returns = expressInfoservice.getAllExpList();
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/express/saveExpress", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String saveExpress(HttpServletRequest request) {
		try {
			String name = request.getParameter("name");
			String postId = request.getParameter("postId");
			String telephone = request.getParameter("telephone");
			String mobile = request.getParameter("expressMobile");
			String servieLine = request.getParameter("serviceLine");
			
			int code = expressInfoservice.getMaxExpressCode();
			if(0 == code){
				code = 10000;
			}else{
				code += 1;
			}
			
			ExpressInfo info = new ExpressInfo();
			info.setName(name);
			info.setPostId(postId);
			info.setTelephone(telephone);
			info.setMobile(mobile);
			info.setServiceLine(servieLine);
			info.setExpressCode(String.valueOf(code));
			
			returns = expressInfoservice.saveExpress(info);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/express/editExpress", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String editExpress(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String postId = request.getParameter("postId");
			String telephone = request.getParameter("telephone");
			String mobile = request.getParameter("expressMobile");
			String servieLine = request.getParameter("serviceLine");
			
			ExpressInfo info = new ExpressInfo();
			info.setId(Integer.parseInt(id));
			info.setName(name);
			info.setPostId(postId);
			info.setTelephone(telephone);
			info.setMobile(mobile);
			info.setServiceLine(servieLine);
			
			returns = expressInfoservice.editExpress(info);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/express/removeExpress", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String removeExpress(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			
			ExpressInfo info = new ExpressInfo();
			info.setId(Integer.parseInt(id));
			
			returns = expressInfoservice.removeExpress(info);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	//upload Logo
	@RequestMapping(value = "/express/expressLogoUpload",produces = "text/html;charset=utf-8")
	public @ResponseBody String expressLogoUpload(HttpServletRequest request,
			@RequestParam("fileUpload") MultipartFile fileUpload) throws Exception {
		String expressId = request.getParameter("expressId");
//		String filePath = request.getSession().getServletContext()
//				.getRealPath(Constants.TEMP_PATH);
		String filePath = request.getSession().getServletContext().getRealPath("/images/logo");
		//logo�洢λ�ù̶�
//		String filePath = "d:/brilliance_logo";
		try {
			//suffix
			String fileOrignalName = fileUpload.getOriginalFilename();
			String suffix = fileOrignalName.substring(fileOrignalName.lastIndexOf("."),fileOrignalName.length());
			//unique File name
			String fileName = String.valueOf(System.nanoTime())+suffix;
			
			System.out.println("---getContextPath---"+request.getSession().getServletContext().getContextPath()+"------filePath="+filePath+"---fileOrignalName"+fileOrignalName+"---fileName"+fileName);
			
			//�����ļ�
			File file = new File(filePath+"\\"+fileName);
			byte[] fileBytes;
			fileBytes = fileUpload.getBytes();
			FileCopyUtils.copy(fileBytes, file);
			
			ExpressInfo info = new ExpressInfo();
			info.setId(Integer.parseInt(expressId));
			info.setLogoUrl("/images/logo/"+fileName);
			
			//DB�����ļ�·��
			returns = expressInfoservice.saveExpressLogo(info);
		} 
		catch (IOException e) {
			e.printStackTrace();
			returns = Tools.getExceptionControllerRetruns(e);
		}
		catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
	
	
	public void saveOrder(){
		//Json ss = new Gson();
		//ss.
		//orderInfoService.saveOrderInfo(orderInfo);
	}
}
