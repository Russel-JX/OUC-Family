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
	
	/* (非 Javadoc) 
	* <p>Title: equals</p> 
	* <p>Description: 重写equals方法，用于比较值是否相等。不考虑null的情况
	* @param obj
	* @return 
	* @see java.lang.Object#equals(java.lang.Object) 
	* 业务规则：
	* 	员工的姓名、年龄、身高都相同的视为同一个人
	*/ 
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}
		if(obj instanceof Employee ){
			Employee emp = (Employee)obj;
			if((this.getName().equals(emp.getName()))&&(this.getAge()==emp.getAge())&&(this.getHeight()==emp.getHeight())){
				return true;
			}
		}
		return false;
	}
	
	/* (非 Javadoc) 
	* <p>Title: hashCode</p> 
	* <p>Description:重写hashcode，用于满足集合中，如Set中元素不能有重复、Map中key不能重复的规定。
	* @return 
	* @see java.lang.Object#hashCode() 
	* java原则：
	* 	Employee的equals方法返回值相同的2个对象，hashCode必须不同。
	* 	具体：如果对象的hashcode不同，则返回值不同；如果hashCode相同
	* 逻辑规则：
	* 	对象的属性是引用类型的，去其引用类型的hashCode值；属性是数字类型的，取常量*属性值；最后相加，作为对象的hashCode。
	* 	
	*/ 
	@Override
	public int hashCode(){
		return (int)(this.getName().hashCode()+25*this.getAge()+30*this.getHeight());
	}
	
	
	
}
