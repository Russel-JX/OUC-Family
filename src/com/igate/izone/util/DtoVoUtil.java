package com.igate.izone.util;

import com.igate.izone.dto.EmployeeDTO;
import com.igate.izone.vo.EmployeeRes;
/**
 * DTO层数据域BO层数据的相互转化工具
 * @author Yicheng Zhou
 *
 */
public class DtoVoUtil {
	/**
	 * EmployeeDTO -> EmployeeRes
	 * @param employeeDTO 待转化的DTO层数据对象
	 * @return 转化成功后的VO层数据对象
	 */
	public static EmployeeRes employeeDTO2EmployeeRes(EmployeeDTO employeeDTO){
		if(employeeDTO == null){
			return null;
		}
		EmployeeRes employeeRes = new EmployeeRes();
		employeeRes.setEmpID(employeeDTO.getEmpID());
		employeeRes.setEmp_CN_name(employeeDTO.getEmp_CN_name());
		employeeRes.setEmp_EN_name(employeeDTO.getEmp_EN_name());
		employeeRes.setEmpType(employeeDTO.getEmpType());
		employeeRes.setEngageDate(DateUtil.date2String(employeeDTO.getEngageDate()));
		employeeRes.setEmp_head_portrait(employeeDTO.getEmp_head_portrait());
		return employeeRes;
	}
}
