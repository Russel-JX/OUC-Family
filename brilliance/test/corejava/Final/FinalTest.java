package corejava.Final;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

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
* 2.修饰方法上：此方法不能被重写
* 3.修饰属性、局部变量、方法形参等变量
* 		1.一定要初始化，才能使用，且只能初始化/赋值一次
* 			所以属性的初始化在创建对象的时候，可在属性定义处初始化，可在自由代码快中初始化
* 			局部变量的初始化，可以在定义的时候，也可在其他时候，只要在使用之前初始化，且只有一次即可。
* 			方法形参的初始化，在方法调用的时候，肯定会给实参，在被调用方法内部，不能再次修改final参数。对方法调用者没有影响。
* 		2.修饰基本数据类型和String类型值不变；修饰对象类型，引用不能改变，引用指向的对象可以更改。如对象的属性值，引用指向集合的元素等。
* 4.自定final属性，怎么保证对象类型不被改变。答：返回属性的副本。
* 		把final属性对象的各个细节copy到新变量中，返回新变量，即每次使用属性的get方法都把属性copy到新的对象返回。（不是地址赋值，地址复制返回的新变量地址就是final属性地址，这样还能修改final对象的值）
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
	public final Student stu = new Student("Tom",12,130);
	public final Student stu2 = new Student("Hellen",21,162);
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
		
		String friend  = "friend";
		friend = "enemy";
		
		//final修饰局部变量。引用类型不可变，引用的对象的属性可变。
		final Student student = new Student("Goody-Goody",18,150);
		student.setName("Norm");
//		student = new Student("Bady-Bady",18,150);//检查异常
		
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
		f1.getStu().setName("jack");
		f1.getStu().setAge(10);
		f1.getStu().setHeight(100);
		//final变量可以改变对象的值，不能改变引用。检查异常。
//		f1.getEmp() = new Student("cc",12,111);
		
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
		System.out.println(f1.stu2.hashCode()+"f1的员工的name是："+f1.stu2.getName());
		//getStu2方法返回的都是final属性的地址，所以不管怎么变化。final属性地址都不变（引用不变），值随意改变
		f1.getStu2().setName("Mike");
		System.out.println("final属性地址给新变量，f1的emp2的hashcode是"+f1.getStu2().hashCode()+"f1的员工的name是："+f1.getStu2().getName());
		System.out.println("final属性地址给新变量，f1的emp2的hashcode是"+f1.stu2.hashCode()+"f1的员工的name是："+f1.stu2.getName());
		
		FinalTest f2 = new FinalTest("3");
		System.out.println(f2.stu2.hashCode()+"f2的员工的name是："+f2.stu2.getName());
		//getStu2方法返回的都是final属性的副本，每次调用都是新的副本，副本之间独立。所以不管怎么变化。final属性地址都不变（引用不变），值随意改变
		f2.getStu2x().setName("Dang Self");
		//每次调用getStu2x方法，都会把final属性copy给新变量。所有getStu2x返回的每次都是内容一样，地址不同的变量。
		System.out.println("final属性细节copy给新变量，f2的emp2的hashcode是"+f2.getStu2x().hashCode()+"f1的员工的name是："+f2.getStu2x().getName());
		System.out.println("final属性细节copy给新变量，f2的emp2的hashcode是"+f2.stu2.hashCode()+"f2的员工的name是："+f2.stu2.getName());//final属性不变
		
		//将新变量的地址给另一个新变量，可以改变返回的新变量的值。
		Student s = f2.getStu2x();
		s.setName("haha");
		System.out.println("hashcode是："+s.hashCode()+"name是："+s.getName());
		
		//final修饰方法形参——基本数据类型、String
		calculate1(1);
		//final修饰方法形参——对象类型
		calculate2(new Student("Bob",80,150));
	}
	
	/** 
	* @Title: calculate 
	* @Description: final修饰方法参数——基本类型或String
	* @param @param x
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* 在被调用方法内，final修饰的形参不能改变（检查异常）。保证入参的不变性。
	*/ 
	public static int calculate1(final int  x){
//		x = 0;//检查异常
//		x++;//检查异常
		return x;
	}
	
	/** 
	* @Title: calculate2 
	* @Description: final修饰方法参数——对象类型
	* @param @param stu
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* 同规定一样，final变量的引用类型不能变，值可变。即不能给引用赋值，可以改变引用对象的属性值。
	*/ 
	public static int calculate2(final Student stu){
		stu.setName("Mackey");
		stu.setAge(60);
//		stu = new Student();//检查异常
		return 9;
	}

	public String getName() {
		return name;
	}
	
	public void setAddressList(String address){
		this.addressList.add(address);
	}

	public Student getStu() {
		return stu;
	}
	
	//返回final对象的副本。将final属性给新的变量，相当于把final属性的地址给了别人，还是可以通过操作变量地址修改final属性的值
	public Student getStu2() {
		Student stu = new Student();
		stu = stu2;
		return stu;
	}
	
	//返回final对象的副本。将final属性的细节copy给新的变量，这样final属性和新变量各自独立，操作新变量不会改变final属性的值。
	//并且每次调用此方法，多会创建新的副本，副本之间独立。
	public Student getStu2x() {
		Student stu = new Student();
		stu.setName(stu2.getName());
		stu.setAge(stu2.getAge());
		stu.setHeight(stu2.getHeight());
		return stu;
	}
	
	

}
