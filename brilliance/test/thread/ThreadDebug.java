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
* 1.将断点打在run方法里。每个线程都会运行run方法。
	这样所有的线程执行到run方法的某个断点处都会暂停。
  2.开启debug。
  3.进入debug视图。
	所有执行此方法的线程都暂停在断点处，见Thread[Thread-0],Thread[Thread-1]..
	鼠标选中某个线程，F6或F8，则此线程先执行。
      注：Thread.currentThread().getName()用于打印当前线程名，即定位哪个线程在运行。
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
