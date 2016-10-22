package com.igate.izone.dao;

import java.util.List;

import com.igate.izone.dto.EmployeeDTO;

public interface EmployeeDaoIntf {
	List<EmployeeDTO> findAllEmployee();
	EmployeeDTO findEmployeeByEmpId(String empID);
	int createNewEmployee(EmployeeDTO employeeDTO);
	int createUploadEMployees(List<EmployeeDTO> employeeList);
	int updateEmployeeInfo(EmployeeDTO employeeDTO);
	int deleteEmployees(String[] empIds);
}
