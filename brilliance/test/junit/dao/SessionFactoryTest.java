/**
 * 
 */
package junit.dao;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author mx801343
 * 
 */
public class SessionFactoryTest {

	private static ApplicationContext cxt;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cxt = new ClassPathXmlApplicationContext("springMVC.xml");
		SessionFactory sessionFactory = (SessionFactory) cxt.getBean("sessionFactory");
	}

	@Test
	public void testSessionFactory() {
		
	}

}
