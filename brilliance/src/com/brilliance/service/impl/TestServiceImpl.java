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
//	@Transactional
	public void A() throws Exception {
		DiaryInfo diaryInfo = new DiaryInfo();
		diaryInfo.setDiaryType(null);
		diaryInfo.setDiaryTitle("A");
		diaryInfo.setSource("A");
		diaryInfo.setAuthor("A");
		diaryInfo.setDiaryDate(new Date());
		diaryInfo.setContent("A");
		diaryInfo.setCreatedBy("A");
		diaryInfo.setCreateTime(new Date());
		diaryInfo.setDeleteFlag(Constants.UNDELETED);
			
		diaryInfoDao.addDiaryInfo(diaryInfo);
		B();
	}
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public void B() throws Exception {
		DiaryInfo diaryInfo2 = new DiaryInfo();
		diaryInfo2.setId(1);
		diaryInfo2.setAuthor("D");
		diaryInfo2.setLastUpdateTime(new Date());
		diaryInfoDao.update(diaryInfo2);
		
		//异常后，下面的代码不执行，不会插入B数据，但会执行上面的更新。说明B方法中的更新和新增作为一个原子操作。
		//直接通过TestController的saveDiary方法调用B方法，会回滚；中间通过非事务方法A转了一下，B就不会滚了。
		int a = 9/0;
		
		DiaryInfo diaryInfo = new DiaryInfo();
		diaryInfo.setDiaryType(null);
		diaryInfo.setDiaryTitle("B");
		diaryInfo.setSource("B");
		diaryInfo.setAuthor("B");
		diaryInfo.setDiaryDate(new Date());
		diaryInfo.setContent("B");
		diaryInfo.setCreatedBy("B");
		diaryInfo.setCreateTime(new Date());
		diaryInfo.setDeleteFlag(Constants.UNDELETED);
			
		diaryInfoDao.addDiaryInfo(diaryInfo);
	}
}
