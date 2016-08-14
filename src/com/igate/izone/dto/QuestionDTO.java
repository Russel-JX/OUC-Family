package com.igate.izone.dto;

import java.sql.Date;

/**
 * @author Xun Jiang
 * @description 员工所提问题数据传输对象
 */
public class QuestionDTO {
	
	private int id;
	private String questionName;
	private int answerID;
	private String questionDescription;
	private int questionFrom;
	private int questionTo;
	private int status;
	private Date raiseDate;
	
	public QuestionDTO() {
		super();
	}
	
	public QuestionDTO(int id, String questionName, int answerID,
			String questionDescription, int questionFrom, int questionTo,
			int status, Date raiseDate) {
		super();
		this.id = id;
		this.questionName = questionName;
		this.answerID = answerID;
		this.questionDescription = questionDescription;
		this.questionFrom = questionFrom;
		this.questionTo = questionTo;
		this.status = status;
		this.raiseDate = raiseDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public int getAnswerID() {
		return answerID;
	}
	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	public int getQuestionFrom() {
		return questionFrom;
	}
	public void setQuestionFrom(int questionFrom) {
		this.questionFrom = questionFrom;
	}
	public int getQuestionTo() {
		return questionTo;
	}
	public void setQuestionTo(int questionTo) {
		this.questionTo = questionTo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getRaiseDate() {
		return raiseDate;
	}
	public void setRaiseDate(Date raiseDate) {
		this.raiseDate = raiseDate;
	}
	
}
