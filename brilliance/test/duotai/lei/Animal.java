package duotai.lei;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 多态知识测试类一
* @ClassName: Animal
* @Package duotai.lei
* @Description: 定义父类动物类，
* 	有此类共有的属性：名称，年龄，性别
* 	有此类共有的方法：进食，休息，运动
* @author Russell Xun Jiang
* @date 2016年10月29日 上午10:04:04
*/

public class Animal implements Serializable{
	
	private static final long serialVersionUID = 5988748363731728540L;

	protected  Log logger = LogFactory.getLog(Animal.class);
	
	//名称
	private String name;
	//年龄
	private int age;
	//性别
	private boolean gender;
	
	public void eat(){
		logger.info("Animal的共有进食方法...");
	}
	
	public void rest(Animal ani){
		logger.info("Animal的共有休息方法。名称："+ani.getName()+"年龄："+ani.getAge()+"性别："+ani.getGender()+"在睡觉。");
	}
	
	public void move(Animal ani){
		logger.info("Animal的共有运动方法...");
	}
	
	//无参构造方法
	public Animal() {
		super();
	}
	
	//有参构造方法
	public Animal(String name, int age, boolean gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

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

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

}
