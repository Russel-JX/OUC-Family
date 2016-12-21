package thread.unsynchronized;

/**
* @ClassName: OfficeThread12306
* @Package thread.unsynchronized
* @Description: 
* @author Russell Xun Jiang
* @date 2016年12月21日 下午10:01:50
* 如果一个类继承Thread，则不适合资源共享。如果实现了Runable接口的话，则很容易的实现资源共享。
*/
public class OfficeThread12306 extends Thread{
	//余票总数
	private int balance; 
	
	public OfficeThread12306(int bala){
		this.balance = bala;
	}
	
	//售票方法
	public void sellTicket(){
		//两个线程（两个售票窗口）不停的卖票
		while(balance!=0){
			balance = balance-1;
			System.out.println(Thread.currentThread().getName()+":balance="+balance);
		}
		System.out.println(Thread.currentThread().getName()+":票卖完了！");
		
	}
	
	public void run(){
		sellTicket();
	}
	
	public static void mx(int z){
		z = 10;
	}
	
	public static void my(String z){
		z = "bbb";
	}

	public static void main(String[] args) {
		int balance = 100;
		Thread t1 = new OfficeThread12306(balance);
		Thread t2 = new OfficeThread12306(balance);
		
		//正常启动线程。继承自Thread累的线程无法共享资源
		normalStart(t1,t2);//输出结果：线程t1,t2交替执行。但都是从100递减到0。没有共享票源。
		
//		//直接运行run方法
//		directRun(t1,t2);
		
//		//重复调用start方法 
//		restart(t1);
		
	}
	
	/** 
	* @Title: normalStart 
	* @Description: 正常启动线程 
	* @param @param t1
	* @param @param t2    设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void normalStart(Thread t1,Thread t2){
		t1.start();
		t2.start();
	}
	
	/** 
	* @Title: directRun 
	* @Description: 直接运行run方法 
	* @param @param t1
	* @param @param t2    设定文件 
	* @return void    返回类型 
	* @throws 
	* 相当于执行的普通方法，因为没有开启线程，和线程无关，或者说只有一个线程在执行。多个线程对象直接调用run方法，只有一个线程按正常顺序执行。cpu不会对多个线程进行切换调度。
	*/ 
	public static void directRun(Thread t1,Thread t2){
		t1.run();
		t2.run();
	}
	
	/** 
	* @Title: restart 
	* @Description: 重复调用start方法 
	* @param @param t    设定文件 
	* @return void    返回类型 
	* @throws 
	* 抛出异常： java.lang.IllegalThreadStateException
	* 线程已经执行完毕状态，不是可执行状态，不能再执行。抛异常：java.lang.IllegalThreadStateException
	*/ 
	public static void restart(Thread t){
		t.start();
		t.start();
	}

}
