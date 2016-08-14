package com.igate.izone.rowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.igate.izone.dto.EmployeeDTO;

public class EmployeeRowMapper implements RowMapper<EmployeeDTO> {

	@Override
	public EmployeeDTO mapRow(ResultSet rst, int index) throws SQLException {
		if(rst == null ){
			return null;
		}
		int id = rst.getInt("id");
		String empID = rst.getString("empID");
		String emp_CN_name = rst.getString("emp_CN_name");
		String emp_EN_name = rst.getString("emp_EN_name");
		String password = rst.getString("password");
		Date engageDate = rst.getDate("engageDate");
		String gender = rst.getString("gender");
		Date birthday = rst.getDate("birthday");
		String birthPlace = rst.getString("birthPlace");
		String constellation = rst.getString("constellation");
		String bloodType = rst.getString("bloodType");
		String personalMail = rst.getString("personalMail");
		String companyMail = rst.getString("companyMail");
		String favourite = rst.getString("favourite");
		String location = rst.getString("location");
		int deleteFlag = rst.getInt("deleteFlag");
		String emp_head_portrait = rst.getString("emp_head_portrait");
		String empType = rst.getString("empType");
		EmployeeDTO employeeDTO = new EmployeeDTO(id, empID, emp_CN_name, empType, emp_EN_name, password, engageDate, gender, birthday, birthPlace, constellation, bloodType, personalMail, companyMail, favourite, location, deleteFlag, emp_head_portrait);
		return employeeDTO;
	}

}
