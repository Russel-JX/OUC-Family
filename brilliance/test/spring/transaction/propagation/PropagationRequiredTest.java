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
import com.brilliance.service.impl.TestServiceImpl;

/**
* @ClassName: MyController
* @Package spring.transaction.propagation
* @Description: Spring事务的传播特性——Propagation.REQUIRED 如果存在一个事务则支持当前事务，没有则开启一个事务。
* @author Russell Xun Jiang
* @date 2016年11月14日 上午10:31:47
* 事务传播行为就是多个事务方法相互调用时，事务如何在这些方法间传播。
假设事务从方法 A 传播到方法 B，您需要面对方法 B，问自己一个问题：方法A是否有事务。
Spring针对这种情况提供7中事务传播特性。
1. PROPAGATION_REQUIRED: 默认如果存在一个事务，则支持当前事务。如果没有事务则开启
2. PROPAGATION_SUPPORTS: 如果存在一个事务，支持当前事务。如果没有事务，则非事务的执行
3. PROPAGATION_MANDATORY: 如果已经存在一个事务，支持当前事务。如果没有一个活动的事务，则抛出异常。
4. PROPAGATION_REQUIRES_NEW: 总是开启一个新的事务。如果一个事务已经存在，则将这个存在的事务挂起。
5. PROPAGATION_NOT_SUPPORTED: 总是非事务地执行，并挂起任何存在的事务。
6. PROPAGATION_NEVER: 总是非事务地执行，如果存在一个活动事务，则抛出异常
7. PROPAGATION_NESTED：如果一个活动的事务存在，则运行在一个嵌套的事务中. 如果没有活动事务, 
      则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行
      在JUnit的@Test注解上加上的Spring事务@Transactional注解没有Spring的事务回滚的效果，
		还是Junit或者@Rollback或者@@TransactionConfiguration中的defaultRollback属性管理是否回滚。
		Junit或者Spring-test的回滚机制会覆盖@Transactional事务回滚。。所以，此方法上不加@Transactional，也不用junit的回滚，而在被调用的方法里加@Transactional来规避测试的回滚机制。
*/
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springMVC.xml")
public class PropagationRequiredTest {
	
private Log logger = LogFactory.getLog(PropagationRequiredTest.class);
	
	@Resource
	private TestServiceImpl testServiceImpl;
	
	
	/** 
	* @Title: A 
	* @Description: 情景一：事务传播都是REQUIRED(不写默认)
	* @param @throws 
	* @return void    
	* @throws 
	* A->B.B发现A存在事务，则B不会新建事务，而是合并到A事务中。
	* 所以，既然是一个事务，不管是A或B发生异常都会全部回滚。
	*/ 
//	@Test
	public void allRequired() throws Exception{
		logger.info("allRequired方法开始执行........");
		//insert
		testServiceImpl.A();
		logger.info("allRequired方法结束执行........");
	}
	
	/** 
	* @Title: noneRequired
	* @Description: 情景二：前者无事务申明,后者REQUIRED事务。 （未完成，有问题）
	* @param @throws 
	* @return void    
	* @throws 
	* C->D.C调用D时，D发现C没有事务，则开启一个事务，把C合并到此事务中。
	* 所以，既然是一个事务，C或D发生异常时，CD都要回滚。
	* 实际情况是：D的事务没有任何效果，效果和不写@Transactional一样。当D中异常时，C1、D1插入数据库，C2、D2因为之前就发生的异常没有执行。
	*/ 
//	@Test
	public void noneRequired() throws Exception{
		logger.info("noneRequired方法开始执行........");
		//insert
		testServiceImpl.C();
		logger.info("noneRequired方法结束执行........");
	}
	
	/** 
	* @Title: requiredNone 
	* @Description: 情景三：前者REQUIRED事务,后者无事务申明。 
	* @param @throws 
	* @return void    
	* @throws 
	* 结论同PropagationNothingTest情景一一样：有事务的方法，这个事务包含的向下嵌套调用的所有方法，都在一个事务中。
	* Demo见PropagationNothingTest。
	*/ 
	
	
	

}
