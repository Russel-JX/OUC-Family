package scope.pkg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* @ClassName: ScopeTest
* @Package scope
* @Description: 变量作用域研究——同一个package下
* @author Russell Xun Jiang
* @date 2016年11月7日 下午2:55:56
* 
* public：所有都能访问 
* protected:同一包下或者子类可访问
* private：私有的，本类对象可访问
* 不写：同一包下可访问
* 
*/
public class ScopeTestSamePkg {
	public static void main(String[] args) {
		
		//
		Father1 f1 = new Father1();
		String name = f1.name;//同包下，访问公有属性
		String nickname = f1.nickname;//同包下，访问保护属性
//		String address = f1.address;//同包下，不能访问私有属性。检查异常
		String school = f1.school;//同包下，访问公有默认属性
		
		f1.f_m1();//同包下，访问公有方法
		f1.f_m2();//同包下，访问保护方法
//		f1.f_m3();//同包下，访问不能私有方法。检查异常
		f1.f_m4();//同包下，访问默认方法。
		
		Son1 son = new Son1();
		//这里子类对象访问父类的私有属性。直接访问是不能的，可通过get属性的公有方法访问私有属性来绕过。
//		String sonAddress = son.address;//同包下，访问不能私有方法。检查异常
		String sonAddress2 =  son.getAddress();

	}

}
