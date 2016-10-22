package thisInClass;

public class Employee {
	
	static{
		System.out.println("静态语句块...");
	}

	private String name;
	private int age;
	private double height;
	
	public String getName() {
		return name;
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
	
	public Employee() {
		super();
		System.out.println("初始化无参构造函数...");
	}
	
	public Employee(String name, int age, double height) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		System.out.println("初始化有参构造函数...");
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("员工的姓名是：%s,年龄是：%s,身高是：%s",this.getName(),this.getAge(),this.getHeight());
	}
	
}
