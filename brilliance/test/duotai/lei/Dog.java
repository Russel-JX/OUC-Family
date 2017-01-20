package duotai.lei;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *  多态知识测试类二
* @ClassName: Dog
* @Package duotai.lei
* @Description: 子类
* 	共有的属性（在Animal中）：名称，年龄，性别
* 	私有的属性：牙齿大小，鼻子灵敏度
* 	共有的方法（在Animal中）：进食，休息，运动
* 	私有的方法：叫唤，
* @author Russell Xun Jiang
* @date 2016年10月29日 上午10:24:11
* 
*/

public class Dog extends Animal{
	private Log logger = LogFactory.getLog(Dog.class);
	//牙齿大小
	private double toothSize;
	//鼻子灵敏度
	private double noseSense;
	
	//子类狗的进食方法
	public void eat(){
		logger.info("子类狗的进食方法是狼吞虎咽（覆盖）...");
	}
	
	//子类狗的休息方法
	public void rest(){
		logger.info("子类狗的休息是趴着睡觉（覆盖）...");
	}
	
	//子类狗的叫唤方法
	public void goujiao(){
		logger.info("子类狗的犬吠...");
	}
	
	//无参构造方法
	public Dog() {
		
	}

	//有参构造方法
	public Dog(String name,int age,boolean gender) {
		super(name,age,gender);
	}
	
	//有参构造方法
	public Dog(String name,int age,boolean gender,double toothSize, double noseSense) {
		super(name,age,gender);
		this.toothSize = toothSize;
		this.noseSense = noseSense;
	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	public double getToothSize() {
		return toothSize;
	}

	public void setToothSize(double toothSize) {
		this.toothSize = toothSize;
	}

	public double getNoseSense() {
		return noseSense;
	}

	public void setNoseSense(double noseSense) {
		this.noseSense = noseSense;
	}
	
	

}

