package corejava.entity;

import java.io.Serializable;

/**
* @ClassName: Worker
* @Package corejava.entity
* @Description: java对象序列化
* @author Russell Xun Jiang
* @date 2016年12月24日 下午12:31:20
* http://www.jianshu.com/p/a73e1202e41d
* 必须实现Serializable接口
* 序列化和反序列化时都是根据创造对象的这个类中的序列号serialVersionUID（常量）确定是否可以反序列化。
* 	serialVersionUID相当于这个类的版本号。与其他类没有任何关系。
* 	序列化之后的文件中保留了对象所属类的serialVersionUID值；（名字一定是serialVersionUID不能用别的名字。原因自己查源码）
* 	如果不显示定义serialVersionUID序列号，工农根据会根据类的类名、属性、方法等默认计算出serialVersionUID的值。
* 		所以每次改动属性后serialVersionUID，重新计算就变了。
* 		反序列化时，ObjectInputStream读取对象时，拿此对象的serialVersionUID和本地类中的serialVersionUID比较。一致才能反序列化（表示类信息没有被改动过），否则反序列化失败。
* 		所以，当序列化到文件或数据库的对象保存后，改动了类中的结构（修改属性名称、类型，增加修改属性等，改动属性值没有影响），反序列化时一定失败（抛异常：java.io.InvalidClassException）。
* 这时要么不要改类结构，要么改完之后重新序列化对象，覆盖原来的对象（把新的序列号放在对象中）。
* 	显示定义了serialVersionUID之后（Eclispe自动计算），修改类就不会出现反序列化失败的问题。因为序列化对象中的serialVersionUID固定，而类的serialVersionUID也固定死了永远相等。
* 	
* 总结，serialVersionUID只是类的版本号（long类型表示）。只要保证不同修改的版本号不同即可。可以自己设置版本号的值，也可以让Eclipse计算。
* 	1.自定义可以从1L开始
* 	2.工具生成。根据会根据类的类名、属性、方法等默认计算出serialVersionUID的值。
* 每次修改类结构后，自己将serialVersionUID加1，或者使用工具生成新的serialVersionUID都行。
* 	注：如果改了类结构，不改serialVersionUID。则以前序列化的对象可以使用修改后的类反序列化，叫做兼容老版本。否则不能以前的序列化对象，无法反序列化，即不兼容老版本。
* 	
* trasient变量不会被序列化和反序列化，直接忽略
* static变量不会被序列化和反序列化，不用序列化，他的值是静态的执行的类加载到JVM中时，最先初始化了。
* 
*/
public class Worker implements Serializable{
	
	//类的版本号。
	private static final long serialVersionUID = -3055432144038137794L;
	private String name;
	private int age;
	private double height;
	private final String school = "Zhengliangmei series school";
	public static String hometown = "Big Huaian";//静态属性
	private transient String nickname;//trasient属性
	
	public Worker(String name, int age, double height, String nickname) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.nickname = nickname;
	}
	
	public String toString(){
		return "工人姓名是："+this.getName()+",年龄是："+this.getAge()+",身高是："+this.getHeight()+
				",学校是："+this.getSchool()+",家乡是："+hometown+",绰号是："+this.getNickname();
	}
	
	public String getName() {
		return name+"fff";
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSchool() {
		return school;
	}

	public static String getHometown() {
		return hometown;
	}

	public static void setHometown(String hometown) {
		Worker.hometown = hometown;
	}
	
}
