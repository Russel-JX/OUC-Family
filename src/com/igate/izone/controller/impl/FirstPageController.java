package com.igate.izone.controller.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igate.izone.dto.EmployeeDTO;
import com.igate.izone.service.EmployeeServiceIntf;
import com.igate.izone.util.DtoVoUtil;
import com.igate.izone.util.JsonUtil;
import com.igate.izone.util.MD5Util;
import com.igate.izone.vo.ReturnMessage;

@Controller
@RequestMapping(value = "/firstPage")
public class FirstPageController {

	@Resource
	private EmployeeServiceIntf employeeService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	String userLogin(@RequestParam("empID") String empID,
			@RequestParam("password") String password,HttpSession session) {
		EmployeeDTO employeeDTO = employeeService.findEmployeeByEmpId(empID);
		Object returnMsg=null;
		if (employeeDTO == null) {
			returnMsg=new ReturnMessage("1",null);
			return JsonUtil.formatObject2Json(returnMsg);
		}
		String pwd = MD5Util.md5(password);
		if (pwd.equals(employeeDTO.getPassword())) {
			session.setAttribute("employee",employeeDTO);
			returnMsg=new ReturnMessage("0",DtoVoUtil.employeeDTO2EmployeeRes(employeeDTO));
			return JsonUtil.formatObject2Json(returnMsg);
		} else {
			returnMsg=new ReturnMessage("2",null);
			return JsonUtil.formatObject2Json(returnMsg);
		}
	}
}
