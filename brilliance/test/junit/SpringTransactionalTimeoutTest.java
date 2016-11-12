package junit;

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
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.dao.DiaryInfoDao;
import com.brilliance.dao.impl.ProvincesInfoDaoImpl;
import com.brilliance.po.DiaryInfo;
import com.brilliance.po.ProvincesInfo;

/**
* @ClassName: SpringTransactionalTest
* @Package junit
* @Description: Spring的Transactional事务超时测试
* @author Russell Xun Jiang
* @date 2016年11月12日 上午11:52:03
* 1.测试Spring事务超时机制
* 	timeout时间 = 方法中最后一个数据库操作的之前的业务处理时间（即非数据库操作）+多个数据库操作时间之和。
* 注：最后一个数据库操作之后的业务处理时间，不计入timeout时间。
* 
*/
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springMVC.xml")
public class SpringTransactionalTimeoutTest {
	
	private Log logger = LogFactory.getLog(SpringTransactionalTimeoutTest.class);
	
	@Resource
	private ProvincesInfoDaoImpl provincesInfoDaoImpl;
	@Resource
	private DiaryInfoDao diaryInfoDaoImpl;
	
	@BeforeTransaction
	public void beforeTest(){
		logger.info("在事务之前执行........");
	}
	@AfterTransaction
	public void afterTest(){
		logger.info("在事务之后执行........");
	}
	
	
	@Transactional(timeout=2)//设置Spring事务的超时时间为2秒
//	@Test
	public  void timeoutTest2() throws InterruptedException{
		logger.info("timeoutTest2开始...");
		//在DB操作之前的物业处理时间，会影响Spring事务的timeout超时时间。所以此方法会超时，并回滚。
		Thread.sleep(2000);//暂停2秒
		
		DiaryInfo diary = new DiaryInfo();
		Date now = new Date();
		diary.setAuthor("李白");
		diary.setContent("仰天大笑出门去！");
		diary.setCreatedBy("小R");
		diary.setCreateTime(now);
		diary.setLastUpdateTime(now);
		diaryInfoDaoImpl.addDiaryInfo(diary);//DB操作时间很小这里可忽略
		logger.info("timeoutTest2结束...");
	}
	
	@Transactional(timeout=2)//设置Spring事务的超时时间位2秒
//	@Test
	public  void timeoutTest3() throws InterruptedException{
		logger.info("timeoutTest3开始...");
		DiaryInfo diary = new DiaryInfo();
		Date now = new Date();
		diary.setAuthor("杜甫");
		diary.setContent("茅屋为秋风所破。小孩儿还我稻草！");
		diary.setCreatedBy("路边社");
		diary.setCreateTime(now);
		diary.setLastUpdateTime(now);
		
		long beforeSqlTime = System.currentTimeMillis();
		diaryInfoDaoImpl.addDiaryInfo(diary);//DB操作时间很小这里可忽略
		logger.info("新增sql的执行时间（毫秒）："+(System.currentTimeMillis()-beforeSqlTime));
		//在DB操作之后的物业处理时间，不会影响Spring事务的timeout超时时间。。所以此方法不会超时，不会回滚，但方法执行时间会很长。
		Thread.sleep(20000);//暂停20秒，也不会超时，不会回滚。
		logger.info("timeoutTest3结束...");
	}
	
	
	/** 
	* @Title: timeoutTest4 
	* @Description: 纯sql超时
	* @param @throws InterruptedException    设定文件 
	* @return void    返回类型 
	* @throws 
	* 前提：
	* Mysql InnoDB数据库，默认事务会自动提交。测试时必须先将默认提交去除，然后在数据库端锁住要修改的记录，在java中测试事务的超时。
	* 此时代码中的更新事务将被挂起，直到此记录被释放，才能提交，并且要手动commit才能看到修改成功。
	* 讲抛出异常：java.sql.BatchUpdateException: Lock wait timeout exceeded; try restarting transaction
	* 事务
	*/ 
	@Transactional//不定义Spring的超时时间，让sql程序执行慢（等待或死锁），让数据库端事务超时。不能通过非数据库操作时间，让数据库端超时。
	@Test
	public void timeoutTest4() throws InterruptedException{
		logger.info("timeoutTest4开始...");
		DiaryInfo diary = new DiaryInfo();
		Date now1 = new Date();
		diary.setId(1);
		diary.setAuthor("白居易伯伯");
		diary.setContent("朱门酒肉嗅，路有冻死骨");
		diary.setCreatedBy("路透社");
		diary.setCreateTime(now1);
		diary.setLastUpdateTime(now1);
		long beforeSqlTime = System.currentTimeMillis();
		diaryInfoDaoImpl.updateDiaryInfo(diary);
		logger.info("修改sql的执行时间（毫秒）："+(System.currentTimeMillis()-beforeSqlTime));
		
		logger.info("timeoutTest4结束...");
	}
	
	
//	@Test
	public void testGetAll() {
		for(ProvincesInfo info :provincesInfoDaoImpl.getAll(ProvincesInfo.class)){
			System.out.println(info.getProvince());
		};
		//断言
		Assert.assertTrue(true);
	}
	

}
