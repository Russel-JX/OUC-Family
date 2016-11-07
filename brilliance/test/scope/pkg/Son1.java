package scope.pkg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* @ClassName: Son1
* @Package scope.pkg
* @Description: 变量作用域研究——子类情况
* @author Russell Xun Jiang
* @date 2016年11月7日 下午6:11:44
* * public：所有都能访问 
* protected:同一包下或者子类可访问
* private：私有的，本类对象可访问
* 不写：同一包下可访问
*/
public class Son1 extends Father1{
	private Log logger = LogFactory.getLog(Son1.class);

	//子类的方法
	public void s_m1(){
		
		logger.info(name);//子类访问公有属性
		logger.info(nickname);//子类访问保护属性
//		logger.info(address);//子类不能访问私有属性，检查异常
		logger.info(school);//子类访问默认属性
		
		f_m1();//子类访问公有方法
		f_m2();//子类访保护有方法
//		f_m3();//私有方法子类不能调用，检查异常
		f_m4();//子类访默认作用域方法
		
		
		
	}
}
