package spring.transaction.propagation;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.dao.DiaryInfoDao;
import com.brilliance.dao.impl.ProvincesInfoDaoImpl;
import com.brilliance.po.DiaryInfo;
import com.brilliance.po.ProvincesInfo;

/**
* @ClassName: MyController
* @Package spring.transaction.propagation
* @Description: Spring事务的传播特性——Propagation.REQUIRED 如果存在一个事务则支持当前事务，没有则开启一个事务。
* @author Russell Xun Jiang
* @date 2016年11月14日 上午10:31:47
* 事务传播行为就是多个事务方法相互调用时，事务如何在这些方法间传播。
假设事务从方法 A 传播到方法 B，您需要面对方法 B，问自己一个问题：方法A是否有事务。
Spring针对这种情况提供7中事务传播特性。
1. PROPAGATION_REQUIRED: 如果存在一个事务，则支持当前事务。如果没有事务则开启
2. PROPAGATION_SUPPORTS: 如果存在一个事务，支持当前事务。如果没有事务，则非事务的执行
3. PROPAGATION_MANDATORY: 如果已经存在一个事务，支持当前事务。如果没有一个活动的事务，则抛出异常。
4. PROPAGATION_REQUIRES_NEW: 总是开启一个新的事务。如果一个事务已经存在，则将这个存在的事务挂起。
5. PROPAGATION_NOT_SUPPORTED: 总是非事务地执行，并挂起任何存在的事务。
6. PROPAGATION_NEVER: 总是非事务地执行，如果存在一个活动事务，则抛出异常
7. PROPAGATION_NESTED：如果一个活动的事务存在，则运行在一个嵌套的事务中. 如果没有活动事务, 
      则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行
*/
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springMVC.xml")
public class PropagationSupportsTest {
	
	private Log logger = LogFactory.getLog(PropagationSupportsTest.class);
	
	@Resource
	private DiaryInfoDao diaryInfoDaoImpl;
	
	@Transactional
	@Test
	public void propagationRequiredTestA(){
		logger.info("事务A开始执行........");
		DiaryInfo diary = new DiaryInfo();
		Date now = new Date();
		diary.setAuthor("A作者");
		diary.setContent("A日记");
		diary.setCreatedBy("A");
		diary.setCreateTime(now);
		diary.setLastUpdateTime(now);
		diaryInfoDaoImpl.addDiaryInfo(diary);
		
		//A调用B
		propagationRequiredTestB();
		
		logger.info("事务A结束执行........");
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void propagationRequiredTestB(){
		logger.info("事务B开始执行........");
		DiaryInfo diary = new DiaryInfo();
		Date now = new Date();
		diary.setAuthor("B作者");
		diary.setContent("B日记");
		diary.setCreatedBy("B");
		diary.setCreateTime(now);
		diary.setLastUpdateTime(now);
		diaryInfoDaoImpl.addDiaryInfo(diary);
		
		int a = 9/0;
		
		logger.info("事务B结束执行........");
	}
	
	

}
