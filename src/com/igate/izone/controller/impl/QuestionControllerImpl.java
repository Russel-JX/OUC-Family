package com.igate.izone.controller.impl;

import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igate.izone.dto.QuestionDTO;
import com.igate.izone.entity.FileMeta;
import com.igate.izone.service.QuestionServiceIntf;

@Controller("questionController")
@Scope("prototype")
public class QuestionControllerImpl{

	@Resource
	public QuestionServiceIntf questionServiceIntf;
	QuestionDTO question = new QuestionDTO();
	LinkedList<QuestionDTO> questions = new LinkedList<QuestionDTO>();
  	
	//上传要共享的文件
	@RequestMapping(value="/raiseQuestion.do",method=RequestMethod.POST)
	public @ResponseBody LinkedList<QuestionDTO> raiseQuestion(HttpServletRequest request,HttpServletResponse response){
		//String 
		int questionFrom = Integer.parseInt(request.getParameter("questin_from"));
		int questionTo = Integer.parseInt(request.getParameter("question_to"));
		String questionName = request.getParameter("question_name");
		String questionDescription = request.getParameter("question_description");
		question.setQuestionFrom(questionFrom);
		question.setQuestionTo(questionTo);
		question.setQuestionName(questionName);
		question.setQuestionDescription(questionDescription);
		
		System.out.println("QuestionControllerImpl=======问题来源ID是： "+question.getQuestionFrom()+" 问题给： "+question.getQuestionTo()+" 问题名： "+question.getQuestionName()+" 问题描述： "+question.getQuestionDescription());
		
		questionServiceIntf.saveQuestion(question);
		questions.add(question);
		return questions;
	}
	
	
//	//删除单个文件
//	@RequestMapping(value="/remove.do",method=RequestMethod.POST)
//	public @ResponseBody boolean removeFile(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		//要删除的文件id
//      	int id = (Integer.parseInt(request.getParameter("id")));
//      	System.out.println("文件ID是： "+id);
//      	
//      	return fileSharingService.removeFile(id);
//	}


}
