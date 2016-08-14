package com.brilliance.service;

import java.text.ParseException;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.DiaryInfo;

public interface DiaryInfoService {
	
	public ServiceReturns addDiaryInfo(DiaryInfo diaryInfo,String appURL);
	
	//获取当天的日记 - 一个
	public ServiceReturns getDiaryInfoByDate(DiaryInfo diaryInfo,String appImgURL) throws BriException;
	
	public ServiceReturns getDiaryList(DiaryInfo diaryInfo,String appImgURL) throws BriException, ParseException;
	
	public ServiceReturns deleteDiaryInfo(String[] diaryIds);
	
	public ServiceReturns updateDiaryInfo(DiaryInfo diaryInfo,String appURL);
	
	//public String addAppImgURL(String htmlContont,String appImgURL);
	
}
