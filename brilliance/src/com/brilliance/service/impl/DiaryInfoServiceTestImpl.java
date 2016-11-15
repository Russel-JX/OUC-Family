package com.brilliance.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.dao.DiaryInfoDao;
import com.brilliance.po.DiaryInfo;
import com.brilliance.service.DiaryInfoServiceTest;

/**
* @ClassName: DiaryInfoServiceTestImpl
* @Package com.brilliance.service.impl
* @Description: 用于Junit测试Spring事务的测试类
* @author Russell Xun Jiang
* @date 2016年11月14日 下午5:20:47
*/
@Repository
public class DiaryInfoServiceTestImpl extends BaseService<DiaryInfo> implements
		DiaryInfoServiceTest {

	@Resource
	private DiaryInfoDao diaryInfoDao;

//	@Transactional
	public void A(DiaryInfo diaryInfo) throws Exception {
		diaryInfoDao.addDiaryInfo(diaryInfo);

		
		//A事务调用B事务
		DiaryInfo diary = new DiaryInfo();
		Date now = new Date();
		diary.setAuthor("B作者");
		diary.setContent("B日记");
		diary.setCreatedBy("B");
		diary.setCreateTime(now);
		diary.setLastUpdateTime(now);
		B(diary);
	}
	
	@Transactional
	public void B(DiaryInfo diaryInfo){
		diaryInfoDao.addDiaryInfo(diaryInfo);
		int a = 9/0;
	}
}
