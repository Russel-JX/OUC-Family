package com.igate.izone.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.stereotype.Repository;

import com.igate.izone.dao.BaseDao;
import com.igate.izone.dao.EmployeeDaoIntf;
import com.igate.izone.dto.EmployeeDTO;
import com.igate.izone.util.MD5Util;
/**
 * 员工dao的实现层
 * @author Yicheng Zhou
 *
 */
@Repository
public class EmployeeDaoImpl extends BaseDao implements EmployeeDaoIntf {
	
	private static final String findAllEmployee_SQL = "select id,empID,emp_CN_name,emp_EN_name," +
			"password,engageDate,gender,birthday,birthPlace,constellation,bloodType,personalMail,companyMail," +
			"favourite,location,deleteFlag,emp_head_portrait,empType from employee";
	private static final String createNewEmployee_SQL = "insert into employee(empID,emp_CN_name,emp_EN_name," +
			"password,empType,engageDate,gender,birthday,birthPlace,constellation,bloodType,"+
			"personalMail,companyMail,favourite,location,deleteFlag,emp_head_portrait) " +
			"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String findEmployeeEmpId_SQL = "select id,empID,emp_CN_name,emp_EN_name," +
			"password,engageDate,gender,birthday,birthPlace,constellation,bloodType,personalMail,companyMail," +
			"favourite,location,deleteFlag,emp_head_portrait,empType from employee where empID = ?";
	private static final String updateEmployeeInfo_SQL = "update employee set emp_CN_name = ?,emp_EN_name = ?," +
			"engageDate = ?,gender = ?,birthday = ?,birthPlace = ?,constellation = ?,bloodType = ?,personalMail = ?,companyMail = ?," +
			"favourite = ?,location = ?,emp_head_portrait = ?,empType = ? where empID = ?";
	private static final String deleteEmployee_SQL = "delete from employee where empID = ?";
	
	@Override
	/**
	 * 查询所有的employeeDTO
	 */
	public List<EmployeeDTO> findAllEmployee() {//jdbcTemplate.queryForList(findAllEmployee_SQL, EmployeeDTO.class);
		//List<EmployeeDTO> employeeList = (List<EmployeeDTO>) jdbcTemplate.queryForList(findAllEmployee_SQL).get(0);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(findAllEmployee_SQL);
		List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
		for(int i=0;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			EmployeeDTO employeeDTO = createEmployeeDTO(map);
			employeeList.add(employeeDTO);
		}
		System.out.println(employeeList.toString());
		return employeeList;
	}

	/**
	 * 将spring jdbcTemplate取出的map对象转化成为EmployeeDTO
	 * @param map
	 */
	private EmployeeDTO createEmployeeDTO(Map<String, Object> map) {
		System.out.println("map : "+map.toString());
		EmployeeDTO employeeDTO = null;
		for(int i=0;i<map.size();i++){
			int id = (Integer) map.get("id");
			String empID = (String)map.get("empID");
			String emp_CN_name = (String)map.get("emp_CN_name");
			String emp_EN_name = (String)map.get("emp_EN_name");
			String password = (String)map.get("password");
			Date engageDate = (Date) map.get("engageDate");
			String gender = (String)map.get("gender");
			Date birthday = (Date)map.get("birthday");
			String birthPlace = (String)map.get("birthPlace");
			String constellation = (String)map.get("constellation");
			String bloodType = (String)map.get("bloodType");
			String personalMail = (String)map.get("personalMail");
			String companyMail = (String)map.get("companyMail");
			String favourite = (String)map.get("favourite");
			String location = (String)map.get("location");
			int deleteFlag = (Integer)map.get("deleteFlag");
			String emp_head_portrait = (String)map.get("emp_head_portrait");
			String empType = (String)map.get("empType");
			
			employeeDTO = new EmployeeDTO(id, empID, emp_CN_name, empType, emp_EN_name, password, engageDate, gender, birthday, birthPlace, constellation, bloodType, personalMail, companyMail, favourite, location, deleteFlag, emp_head_portrait);
		}
		return employeeDTO;
	}

