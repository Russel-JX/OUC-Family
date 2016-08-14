package com.igate.izone.dao.impl;

import java.util.List;




import org.apache.log4j.Level;
import org.springframework.stereotype.Repository;

import com.igate.izone.dao.BaseDao;
import com.igate.izone.dao.ReportDaoIntf;
import com.igate.izone.dto.ReportDetailDTO;

@Repository("reportDao")
public class ReportDaoImpl  extends BaseDao implements ReportDaoIntf{
	
	public List<String> fetchAllReport(int userID) {
		String sql = "select reportName from report";
		
		List<String> reports = jdbcTemplate.queryForList(sql,String.class);
		reports.add("99999");
		//System.out.println("====="+reports.size()+"==="+reports.get(0));
		
		try {
			System.out.println(5/0);
		} catch (Exception e) {
			System.out.println("异常已经发生！");
			//抛出异常。让log4j日志记录
			throw new RuntimeException();
		}
		
		return reports;
	}

	public List<ReportDetailDTO> fetchReportDetail() {
		return null;
	}

	public boolean saveReport(ReportDetailDTO report) {
		return false;
	}

	public boolean removeReport(int reportID) {
		return false;
	}

	public boolean editReport(String newtTitle, String newDescription) {
		return false;
	}

	public boolean editReportDetail(int reportID, ReportDetailDTO newReport) {
		return false;
	}

}
