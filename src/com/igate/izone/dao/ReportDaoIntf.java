package com.igate.izone.dao;

import java.util.List;

import com.igate.izone.dto.QuestionDTO;
import com.igate.izone.dto.ReportDetailDTO;

public interface ReportDaoIntf {
	
	// 查询摸个角色下的所有FAQ
	public List<String> fetchAllReport(int userID);

	// 查询某个报告详细内容
	public List<ReportDetailDTO> fetchReportDetail();

	// 新建报告
	public boolean saveReport(ReportDetailDTO report);

	// 删除报告
	public boolean removeReport(int reportID);

	// 修改报告（报告标题和描述）
	public boolean editReport(String newtTitle, String newDescription);

	// 修改报告内容
	public boolean editReportDetail(int reportID, ReportDetailDTO newReport);	

}
