package patten.singleton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import thread.ThreadCreation;

/**
* @ClassName: SingletonLazy
* @Package patten.singleton
* @Description: 非懒汉单例模式()
* @author Russell Xun Jiang
* @date 2016年11月30日 下午4:22:46
* 
* 创建常量作为类的实例，类加载的时候就创建了——“非懒汉”。
* 这种方式是线程安全的。
* 缺点：
* 	实例是规定的。不能通过参数来创建实例。所以不能通过配置文件，或方法传参来使用
*/
public class SingletonNonLazy {
	private static Log logger = LogFactory.getLog(SingletonNonLazy.class);
	
	private String name;
	private int age;
	
	private static final SingletonNonLazy entity = new SingletonNonLazy();
	
	public static SingletonNonLazy getInstance(){
		return entity;
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


	public static void main(String[] args) {
		SingletonNonLazy e1 = SingletonNonLazy.getInstance();
		SingletonNonLazy e2 = SingletonNonLazy.getInstance();
		SingletonNonLazy e3 = SingletonNonLazy.getInstance();
		logger.info("对象："+e1);//打印出引用的内存地址。即可知道是否是同一实例了。
		logger.info("对象："+e2);//打印出引用的内存地址。即可知道是否是同一实例了。
		logger.info("对象："+e3);//打印出引用的内存地址。即可知道是否是同一实例了。
	}
}
