package com.igate.izone.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igate.izone.dao.EmployeeDaoIntf;
import com.igate.izone.dto.EmployeeDTO;
import com.igate.izone.service.EmployeeServiceIntf;
/**
 * Employee的Service层,用来衔接controller与dao层
 * @author Yicheng Zhou
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeServiceIntf {

	@Resource
	private EmployeeDaoIntf employeeDao;
	
	/**
	 * @author Yicheng Zhou
	 * 增加员工操作
	 */
	@Override
	public int createNewEmployee(EmployeeDTO employeeDTO) {
		int updateLines = employeeDao.createNewEmployee(employeeDTO);
		System.out.println("updateLines : "+updateLines);
		return updateLines;
	}
	
	/**
	 * 通过Execl上传员工增加员工操作
	 */
	@Override
	public int createUploadEmployees(List<EmployeeDTO> employeeList) {
		int updateLines = employeeDao.createUploadEMployees(employeeList);
		System.out.println("updateLines : "+updateLines);
		return updateLines;
	}

	/**
	 * 查询所有Employee信息
	 */
	@Override
	public List<EmployeeDTO> findAllEmployee() {
		List<EmployeeDTO> employeeDTOList = employeeDao.findAllEmployee();
		System.out.println("size : "+employeeDTOList.size());
		return employeeDTOList;
	}

	/**
	 * 根据EmpId查询employee信息
	 */
	@Override
	public EmployeeDTO findEmployeeByEmpId(String empID) {
		EmployeeDTO employeeDTO = employeeDao.findEmployeeByEmpId(empID);
		return employeeDTO;
	}

	@Override
	/**
	 * 传入要更新的员工的信息
	 */
	public int updateEmployeeInfo(EmployeeDTO employeeDTO) {
		int updateLines = employeeDao.updateEmployeeInfo(employeeDTO);
		return updateLines;
	}

	/**
	 * 删除员工信息
	 * 传入一个带删除的员工id的数组
	 */
	@Override
	public int deleteEmployees(String[] empIds) {
		int flag = employeeDao.deleteEmployees(empIds);
		return flag;
	}

}
