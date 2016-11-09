package junit.dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.brilliance.dao.impl.ProvincesInfoDaoImpl;
import com.brilliance.po.ProvincesInfo;

public class ProvinceInfoDaoTest {
	private static ApplicationContext cxt;
	private static ProvincesInfoDaoImpl provincesInfoDaoImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cxt = new ClassPathXmlApplicationContext("springMVC.xml");
		provincesInfoDaoImpl = (ProvincesInfoDaoImpl) cxt.getBean("provincesInfoDaoImpl");
	}
	
	
	@Test
	public void testGetAll() {
		for(ProvincesInfo info :provincesInfoDaoImpl.getAll(ProvincesInfo.class)){
			System.out.println(info.getProvince());
		};
	}
}
