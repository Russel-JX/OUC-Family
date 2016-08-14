package thisInClass;

public class Employee {

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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("员工的姓名是：%s,年龄是：%s,身高是：%s",this.getName(),this.getAge(),this.getHeight());
	}
	
}
