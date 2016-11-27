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
* @Description: Spring事务的传播特性——Propagation.NOT_SUPPORTED 总是非事务执行，并挂起任何存在的事务。
* @author Russell Xun Jiang
* @date 2016年11月16日 下午14:25:23
*/
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springMVC.xml")
public class PropagationNotSupportedTest {
	
	private Log logger = LogFactory.getLog(PropagationNotSupportedTest.class);
	
	@Resource
	private TestServiceImpl testServiceImpl;
	
	/** 
	* @Title: txNotSupported 
	* @Description: 情景一：前者有事务，后者为NOT_SUPPORTED事务。（有异议）
	* @param @throws 
	* @return void    
	* @throws 
	* T->U。前者T有事务，则U将T的事务挂起。不存在事务了。
	* 实际测量结果。U异常时，因为U是非事务，程序终止，无任何数据库成功操作
	* T最下方异常时，T是事务，TU都回滚了。
	* 注：
	*/ 
	@Test
	public void txNotSupported() throws Exception{
		logger.info("txNotSupported方法开始执行........");
		//insert
		testServiceImpl.T();
		logger.info("txNotSupported方法结束执行........");
	}
	
	/** 
	* @Title: noneNotSupported
	* @Description: 情景二：前者没有事务，后者为NOT_SUPPORTED事务。
	* @param @throws 
	* @return void    
	* @throws 
	* V->W。前者V没有事务，后者W也没有事务。不存在事务了，异常不回滚。
	*/ 
//	@Test
	public void noneNotSupported() throws Exception{
		logger.info("noneNotSupported方法开始执行........");
		//insert
		testServiceImpl.V();
		logger.info("noneNotSupported方法结束执行........");
	}
	
	
	
	

}
