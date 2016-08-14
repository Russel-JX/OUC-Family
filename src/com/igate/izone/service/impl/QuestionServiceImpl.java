package com.igate.izone.service.impl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igate.izone.dao.QuestionDaoIntf;
import com.igate.izone.dto.QuestionDTO;
import com.igate.izone.service.QuestionServiceIntf;
import com.igate.izone.util.Static.QuestionStatus;

@Service
public class QuestionServiceImpl implements QuestionServiceIntf {
	@Autowired
	private QuestionDaoIntf questionDaoIntf; 
	
	// 新建FAQ
	public boolean saveQuestion(QuestionDTO question) {
		question.setRaiseDate(new Date(new java.util.Date().getTime()));
		//初始化问题状态为未回答 - 0
		question.setStatus(QuestionStatus.pending);
		
		return questionDaoIntf.saveQuestion(question);
	}

}
