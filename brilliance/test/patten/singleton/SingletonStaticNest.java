package patten.singleton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import thread.ThreadCreation;

/**
* @ClassName: SingletonLazy
* @Package patten.singleton
* @Description: 非单例模式——静态内部类实现方式
* @author Russell Xun Jiang
* @date 2016年11月30日 下午4:22:46
* 创建对象的方法，再去调用内部类的常量。
* 这种方式是线程安全的。
* 优点：懒加载方式。只有调用了方法，才回去创建，而不是上来就加载。
* 缺点：同SingletonNonLazy一样，实例是固定的。不能通过参数来创建实例。所以不能通过配置文件，或方法传参来使用
*/
public class SingletonStaticNest {
	private static Log logger = LogFactory.getLog(SingletonStaticNest.class);
	
	//在SingletonNonLazy的基础上，内部类中定义的静态常量
	private static class SingletonNest{
		private static final SingletonStaticNest entity = new SingletonStaticNest();
	}
	public static SingletonStaticNest getInstance(){
		return SingletonNest.entity;
	}

	public static void main(String[] args) {
		SingletonStaticNest e1 = SingletonStaticNest.getInstance();
		SingletonStaticNest e2 = SingletonStaticNest.getInstance();
		SingletonStaticNest e3 = SingletonStaticNest.getInstance();
		logger.info("对象："+e1);//打印出引用的内存地址。即可知道是否是同一实例了。
		logger.info("对象："+e2);//打印出引用的内存地址。即可知道是否是同一实例了。
		logger.info("对象："+e3);//打印出引用的内存地址。即可知道是否是同一实例了。
	}
}
