package junit.dao;

import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.brilliance.dao.impl.ExpressInfoDaoImpl;
import com.brilliance.service.impl.ExpressInfoServiceImpl;

public class ExpressInfoDaoTest {
	private static ApplicationContext cxt;
	private static ExpressInfoServiceImpl expressInfoServiceImpl;
	private static ExpressInfoDaoImpl expressInfoDaoImpl;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cxt = new ClassPathXmlApplicationContext("springMVC.xml");
		ExpressInfoServiceImpl expressInfoServiceImpl = (ExpressInfoServiceImpl) cxt.getBean("expressInfoServiceImpl");
	}
	
	@Test
	public void testgetExpressInfo() {
		List list;
		try {
			/*list = expressInfoServiceImpl.getExpressInfo(null);
			Iterator itor = list.iterator();
			while (itor.hasNext()){
				System.out.println("oo = "+itor.next());
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
