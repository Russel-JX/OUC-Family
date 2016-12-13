package corejava.Final;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import thisInClass.Employee;

/**
* @ClassName: FinalTest
* @Package corejava.Final
* @Description: final关键字
* @author Russell Xun Jiang
* @date 2016年12月13日 下午7:41:40
* final修饰的在编译器绑定，是静态绑定。
* 修饰基本数据类型和String是内容不可变，修饰引用类型是引用类型不可变(即地址不变)，引用指向的对象可变。
* 	所以在set方法中或任意地方，可以改变final集合或final对象的内容，但不能改变final引用。
* 接口中声明的所有变量本身是final的。
* 1,2,3都会报检查异常
* 1.修饰类上：此类不能被继承
* 		此类成为“不可变类”，如String类。
* 2.修饰方法上，此方法不能被重写
* 3.修饰属性上，必须要先初始化，才能使用；有且只能初始化一次。
	* 	初始化不要求一定在定义处初始化，只要使用类的实例之前初始化就行。所以创建的时候初始化即可。
	* 	可在属性定义处初始化，
	* 	可在自由代码快中初始化，
	* 	在静态代码块虽然在创建类之前就会运行，但如果属性不是静态常量变量是不能再静态代码块中初始化的，因为静态代码块在创建实例之前运行。
* 4.修饰局部变量。类似修饰属性，必须要先初始化，才能使用；有且只能初始化一次。初始化的时机没有要求，只要在使用前初始化就行。即只能赋值一次。
* 5.修饰方法参数（未完成）
* 6.自定final属性，怎么保证对象类型不被改变，返回属性的副本（未完成）
*/
public class FinalTest {//final
	
	//非final变量
	public int weight;
	
	//final定义常量，定义时初始化
	public final int age = 26;//final属性必须初始化，且只能初始化一次
	
	//final定义常量
	public final String school;
	//常量在自由代码块中初始化
	{
		school = "Xuji Middle School";//final属性必须初始化
//		school = "Xuji Middle School2";//只能初始化一次
	}
	
	//final定义常量
	public final String grade;
	//常量在构造函数中初始化
	public FinalTest(String grade){
		this.grade = grade;//final属性必须初始化
//		this.grade = "4";//只能初始化一次
	}
	
	//final定义常量
	public final String name = "Scott";
	
	//final定义引用类型。如对象、集合等。引用类型不可变，引用指向的对象可变。
	public final Employee emp = new Employee("Tom",12,130);
	public final Employee emp2 = new Employee("Hellen",21,162);
	public final List<String> addressList = new LinkedList<String>();
	
	
	//非final定义方法
	public int getAge1(int birthday){
		return Calendar.getInstance().get(Calendar.YEAR)-birthday;
	}
	
	//final定义方法
	public final int getAge2(int birthday){
		return Calendar.getInstance().get(Calendar.YEAR)-birthday;
	}

	public static void main(String[] args) {
		//final修饰局部变量，一定要初始化。但不要求在定义的时候初始化，可在任意时刻初始化，但只能赋值一次，不能赋值多次，否则报检查异常。
		final boolean gender;
		gender = true;//必须初始化
//		gender = true;//并且只能初始化一次
		System.out.println("gender"+gender);
		
		FinalTest f1 = new FinalTest("3");
		System.out.println("初始化实例后，final属性name值是："+f1.name+"，年级是："+f1.grade);
		//不管在哪（方法中或代码里）final变量不能再次赋值，报检查异常：The final field FinalTest.name cannot be assigned
//		f1.name = "abc";
//		f1.age = 20;
//		f1.school = "gaozhong";
		
		//对象类型的final属性。引用不会改变，引用指向的对象会被改变。
		changeFinalObject();
		
		//返回final属性的副本，保证对象类型的final属性值不被修改。
		changeFinalObjectReturnCopy();
	}
	
	//改变对象类型
	public static void changeFinalObject(){
		FinalTest f1 = new FinalTest("3");
		//对象情况：final变量可以改变对象的值，不能改变引用
		f1.getEmp().setName("jack");
		f1.getEmp().setAge(10);
		f1.getEmp().setHeight(100);
		
		//final变量可以改变对象的值，不能改变引用。检查异常。
//		f1.getEmp() = new Employee("cc",12,111);
		
		//将对象的final属性拷贝出来，可以任意使用。不受final的限制。
		Employee emp2= f1.getEmp();
		emp2 = new Employee("cc",12,111);
		
		//集合情况：final变量可以改变对象的值，不能改变引用
		f1.addressList.add("a");
		f1.addressList.add("b");
		f1.addressList.add("c");
		f1.addressList.remove(0);
		//集合情况：final变量可以改变对象的值，不能改变引用。检查异常。
//		f1.addressList = null;
	}
	
	//返回属性的副本，而不是属性。从而保护引用类型的成员变量的值不被改变(未完成)
	public static void changeFinalObjectReturnCopy(){
		FinalTest f1 = new FinalTest("3");
		Employee en = new Employee();
		en = f1.getEmp2();
		en.setName("Mike");
		System.out.println("f1的员工的name是："+f1.getEmp2().getName());
		System.out.println("f1的员工的name是："+f1.emp2.getName());
	}

	public void setName(String name){
		//不管在哪（方法中或代码里）final变量不能再次赋值，报检查异常：The final field FinalTest.name cannot be assigned
//		this.name = name;
		
	}
	public String getName() {
		return name;
	}
	
	public void setAddressList(String address){
		this.addressList.add(address);
	}

	public Employee getEmp() {
		return emp;
	}
	
	//返回final对象的副本（未完成）
	public Employee getEmp2() {
		Employee e = new Employee();
		e = emp2;
		return e;
	}
	
	

}
