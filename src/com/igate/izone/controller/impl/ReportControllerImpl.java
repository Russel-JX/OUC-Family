package com.igate.izone.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.igate.izone.controller.ReportControllerIntf;
import com.igate.izone.dto.ReportDTO;
import com.igate.izone.dto.ReportDetailDTO;
import com.igate.izone.service.ReportServiceIntf;
import com.igate.izone.service.impl.ReportServiceImpl;

@Controller("reportController")
public class ReportControllerImpl implements ReportControllerIntf{

	
	@Resource
	public ReportServiceIntf reportService;
	
	//查询某人所有报告
	@RequestMapping(value="/fetchAllReport.do",method=RequestMethod.GET)
	public String fetchAllReport() {
		reportService.fetchAllReport(100);
		
		return "/russel/test";
	}

	//查询某个报告详细内容
	public List<ReportDetailDTO> fetchReportDetail() {
		return null;
	}

	//新建报告
	public boolean saveReport(ReportDetailDTO report) {
		return false;
	}

	//删除报告
	public boolean removeReport(int reportID) {
		return false;
	}

	//修改报告（报告标题和描述）
	public boolean editReport(String newtTitle, String newDescription) {
		return false;
	}

	//修改报告内容
	public boolean editReportDetail(int reportID, ReportDetailDTO newReport) {
		return false;
	}

}
