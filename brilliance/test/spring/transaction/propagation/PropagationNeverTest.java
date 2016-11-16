package spring.transaction.propagation;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.brilliance.service.impl.TestServiceImpl;

/**
* @ClassName: PropagationNeverTest
* @Package spring.transaction.propagation
* @Description: Spring事务的传播特性——Propagation.NEVER 总是非事务运行。如有事务，抛出异常
* @author Russell Xun Jiang
* @date 2016年11月16日 上午11:25:00
*/
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springMVC.xml")
public class PropagationNeverTest {
	
	private Log logger = LogFactory.getLog(PropagationNeverTest.class);
	
	@Resource
	private TestServiceImpl testServiceImpl;
	
	/** 
	* @Title: txMandatory 
	* @Description: 情景一：前者有事务，后者为NEVER事务。（有异议）
	* @param @throws 
	* @return void    
	* @throws 
	* P->Q。前者P有事务，则Q抛出异常。
	* 实际测试结果：P有事务，Q没有抛出异常。
	* 当手动在Q在异常时，整体都回滚。相当于PQ在一个事务中了。
	* 
	*/ 
//	@Test
	public void txNever() throws Exception{
		logger.info("txNever方法开始执行........");
		//insert
		testServiceImpl.P();
		logger.info("txNever方法结束执行........");
	}
	
	/** 
	* @Title: noneNever
	* @Description: 情景二：前者没有事务，后者为NEVER事务。
	* @param @throws 
	* @return void    
	* @throws 
	* R->S。前者R没有事务，非事务执行。所以发生异常，不回滚。
	*/ 
	@Test
	public void noneNever() throws Exception{
		logger.info("noneNever方法开始执行........");
		//insert
		testServiceImpl.R();
		logger.info("noneNever方法结束执行........");
	}
	
	
	
	

}
