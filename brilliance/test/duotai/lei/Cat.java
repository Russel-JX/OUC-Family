package duotai.lei;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *  多态知识测试类三
* @ClassName: Cat
* @Package duotai.lei
* @Description: 
* 	共有的属性（在Animal中）：名称，年龄，性别
* 	私有的属性：爪子大小
* 	共有的方法（在Animal中）：进食，休息，运动
* 	私有的方法：爬树
* @author Russell Xun Jiang
* @date 2016年10月29日 上午10:24:34
* 
*/

public class Cat extends Animal{
	private Log logger = LogFactory.getLog(Cat.class);
	
	//爪子大小
	private int clawSize;
	
	//覆盖父类的饮食方法
	public void eat(){
		logger.info("猫吃饭小口小口的...");
	}
	
	//子类独有的爬树方法
	public void climb(){
		logger.info("猫独有的方法，猫会爬树...");
	}
	
	//构造方法
	public Cat(String name, int age, boolean gender) {
		super(name, age, gender);
	}
	
	//构造方法
	public Cat(String name, int age, boolean gender,int clawSize) {
		super(name, age, gender);
		this.clawSize = clawSize;
	}
	
	public int getClawSize() {
		return clawSize;
	}

	public void setClawSize(int clawSize) {
		this.clawSize = clawSize;
	}

}