	@Override
	/**
	 * 根据empId找出employee信息
	 */
	public EmployeeDTO findEmployeeByEmpId(String empID) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(findEmployeeEmpId_SQL, new Object[]{empID});
		EmployeeDTO employeeDTO=null;
		if(list.size() == 0){
			return employeeDTO;
		}else{
			employeeDTO = createEmployeeDTO(list.get(0));
		}
		return employeeDTO;
	}

	@Override
	/**
	 * 增加新的employee账户
	 */
	public int createNewEmployee(EmployeeDTO employeeDTO) {
		Object[] params = new Object[]{employeeDTO.getEmpID(),employeeDTO.getEmp_CN_name(),employeeDTO.getEmp_EN_name(),
				employeeDTO.getPassword(),employeeDTO.getEmpType(),employeeDTO.getEngageDate(),employeeDTO.getGender(),employeeDTO.getBirthday(),
				employeeDTO.getBirthPlace(),employeeDTO.getConstellation(),employeeDTO.getBloodType(),employeeDTO.getPersonalMail(),
				employeeDTO.getCompanyMail(),employeeDTO.getFavourite(),employeeDTO.getLocation(),employeeDTO.getDeleteFlag(),
				employeeDTO.getEmp_head_portrait()};
		int updateLines = jdbcTemplate.update(createNewEmployee_SQL, params);
		return updateLines;
	}

	/**
	 * 增加通过文件execl上传的employee信息
	 * @return 0:代表出传入的employeeList为null，>0代表进行了操作
	 */
	@Override
	public int createUploadEMployees(List<EmployeeDTO> employeeList) {
		if(employeeList == null){
			return 0;
		}
		System.out.println(employeeList.toString());
		int updateLine = 0;//记录是否对数据进行了操作
		for(int i=0;i<employeeList.size();i++){
			updateLine++;
			EmployeeDTO employeeDTO = employeeList.get(i);
			String empID = employeeDTO.getEmpID();//将list中的empID取出来，做判断，如果在数据库中已存在
			//那么这条记录不做操作，只有不存在的时候才做插入操作
			int flag = jdbcTemplate.queryForInt("select count(empID) from employee where empID = ?", new Object[]{empID});
			if(flag == 0){//数据库中不存在这条记录，则增加这条记录
				employeeDTO.setPassword(MD5Util.md5("111111"));//给密码加密
				employeeDTO.setEmp_head_portrait("C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg");//指定默认的头像
				employeeDTO.setDeleteFlag(1);//1代表在职，0代表离职
				createNewEmployee(employeeDTO);
			}else{//数据库中已存在这条记录，则继续下一次迭代
				continue;
			}
		}
		return updateLine;
	}

	@Override
	public int updateEmployeeInfo(EmployeeDTO employeeDTO) {
		/**
		 * private static final String updateEmployeeInfo_SQL = 
		 * "update employee set emp_CN_name = ?,emp_EN_name = ?," +
			"engageDate = ?,gender = ?,birthday = ?,birthPlace = ?,constellation = ?,
			bloodType = ?,personalMail = ?,companyMail = ?," +
			"favourite = ?,location = ?,emp_head_portrait = ?,
			empType =? from employee where empID = ?";
	
		 */
		Object[] params = new Object[]{employeeDTO.getEmp_CN_name(),employeeDTO.getEmp_EN_name(),employeeDTO.getEngageDate(),
				employeeDTO.getGender(),employeeDTO.getBirthday(),employeeDTO.getBirthPlace(),
				employeeDTO.getConstellation(),employeeDTO.getBloodType(),employeeDTO.getPersonalMail(),
				employeeDTO.getCompanyMail(),employeeDTO.getFavourite(),employeeDTO.getLocation(),
				employeeDTO.getEmp_head_portrait(),employeeDTO.getEmpType(),employeeDTO.getEmpID()};
		int updateLines = jdbcTemplate.update(updateEmployeeInfo_SQL, params);
		return updateLines;
	}

	/**
	 * 删除员工操作
	 */
	@Override
	public int deleteEmployees(String[] empIds) {
		if(empIds == null){
			return 0;
		}
		int updateLines = 0;//记录此次删除的员工个数
		int needTobeDeletedLines = empIds.length;//要删除的员工个数
		System.out.println(Arrays.toString(empIds));
		for(String empId : empIds){
			int line = jdbcTemplate.update(deleteEmployee_SQL, new Object[]{empId});
			if(line != 0){//如果不为0代表删除成功
				updateLines++;//记录数+1
			}
		}
		if(updateLines == needTobeDeletedLines){
			return 1;//代表成功
		}else{//代表失败
			return 0;
		}
	}
}
