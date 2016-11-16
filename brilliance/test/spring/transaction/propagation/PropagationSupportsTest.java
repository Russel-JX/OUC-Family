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
* @ClassName: PropagationSupportsTest
* @Package spring.transaction.propagation
* @Description: Spring事务的传播特性——Propagation.SUPPORTS 如果存在一个事务则支持当前事务，没有非事务执行。
* @author Russell Xun Jiang
* @date 2016年11月14日 上午10:31:47
*/
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springMVC.xml")
public class PropagationSupportsTest {
	
	private Log logger = LogFactory.getLog(PropagationSupportsTest.class);
	
	@Resource
	private TestServiceImpl testServiceImpl;
	
	/** 
	* @Title: txSupports 
	* @Description: 情景一：前者有事务，后者为SUPPORTS事务。
	* @param @throws 
	* @return void    
	* @throws 
	* H->I。前者H有事务，则I合并到H事务中。
	* 任何异常，一起回滚。
	*/ 
//	@Test
	public void txSupports() throws Exception{
		logger.info("txSupports方法开始执行........");
		//insert
		testServiceImpl.H();
		logger.info("txSupports方法结束执行........");
	}
	
	/** 
	* @Title: noneSupports 
	* @Description: 情景二：前者没有事务，后者为SUPPORTS事务。
	* @param @throws 
	* @return void    
	* @throws 
	* J->K。前者J没有事务，K方法也是非事务运行。
	* 整个过程，没有任何事务。所以，异常之前的sql都会提交不回滚，异常之后的代码不运行。
	*/ 
	@Test
	public void noneSupports() throws Exception{
		logger.info("txSupports方法开始执行........");
		//insert
		testServiceImpl.J();
		logger.info("txSupports方法结束执行........");
	}
	
	
	
	

}
