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
* @ClassName: PropagationMandatoryTest
* @Package spring.transaction.propagation
* @Description: Spring事务的传播特性——Propagation.MANDATORY 如果已经存在事务，则在此事务中执行；否则抛出异常。
* @author Russell Xun Jiang
* @date 2016年11月16日 上午10:54:23
*/
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springMVC.xml")
public class PropagationMandatoryTest {
	
	private Log logger = LogFactory.getLog(PropagationMandatoryTest.class);
	
	@Resource
	private TestServiceImpl testServiceImpl;
	
	/** 
	* @Title: txMandatory 
	* @Description: 情景一：前者有事务，后者为MANDATORY事务。
	* @param @throws 
	* @return void    
	* @throws 
	* L->M。前者L有事务，则M合并到L事务中。
	* 任何异常，一起回滚。
	*/ 
//	@Test
	public void txMandatory() throws Exception{
		logger.info("txSupports方法开始执行........");
		//insert
		testServiceImpl.L();
		logger.info("txSupports方法结束执行........");
	}
	
	/** 
	* @Title: noneMandatory
	* @Description: 情景二：前者没有事务，后者为MANDATORY事务。（有异议）
	* @param @throws 
	* @return void    
	* @throws 
	* N->O。前者N没有事务，抛出异常。
	* O为强制的，发现调用者N没事务，应该抛出异常，但实际测试没有异常发生，成功提交。
	* 手动异常时，也不回滚。相当用于没有任何事务机制。
	*/ 
	@Test
	public void noneMandatory() throws Exception{
		logger.info("txSupports方法开始执行........");
		//insert
		testServiceImpl.N();
		logger.info("txSupports方法结束执行........");
	}
	
	
	
	

}
