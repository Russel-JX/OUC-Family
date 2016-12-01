package patten.singleton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import thread.ThreadCreation;

/**
* @ClassName: SingletonLazy
* @Package patten.singleton
* @Description: 懒汉单例模式（调用类的方法是才会创建实例）
* 非懒汉式：类加载的时候就创建。
* private static final Singleton instance = new Singleton();
* @author Russell Xun Jiang
* @date 2016年11月30日 下午4:22:46
* 
* 如果多个线程来创建实例。都运行完(1)结束,未运行(2)。由于线程互不影响，不知道对方，则会创建多个实例，则单例失败。
* 	
*/
public class SingletonLazy {
	private static Log logger = LogFactory.getLog(SingletonLazy.class);
	
	private static SingletonLazy entity;
	public static SingletonLazy getInstance(){
		if(entity==null){//(1)
			logger.info(Thread.currentThread().getName()+"正在创建实例...");//(1.1)
			entity = new SingletonLazy();//(2)
			return entity;//(3)
		}
		return entity;//(4)
	}
	public static void main(String[] args) {
		/*
		 * 正常情况
		 * 因为只有一个线程(Main线程),所以下方3次创建实例，是按顺序执行的，第一句创建实例之后，后面的判断实例非空就不会再创建了。
		 */
		SingletonLazy.getInstance();
		SingletonLazy.getInstance();
		SingletonLazy.getInstance();
		
		/*
		 * 反例
		 * 测试条件：100线程去创建100此实例。
		 * 测试结果：会陆续创建10几个实例。
		 * 结论：说明多个线程有时会同时进入(1)代码之后，重复创建实例。
		 * 或者创建几个线程去测试，因为线程少的话，CPU执行太快。每一个线程会同时进入(1)后面的代码，重复创建实例。
		 * 也可以用断点去测试，让线程都停留在(1.1),有多少个线程，将会创建多少个对象。
		 */
//		Thread ts[] = new ThreadA[100];
//		for(Thread t:ts){
//			t = new ThreadA();
//			t.start();
//		}
	}
	
	public static class ThreadA extends Thread{
		@Override
		public void run() {
			SingletonLazy.getInstance();
		}
	}
}
