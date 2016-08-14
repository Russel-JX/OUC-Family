package com.igate.izone.dao;

import java.util.List;

import com.igate.izone.dto.QuestionDTO;
import com.igate.izone.dto.ReportDTO;
import com.igate.izone.dto.ReportDetailDTO;

public interface QuestionDaoIntf {
	
	// 查询某个角色下的所有FAQ
	public List<QuestionDTO> fetchAllQuestionByType(int userID);

/*	// 查询FAQ详细内容
	public List<QuestionDTO> fetchReportDetail();*/

	// 新建FAQ
	public boolean saveQuestion(QuestionDTO question);

	// 删除FAQ
	public boolean removeQuestion(int questionID);

}
