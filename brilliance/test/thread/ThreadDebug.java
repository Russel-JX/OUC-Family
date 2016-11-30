package thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jihe.map.MapTest;

/**
* @ClassName: ThreadDebug
* @Package thread
* @Description: 线程调试debug
* @author Russell Xun Jiang
* @date 2016年11月30日 下午3:49:53
* 
*/
public class ThreadDebug {
	private static Log logger = LogFactory.getLog(MapTest.class);
	public static void main(String[] args) {
		Runnable target1 = new ThreadA();
		Runnable target2 = new ThreadA();
		Runnable target3 = new ThreadA();
		Runnable target4 = new ThreadA();
		Runnable target5 = new ThreadA();
		Thread t1 = new Thread(target1);
		Thread t2 = new Thread(target2);
		Thread t3 = new Thread(target3);
		Thread t4 = new Thread(target4);
		Thread t5 = new Thread(target5);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
	
	public static class ThreadA implements Runnable{

		@Override
		public void run() {
			logger.info("当前线程是："+Thread.currentThread().getName()+",run ThreadA...");
		}
	}

}
