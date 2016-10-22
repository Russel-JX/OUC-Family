package com.igate.izone.service;

import java.util.List;

import com.igate.izone.dto.EmployeeDTO;

public interface EmployeeServiceIntf {
	int createNewEmployee(EmployeeDTO employeeDTO);
	int createUploadEmployees(List<EmployeeDTO> employeeList);
	List<EmployeeDTO> findAllEmployee();
	EmployeeDTO findEmployeeByEmpId(String empID);
	int updateEmployeeInfo(EmployeeDTO employeeDTO);
	int deleteEmployees(String[] empIds);
}
