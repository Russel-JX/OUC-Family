package corejava.generics;

import java.util.ArrayList;
import java.util.List;

import corejava.entity.Doctor;
import corejava.entity.Hospital;
import corejava.entity.Nurse;
import corejava.entity.Worker;

/**
* @ClassName: NormalGenerics
* @Package corejava.generics
* @Description: 泛型基本知识
* @author Russell Xun Jiang
* @date 2016年12月27日 上午11:14:20
* 1.
*/
public class NormalGenerics{

	public static void main(String[] args) {
//		List<String> l = new ArrayList<String>();
//		l.equals(null);
		
//		//泛型在集合中可以存放不同数据类型的数据。
//		diffDataType();
		
//		//泛型定义类。初始化类的时候，确定泛型的具体数据类型
//		genericsInClass();
		
//		//泛型定义方法。调用方法的时候，确定泛型的数据类型
//		genericsInMethod();
		
//		//泛型定义接口。实现类不具体化泛型  
//		genericsInInterface1();
		
		//泛型定义接口。实现类不具体化泛型  
		genericsInInterface2();
	}
	
	/** 
	* @Title: eleInJihe 
	* @Description: (1)泛型在集合中可以存放不同数据类型的数据。 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 只要元素数据类型是泛型数据类型的同类或子类即可
	*/ 
	public static void diffDataType(){
		List<Number> list = new ArrayList<Number>();
		list.add(10);//int
		list.add(11.2);//double
		list.add(12356L);//long
		System.out.println(list);
		"ss".equals("");
		
		List<Hospital> hosList = new ArrayList<Hospital>();
		hosList.add(new Doctor("Li Gang",3,1,30));
		hosList.add(new Nurse("Wang Xiao Li",2,2,25));
		System.out.println("集合中医生姓名是："+((Doctor)hosList.get(0)).getDocName());
		System.out.println("集合中护士姓名是："+((Nurse)hosList.get(1)).getNurName());
	}
	
	/** 
	* @Title: genericsInClass 
	* @Description: 测试泛型在类上 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 泛型修饰类。
	* 	可在类实例化类的时候，将泛型表示的具体类型明确化到：类成员属性、方法参数等
	* 类实例化之后，实例这个对象中的所有之前不确定的使用泛型的地方（如成员变量、方法），都确定了成员变量的数据类型或方法参数的数据类型，就是实例化时指定的数据类型。
	*/ 
	public static void genericsInClass(){
		GenericClass1<String> g1 = new GenericClass1<String>();//实例化类时，给类的实例确定，此对象中使用的泛型为：字符串类型
		g1.setW("Hello World!");//再调用包含泛型的方法时。就知道这里传的参数类型就是字符串了。
		System.out.println("泛型对象所指定的数据是："+g1.getW());
		System.out.println("调用泛型方法："+g1.isNull(null));
		System.out.println("调用泛型方法："+g1.isNull("abc"));
		
		GenericClass1<Worker> g2 = new GenericClass1<Worker>();//实例化类时，给类的实例确定，此对象中使用的泛型为：自定义的工人类型
		g2.setW(new Worker("Tom",20,30,"Plunck"));//再调用包含泛型的方法时。就知道这里传的参数类型就是worker对象类型了。
		System.out.println(g2.getW());
		System.out.println("调用泛型方法："+g2.isNull(null));
		System.out.println("调用泛型方法："+g2.isNull(new Worker("Tom2",220,320,"Plunck2")));
	}
	
	/** 
	* @Title: genericsInMethod 
	* @Description: 测试定义方法时使用泛型
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void genericsInMethod(){
		GenericClass2 g = new GenericClass2();
		g.show("PQR");
		g.show(3.14);
		g.show(new Worker("Jack",77,88,"Jacky"));
	}
	
	/** 
	* @Title: genericsInInterface 
	* @Description: 测试泛型定义在接口上——实现类具体化泛型 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void genericsInInterface1(){
		//接口的引用指向实现类的对象。不能调用实现类的属性和实现类自己的方法。
		GenericInterface<Worker> g = new GenericClass3();//之前定义实现类的时候已经确定了泛型的具体数据类型了
		Worker w = g.getValue();
		System.out.println("w="+w);
		g.setValue(new Worker("Leo",40,40,"li"));
//		g.setWorker();//这一句无法使用
		
		//接口的引用指向实现类的对象。可以调用实现类的属性和实现类自己的方法。
		GenericClass3 g2 = new GenericClass3();
		g2.setWorker(new Worker("Russell",40,40,"R"));
		Worker w2 = g2.getWorker();
		System.out.println("类的成员变量："+w2);
		
		
	}
	
	/** 
	* @Title: genericsInInterface2 
	* @Description: 测试泛型定义在接口上——实现类不具体化泛型  
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 
	*/ 
	public static void genericsInInterface2(){
		GenericInterface<String> g1 = new GenericClass4<String>();
		g1.setValue("abcd");
		String s = g1.getValue();
		System.out.println("s="+s);
		
		GenericInterface<Worker> g2 = new GenericClass4<Worker>();
		g2.setValue(new Worker("Bigger Ge",10,11,"bold"));
		Worker worker = g2.getValue();
		System.out.println("worker="+worker);
	}
	
