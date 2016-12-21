package corejava;

import java.util.ArrayList;
import java.util.List;

import thisInClass.Employee;

/**
* @ClassName: ReferenceAndObject
* @Package corejava
* @Description: 方法无返回值，调用方法后，尽然改变了调用者传入的实参。（变量引用和变量真实值）
* @author Russell Xun Jiang
* @date 2016年12月21日 下午11:38:11
* 调用方法传参是对象类型时，传的对象的引用，即地址。
* 调用方法传参是基本数据类型时，传的对象本身。
* 
*/
public class ReferenceAndObject {

	public static void main(String[] args) {
		
		//传参基本数据类型。
		int a = 0;
		updateValue(a);
		System.out.println("a="+a);//0
		
		//传参字符串
		String b = "aaa";
		updateString(b);
		System.out.println("b="+b);//aaa
		
		//通过引用传参——传递集合的引用。即常说的传递对象
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		System.out.println("list="+list);//[1, 2]
		updateValuebyReference(list);
		System.out.println("list="+list);//[1, 2, 3, 4]
		
		//通过引用传参——传递对象的引用。
		Employee emp = new Employee("Tom",20,20);
		updateValuebyReference2(emp);
		System.out.println("emp="+emp);//emp=员工的姓名是：Got You!,年龄是：60,身高是：600.0

	}
	
	public static void updateValue(int param){
		param = 10;
	}
	
	public static void updateString(String param){
		param = "bbb";
	}
	
	/** 
	* @Title: updateValuebyReference 
	* @Description: 引用传递 
	* @param @param param    设定文件 
	* @return void    返回类型 
	* @throws 
	* 传给方法形参的是对象的引用，那么在方法内对此引用所知道的值操作后，也会反应到方法调用者那。
	* 因为调用者传的实参和方法形参都是指向同一个内存地址，操作的是同一个对象空间。
	*/ 
	public static void updateValuebyReference(List<String> param){
		param.add("3");
		param.add("4");
	}
	
	public static void updateValuebyReference2(Employee param){
		param.setName("Got You!");
		param.setAge(60);
		param.setHeight(600);
	}

}
