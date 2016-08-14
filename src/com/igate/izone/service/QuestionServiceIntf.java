package com.igate.izone.service;

import com.igate.izone.dto.QuestionDTO;

public interface QuestionServiceIntf {
	// 新建FAQ
	public boolean saveQuestion(QuestionDTO question);

}
