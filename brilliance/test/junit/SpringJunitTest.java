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
* @ClassName: MyController
* @Package transaction.spring
* @Description: 1.Spring-test知识  2.Spring事务简单测试
* @author Russell Xun Jiang
* @date 2016年11月9日 下午3:50:21
* 一、事务测试步骤：
* 1.在类上方，@RunWith(SpringJUnit4ClassRunner.class)配置哪种测试，用Spring-test框架测试，而不只是JUnit测试。
* 2.在类上方，@ContextConfiguration(locations="classpath:springMVC.xml")。配置context,加载配置文件
* 	可配置defaultRollback是否对整个类中的所有方法回滚与否，默认为true。即类中所有方法的事务（加了@Transactional的）都回滚
* 3.注解注入依赖。@Resource
* 4.在类上火方法上配置Spring事务控制。@Transactional。要想使用Spring事务必须配置，否则@ContextConfiguration定义无任何意义。
* 	只有写了@Transactional的方法或类才能真正使用Spring事务。@TransactionConfiguration只是前提而起，对是否回滚不影响。
* 	
* 5.在方法上配置事务是否回滚。@Rollback(true或者false)。决定了是否是否回滚。默认true，即回滚。
* 而@TransactionConfiguration中的defaultRollback只是对类中的方法全局设置了是否混滚，优先级@Rollback高。
* 也可不写@Rollback注解，则是否回滚由defaultRollback控制。
* 
* 6.@BeforeTransaction和@AfterTransaction：使用这两个注解注解的方法定义了在一个事务性测试方法之前或之后执行的行为，且被注解的方法将运行在该事务性方法的事务之外。
* 
* 二、非事务测试（其他测试）
* 1.@Repeat(5)：表示被注解的方法应被重复执行多少次
* 2.@Timed：表示被注解的方法必须在多长时间内运行完毕，超时将抛出异常，使用如@Timed(millis=10)方式指定，单位为毫秒。
* 注意此处指定的时间是如下方法执行时间之和：测试方法执行时间（或者任何测试方法重复执行时间之和）、
* @Before和@After注解的测试方法之前和之后执行的方法执行时间。
* 而Junit 4中的@Test(timeout=2)指定的超时时间只是测试方法执行时间，包括任何重复等（和博客说的相反。已验证）。
*/
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)//配置事务管理器。只是管理器配置，对是否回滚没有影响，但要想Spring测试支持事务必须配置。用@Transactional注解定义。
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springMVC.xml")//配置上下文context，用于加载Spring配置文件
public class SpringJunitTest {
	
	private Log logger = LogFactory.getLog(SpringJunitTest.class);
	
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
	
	@Transactional
	@Rollback(false)
//	@Test
	public void testTansaction(){
		logger.info("事务开始执行........");
		DiaryInfo diary = new DiaryInfo();
		Date now = new Date();
		diary.setAuthor("Russell");
		diary.setContent("这是我的日记");
		diary.setCreatedBy("Russell");
		diary.setCreateTime(now);
		diary.setLastUpdateTime(now);
		diaryInfoDaoImpl.addDiaryInfo(diary);
		logger.info("事务结束执行........");
	}
	
	@Repeat(3)//重复执行此方法3次
//	@Test
	public void repeatTest(){
		logger.info("执行repeatTest方法重复次数...");
	}
	
	//Spring的timed超时时间包括：测试方法执行时间（或者任何测试方法重复执行时间之和）、@Before和@After注解的测试方法之前和之后执行的方法执行时间
	@Timed(millis=2)//执行方法超时2毫秒。异常见JUnit视图，抛出异常:java.util.concurrent.TimeoutException: Test took 6345 ms; limit was 2 ms.
//	@Test
	public void springTimedTest(){
		logger.info("执行timedTest方法测试超时...");
		for(int i=0;i<100000;i++){
			logger.info(i);
		}
	}
	
	//JUnit的timeout超时设置，只是测试的这个方法的运行时长。
	@Repeat(10000)
//	@Test(timeout=300)//300毫秒超时（只执行一次时，不会超时；重复1万次时，超时）。。异常见JUnit视图，将抛出异常:java.lang.Exception: test timed out after 3 milliseconds
	public void junitTimeoutTest(){
		logger.info("junitTimeoutTest...");
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
