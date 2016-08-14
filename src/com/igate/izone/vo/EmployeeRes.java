package com.igate.izone.vo;

import java.sql.Date;

public class EmployeeRes {
	private String empID;//员工ID，如yz816343
	private String emp_CN_name;//原工中文名
	private String emp_EN_name;//员工英文名
	private String empType;//员工类型
	private String engageDate;//员工入职日期
	private String emp_head_portrait;//员工头像地址
	
	public EmployeeRes(){}
	
	public EmployeeRes(String empID, String emp_CN_name, String emp_EN_name,
			String empType, String engageDate) {
		super();
		this.empID = empID;
		this.emp_CN_name = emp_CN_name;
		this.emp_EN_name = emp_EN_name;
		this.empType = empType;
		this.engageDate = engageDate;
	}
	
	public EmployeeRes(String empID, String emp_CN_name, String emp_EN_name,
			String empType, String engageDate,String emp_head_portrait) {
		super();
		this.empID = empID;
		this.emp_CN_name = emp_CN_name;
		this.emp_EN_name = emp_EN_name;
		this.empType = empType;
		this.engageDate = engageDate;
		this.emp_head_portrait = emp_head_portrait;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmp_CN_name() {
		return emp_CN_name;
	}

	public void setEmp_CN_name(String emp_CN_name) {
		this.emp_CN_name = emp_CN_name;
	}

	public String getEmp_EN_name() {
		return emp_EN_name;
	}

	public void setEmp_EN_name(String emp_EN_name) {
		this.emp_EN_name = emp_EN_name;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getEngageDate() {
		return engageDate;
	}

	public void setEngageDate(String engageDate) {
		this.engageDate = engageDate;
	}

	public String getEmp_head_portrait() {
		return emp_head_portrait;
	}

	public void setEmp_head_portrait(String emp_head_portrait) {
		this.emp_head_portrait = emp_head_portrait;
	}

	@Override
	public String toString() {
		return "EmployeeRes [empID=" + empID + ", emp_CN_name=" + emp_CN_name
				+ ", emp_EN_name=" + emp_EN_name + ", empType=" + empType
				+ ", engageDate=" + engageDate + ", emp_head_portrait="
				+ emp_head_portrait + "]";
	}


	
}
