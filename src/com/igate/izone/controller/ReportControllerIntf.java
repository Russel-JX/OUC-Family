package com.igate.izone.controller;

import java.util.List;

import com.igate.izone.dto.ReportDTO;
import com.igate.izone.dto.ReportDetailDTO;

/**
 * @author Xun Jiang
 * @Description 报告借口 
 */
public interface ReportControllerIntf {
	
	//查询某人所有报告
	public String fetchAllReport();
	
	//查询某个报告详细内容
	public List<ReportDetailDTO> fetchReportDetail();
	
	//新建报告
	public boolean saveReport(ReportDetailDTO report);
	
	//删除报告
	public boolean removeReport(int reportID);
	
	//修改报告（报告标题和描述）
	public boolean editReport(String newtTitle,String newDescription);
	
	//修改报告内容
	public boolean editReportDetail(int reportID,ReportDetailDTO newReport);
	

}
