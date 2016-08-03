package com.brilliance.dao;

import java.text.ParseException;
import java.util.List;

import com.brilliance.base.BaseDao;
import com.brilliance.base.BriException;
import com.brilliance.po.DiaryInfo;

public interface DiaryInfoDao  extends BaseDao<DiaryInfo>{
	
	public void addDiaryInfo(DiaryInfo diaryInfo);
	
//	public DiaryInfo getDiaryInfoById(DiaryInfo diaryInfo) throws BriException;
	
	//获取当天的日记 - 一个
	public DiaryInfo getDiaryInfoByDate(DiaryInfo diaryInfo) throws BriException;
	
	public List<DiaryInfo> getDiaryList(DiaryInfo diaryInfo) throws BriException, ParseException;
	
	public int deleteDiaryInfo(Integer[] diaryIds);
	
	public void updateDiaryInfo(DiaryInfo diaryInfo);
	
}
