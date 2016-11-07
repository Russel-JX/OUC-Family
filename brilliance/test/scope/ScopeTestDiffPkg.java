package scope;

import scope.pkg.Father1;

/**
* @ClassName: ScopeTestDiffPkg
* @Package ScopeTestDiffPkg
* @Description: 变量作用域研究——不不同package下
* @author Russell Xun Jiang
* @date 2016年11月7日 下午6:11:22
* * public：所有都能访问 
* protected:不同一包下或者子类可访问
* private：私有的，本类对象可访问
* 不写：不同一包下可访问
*/

public class ScopeTestDiffPkg {

	public static void main(String[] args) {
		//
		Father1 f1 = new Father1();
		String name = f1.name;//不同包下，访问公有属性
//		String nickname = f1.nickname;//不同包下，不能访问保护属性
//		String address = f1.address;//不同包下，不能访问私有属性。检查异常
//		String school = f1.school;//不同包下，不能访问公有默认属性
		
		f1.f_m1();//不同包下，访问公有方法
//		f1.f_m2();//不同包下，不能访问保护方法
//		f1.f_m3();//不同包下，不能访问私有方法。检查异常
//		f1.f_m4();//不同包下，不能访问默认方法。

	}

}
