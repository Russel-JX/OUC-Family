package spring.transaction.propagation;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.brilliance.po.DiaryInfo;
import com.brilliance.service.impl.TestServiceImpl;

/**
* @ClassName: PropagationNothingTest
* @Package spring.transaction.propagation
* @Description: Spring事务的传播特性
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
        
	在JUnit的@Test注解上加上的Spring事务@Transactional注解没有Spring的事务回滚的效果，
		还是Junit或者@Rollback或者@@TransactionConfiguration中的defaultRollback属性管理是否回滚。
		Junit或者Spring-test的回滚机制会覆盖@Transactional事务回滚。。所以，此方法上不加@Transactional，也不用junit的回滚，而在被调用的方法里加@Transactional来规避测试的回滚机制。
*/
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springMVC_test.xml")
public class PropagationNothingTest {
	
	private Log logger = LogFactory.getLog(PropagationNothingTest.class);
	
	@Resource
	private TestServiceImpl testServiceImpl;
	
	/** 
	* @Title: requiredNone 
	* @Description: 前者有事务,后者几个方法无事务申明。 
	* @param @throws 
	* @return void    
	* @throws 
	* E->F->G->...。E调用F,F调用G...。E有事务，FG无事务。
	* FG还是在E的大事务中，所以无论E或F或G任何一个方法异常时，整体都会一起回滚。
	* 结论：Spring的事务，包含了这个事务方法调用的所有嵌套方法，都在一个事务中。
	*/ 
	@Test
	public void TransactionalNone() throws Exception{
		logger.info("requiredNone方法开始执行........");
		//insert
		testServiceImpl.E();
		logger.info("requiredNone方法结束执行........");
	}
	
}
