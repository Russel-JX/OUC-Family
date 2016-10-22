package com.igate.izone.dto;

import java.sql.Date;
/**
 * @author Yicheng Zhou
 *
 */
public class EmployeeDTO {
	private int id;//自增列
	private String empID;//员工ID，如yz816343
	private String emp_CN_name;//原工中文名
	private String emp_EN_name;//员工英文名
	private String empType;//员工类型
	private String password;//员工登录密码
	private Date engageDate;//员工入职日期
	private String gender;//员工性别
	private Date birthday;//员工生日
	private String birthPlace;//员工出生地
	private String constellation;//员工星座
	private String bloodType;//员工血型
	private String personalMail;//员工个人邮箱
	private String companyMail;//员工公司邮箱
	private String favourite;//员工爱好
	private String location;//员工工作地点
	private int deleteFlag;//删除标记
	private String emp_head_portrait;//员工头像
	
	public EmployeeDTO(){}
	
	public EmployeeDTO(int id, String empID, String emp_CN_name,String empType,
			String emp_EN_name, String password, Date engageDate,
			String gender, Date birthday, String birthPlace,
			String constellation, String bloodType, String personalMail,
			String companyMail, String favourite, String location,
			int deleteFlag, String emp_head_portrait) {
		super();
		this.id = id;
		this.empID = empID;
		this.emp_CN_name = emp_CN_name;
		this.emp_EN_name = emp_EN_name;
		this.empType = empType;
		this.password = password;
		this.engageDate = engageDate;
		this.gender = gender;
		this.birthday = birthday;
		this.birthPlace = birthPlace;
		this.constellation = constellation;
		this.bloodType = bloodType;
		this.personalMail = personalMail;
		this.companyMail = companyMail;
		this.favourite = favourite;
		this.location = location;
		this.deleteFlag = deleteFlag;
		this.emp_head_portrait = emp_head_portrait;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getEngageDate() {
		return engageDate;
	}

	public void setEngageDate(Date engageDate) {
		this.engageDate = engageDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getPersonalMail() {
		return personalMail;
	}

	public void setPersonalMail(String personalMail) {
		this.personalMail = personalMail;
	}

	public String getCompanyMail() {
		return companyMail;
	}

	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}

	public String getFavourite() {
		return favourite;
	}

	public void setFavourite(String favourite) {
		this.favourite = favourite;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getEmp_head_portrait() {
		return emp_head_portrait;
	}

	public void setEmp_head_portrait(String emp_head_portrait) {
		this.emp_head_portrait = emp_head_portrait;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", empID=" + empID + ", emp_CN_name="
				+ emp_CN_name + ", emp_EN_name=" + emp_EN_name + ", empType" + empType
				+ ", password=" 
				+ password + ", engageDate=" + engageDate + ", gender="
				+ gender + ", birthday=" + birthday + ", birthPlace="
				+ birthPlace + ", constellation=" + constellation
				+ ", bloodType=" + bloodType + ", personalMail=" + personalMail
				+ ", companyMail=" + companyMail + ", favourite=" + favourite
				+ ", location=" + location + ", deleteFlag=" + deleteFlag
				+ ", emp_head_portrait=" + emp_head_portrait + "]"+"\n";
	}
	
	
	
}