	/** 
	* @Title: genericsScope 
	* @Description: 测试泛型的范围限定 （未完成）
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 
	*/ 
	public static void genericsScope(){
		
	}
	
	
	/**
	* @ClassName: GenericClass1
	* @Package corejava.generics
	* @Description: 定义类时使用泛型
	* @author Russell Xun Jiang
	* @date 2016年12月27日 下午3:17:26
	* 
	* @param <What>	泛型标识符。
	* 
	* 可在类实例化类的时候，将泛型表示的具体类型明确化到：类成员属性、方法参数等。
	* 接类初始化时，确定泛型的数据类型
	*/
	
	public static class GenericClass1<What>{
		private What w;
		private String name;
		
		//打印泛型
		public void printW(What w){
			System.out.println(w);
		}
		
		//如果泛型对象为空，返回空；否则，返回泛型。
		public What isNull(What w){
			if(w==null){
				return null;
			}
			return w;
		} 
		
		public What getW() {
			return w;
		}
		public void setW(What w) {
			this.w = w;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	/**
	* @ClassName: GenericClass2
	* @Package corejava.generics
	* @Description: 定义类时使用泛型
	* @author Russell Xun Jiang
	* @date 2016年12月27日 下午3:43:26
	* 和GenericClass1类似
	* 	调用方法的时候，确定泛型的具体数据类型。
	*/
	public static class GenericClass2 {
		//调用方法的时候，确定泛型的具体数据类型。
		//注：这里泛型的确定不是类实例化的时候传过来的数据类型,GenericClass2在类上已经定义好了泛型<What>。
		//所以在方法上定义了泛型，泛型的定义加尖括号“<>”，表示这里定义泛型,角括号内标识符任意，保持定义和使用标识符一致即可。
		public <T> void show(T t) {
			System.out.println(t);
		}
	}
	
	/**
	* @ClassName: GenericInterface
	* @Package corejava.generics
	* @Description: 泛型定义在接口上
	* @author Russell Xun Jiang
	* @date 2016年12月27日 下午3:55:54
	* @param <G>	泛型标识符
	* 和定义在类或者方法上类似。
	* 	定义在接口上，让实现类实现接口。
	* 使用的时候，两中方式
	* 	定义实现类的时，指定数据类型；
	* 	创建实现类的实例时，指定数据类型；
	*/
	public interface GenericInterface<G> {
		public G getValue();
		public void setValue(G g);
		public void printValue(int i);
	}
	/**
	* @ClassName: GenericClass3
	* @Package corejava.generics
	* @Description: 实现类实现含有泛型定义的接口——在实现类定义时确定泛型的具体数据类型
	* @author Russell Xun Jiang
	* @date 2016年12月27日 下午4:13:17
	*/
	public static class GenericClass3 implements GenericInterface<Worker> {
		private Worker worker;//实现类成员变量
		public Worker getWorker() {
			return worker;
		}
		public void setWorker(Worker worker) {
			this.worker = worker;
		}
		
		public GenericClass3(){
			super();
		}
		@Override
		public Worker getValue() {
			return new Worker("NormalWorker",10,10,"Yoo!");
		}
		@Override
		public void setValue(Worker g) {
			
		}
		@Override
		public void printValue(int i) {
			System.out.println("i的值是："+i);
		}
		
		
	}
	
	/**
	* @ClassName: GenericClass4
	* @Package corejava.generics
	* @Description: 实现类实现含有泛型定义的接口——在实现类定义时不确定泛型的具体数据类型
	* @author Russell Xun Jiang
	* @date 2016年12月27日 下午4:57:36
	* 
	* @param <X>	泛型标识符
	* 在实现类的对象实例化时，确定泛型的具体数据类型
	*/
	public static class GenericClass4<X> implements GenericInterface<X> {
		private X x;
		
		@Override
		public X getValue() {
			return x;
		}

		@Override
		public void setValue(X g) {
			x = g;
		}

		@Override
		public void printValue(int i) {
			
		}
	}
}
