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
	
	//#PropagationNothingTest
	@Transactional(propagation=Propagation.REQUIRED)
	public void E() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("E1","E1Author","E1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("E2","E2Author","E2Content",new Date(),new Date(),new Date());
		//新增日志E1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		F();
//		int a = 9/0;
		//新增日志E2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	public void F() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("F1","F1Author","F1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("F2","F2Author","F2Content",new Date(),new Date(),new Date());
		//新增日志F1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
//		int a = 9/0;
		G();
		//新增日志F2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	public void G() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("G1","G1Author","G1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("G2","G2Author","G2Content",new Date(),new Date(),new Date());
		//新增日志G1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		int a = 9/0;
		//新增日志G2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	
	//#PropagationRequiredTest	allRequired
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
	
	//#PropagationRequiredTest	noneRequired
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
	
	//#PropagationSupportsTest	txSupports
	@Transactional
	public void H() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("H1","H1Author","H1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("H2","H2Author","H2Content",new Date(),new Date(),new Date());
		//新增日志H1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		I();
//		int a = 9/0;
		//新增日志H2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	@Transactional(propagation=Propagation.SUPPORTS)
	public void I() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("I1","I1Author","I1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("I2","I2Author","I2Content",new Date(),new Date(),new Date());
		//新增日志I1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		int a = 9/0;
		//新增日志I2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	
	//#PropagationSupportsTest	noneSupports
	public void J() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("J1","J1Author","J1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("J2","J2Author","J2Content",new Date(),new Date(),new Date());
		//新增日志J1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		K();
//		int a = 9/0;
		//新增日志J2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	@Transactional(propagation=Propagation.SUPPORTS)
	public void K() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("K1","K1Author","K1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("K2","K2Author","K2Content",new Date(),new Date(),new Date());
		//新增日志K1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		int a = 9/0;
		//新增日志K2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	
	//#PropagationMandatoryTest	txMandatory
	@Transactional
	public void L() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("L1","L1Author","L1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("L2","L2Author","L2Content",new Date(),new Date(),new Date());
		//新增日志L1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		M();
//		int a = 9/0;
		//新增日志L2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	@Transactional(propagation=Propagation.MANDATORY)
	public void M() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("M1","M1Author","M1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("M2","M2Author","M2Content",new Date(),new Date(),new Date());
		//新增日志M1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		int a = 9/0;
		//新增日志M2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	
	//#PropagationMandatoryTest	noneMandatory
	public void N() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("N1","N1Author","N1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("N2","N2Author","N2Content",new Date(),new Date(),new Date());
		//新增日志N1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		O();
//		int a = 9/0;
		//新增日志N2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	@Transactional(propagation=Propagation.MANDATORY)
	public void O() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("O1","O1Author","O1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("O2","O2Author","O2Content",new Date(),new Date(),new Date());
		//新增日志O1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		int a = 9/0;
		//新增日志O2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	
	//#PropagationNeverTest	txNever
	@Transactional
	public void P() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("P1","P1Author","P1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("P2","P2Author","P2Content",new Date(),new Date(),new Date());
		//新增日志P1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		Q();
		//新增日志P2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	@Transactional(propagation=Propagation.NEVER)
	public void Q() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("Q1","Q1Author","Q1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("Q2","Q2Author","Q2Content",new Date(),new Date(),new Date());
		//新增日志Q1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
//		int a = 9/0;
		//新增日志Q2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
		
	//#PropagationNeverTest	noneNever
	public void R() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("R1","R1Author","R1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("R2","R2Author","R2Content",new Date(),new Date(),new Date());
		//新增日志R1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		S();
		//新增日志R2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	@Transactional(propagation=Propagation.NEVER)
	public void S() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("S1","S1Author","S1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("S2","S2Author","S2Content",new Date(),new Date(),new Date());
		//新增日志S1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		int a = 9/0;
		//新增日志S2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	
	//PropagationNotSupportedTest	txNotSupported
	@Transactional
	public void T() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("T1","T1Author","T1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("T2","T2Author","T2Content",new Date(),new Date(),new Date());
		//新增日志T1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		U();
		//新增日志T2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
		int a = 9/0;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void U() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("U1","U1Author","U1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("U2","U2Author","U2Content",new Date(),new Date(),new Date());
		//新增日志U1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		//新增日志U2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	
	//PropagationNotSupportedTest	noneNotSupported
	public void V() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("V1","V1Author","V1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("V2","V2Author","V2Content",new Date(),new Date(),new Date());
		//新增日志V1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		W();
		//新增日志V2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void W() throws Exception {
		DiaryInfo diaryInfo1 = new DiaryInfo("W1","W1Author","W1Content",new Date(),new Date(),new Date());
		DiaryInfo diaryInfo2 = new DiaryInfo("W2","W2Author","W2Content",new Date(),new Date(),new Date());
		//新增日志W1
		diaryInfoDao.addDiaryInfo(diaryInfo1);
		int a = 9/0;
		//新增日志W2
		diaryInfoDao.addDiaryInfo(diaryInfo2);
	}
	
	
}
