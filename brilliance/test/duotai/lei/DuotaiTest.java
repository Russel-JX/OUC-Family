package duotai.lei;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* @ClassName: DuotaiTest
* @Package duotai.lei
* @Description: 
* @author Russell Xun Jiang
* @date 2016年10月29日 上午11:08:19
* 需求描述
* 父类Animal
* 	有此类共有的属性：名称，年龄，性别
* 	有此类共有的方法：进食，休息，运动
* 子类Dog
* 	共有的属性（在Animal中）：名称，年龄，性别
* 	私有的属性：牙齿大小，鼻子灵敏度
* 	共有的方法（在Animal中）：进食（覆盖），休息（覆盖），运动
* 	私有的方法：叫唤，
* 子类Cat
* 	私有的属性：爪子大小
* 	共有的方法（在Animal中）：进食（覆盖），休息，运动
* 	私有的方法：爬树
*/

public class DuotaiTest {
	
	public static Log logger = LogFactory.getLog(DuotaiTest.class);

	public static void main(String[] args) {
		m1();
		m2();
		m3();
		m4();
	}
	
	/** 
	* @Title: m1
	* @Description: 多态——通用性/可扩充性/可替换性。以后定义其他的动物子类，不会改变现有的类结构。或者修改a1的Dog为其他如Cow类，简单。
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void m1(){
		Animal a1 = new Dog("阿黄",5,true);
		Animal a2 = new Cat("阿喵",3,false);
		a1.rest(a1);
		a2.rest(a2);
	}
	
	/** 
	 * 
	* @Title: m2 
	* @Description: 父类的引用指向之类的对象，测试调用子类覆盖的方法。只会执行子类覆盖的方法。
	* @param     
	* @return void    
	* @throws 
	*/ 
	
	public static void m2(){
		Animal a = new Dog();
		a.eat();//output:[duotai.lei.Dog] 子类狗的进食方法是狼吞虎咽...
	}
	
	/** 
	* @Title: m3 
	* @Description: 父类的引用指向之类的对象，无法调用子类自己的方法
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void m3(){
		Animal a = new Dog();
		//没有a.goujiao()方法(点不出来)，因为申明的a是Animal类的实例，只能调用Animal的方法。
	}
	
	/** 
	* @Title: m4 
	* @Description:向下转型，使用子类特有方法。父类的引用指向之类的对象，强转成子类后，可以调用子类自己的方法  
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void m4(){
		Animal a = new Dog();
		//把父类的引用强制转换成子类后，可以调用子类自己的方法
		//想下转型，注意引用类型是否可以转，否则抛出ClassCastException类型转换异常。
		Dog d = (Dog) a;
		d.goujiao();//输出[duotai.lei.Dog] 子类狗的犬吠...
	}

}
