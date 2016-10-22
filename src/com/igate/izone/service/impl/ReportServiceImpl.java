package com.igate.izone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.igate.izone.dao.ReportDaoIntf;
import com.igate.izone.dao.impl.ReportDaoImpl;
import com.igate.izone.dto.ReportDTO;
import com.igate.izone.dto.ReportDetailDTO;
import com.igate.izone.service.ReportServiceIntf;

@Service
public class ReportServiceImpl implements ReportServiceIntf{

	
	@Autowired
	private ReportDaoIntf reportDao;  
	
	public List<ReportDTO> fetchAllReport(int userID) {
		reportDao.fetchAllReport(userID);
		
		return null;
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
