package com.brilliance.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.dao.DiaryInfoDao;
import com.brilliance.dao.UserInfoDao;
import com.brilliance.po.DiaryInfo;
import com.brilliance.po.UserInfo;
import com.brilliance.utils.Constants;

@Repository
public class TestServiceImpl extends BaseService<DiaryInfo> {
	@Resource
	private DiaryInfoDao diaryInfoDao;
	
	@Resource
	private UserInfoDao userInfoDao;
	@Transactional
	public void A() throws Exception {//不写也是REQUIRED事务传播特性
		DiaryInfo diaryInfo1 = new DiaryInfo("A1","A1Author","A1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("A2","A2Author","A2Content",new Date(),new Date(),new Date());
		//新增日志A1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		B();
		
		//新增日志A2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
		int a = 9/0;
	}
	@Transactional(propagation=Propagation.REQUIRED)//不写也是REQUIRED事务传播特性
	public void B() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("B1","B1Author","B1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("B2","B2Author","B2Content",new Date(),new Date(),new Date());
		//新增日志B1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
//		int a = 9/0;
		//新增日志B2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	
	public void C() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("C1","C1Author","C1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("C2","C2Author","C2Content",new Date(),new Date(),new Date());
		//新增日志C1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		D();
//		int a = 9/0;
		//新增日志C2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void D() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("D1","D1Author","D1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("D2","D2Author","D2Content",new Date(),new Date(),new Date());
		//新增日志D1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		int a = 9/0;
		//新增日志D2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void E() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("E1","E1Author","E1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("E2","E2Author","E2Content",new Date(),new Date(),new Date());
		//新增日志C1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		F();
//		int a = 9/0;
		//新增日志C2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	public void F() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("F1","F1Author","F1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("F2","F2Author","F2Content",new Date(),new Date(),new Date());
		//新增日志D1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
//		int a = 9/0;
		G();
		//新增日志D2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	public void G() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("G1","G1Author","G1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("G2","G2Author","G2Content",new Date(),new Date(),new Date());
		//新增日志D1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		int a = 9/0;
		//新增日志D2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
}
