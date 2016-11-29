package thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ThreadCreation {
	private static Log logger = LogFactory.getLog(ThreadCreation.class);

	public static void main(String[] args) {
		ThreadA ta = new ThreadA("TA");
		ThreadB targetb = new ThreadB();
		Thread tb = new Thread(targetb);
		Thread tc = new ThreadC("TC");
		Thread td = createThread();
		//不管怎么创建的线程，启动线程都是调用start方法。
		ta.start();
		tb.start();
		tc.start();
		td.start();
	}
	
	//(1)类实现Runnable接口。类中创建线程。
	public static class ThreadA implements Runnable{
		private Thread thread;
		
		public ThreadA(String threadName){
			thread = new Thread(this,threadName);//
		}
		@Override
		public void run() {
			logger.info("run TA...");
		}
		
		//因为Runnable接口只有一个run方法。所以实现接口的类，在类中自己创建包含Thread的start方法的方法。
		public void start(){
			thread.start();
		}
	}
	//(2)类实现Runnable接口。
	public static class ThreadB implements Runnable{
		@Override
		public void run() {
			logger.info("run TB...");
		}
	}
	//(3)继承自Thread类
	public static class ThreadC extends Thread{
		private Thread thread;
		public ThreadC(String threadName){
			thread = new Thread(threadName);
		}
		public void run(){
			logger.info("run TC...");
		}
	}
	//(4)在方法中创建线程。
	public static Thread createThread(){
		return new Thread("ThreadD"){
			public void run(){
				logger.info("run TD...");
			}
		};
	}

}
