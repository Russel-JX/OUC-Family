package thread.unsynchronized;

/**
* @ClassName: OfficeRunnable12306
* @Package thread.unsynchronized
* @Description: 模拟12306售票
* @author Russell Xun Jiang
* @date 2016年12月21日 下午10:01:50
*/
public class OfficeRunnableRight12306 implements Runnable{
	//余票总数
	private int balance; 
	
	public OfficeRunnableRight12306(int bala){
		this.balance = bala;
	}
	
	
	/** 
	* @Title: sellTicket 
	* @Description: 售票方法
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 此方法将共享资源——余票数量，保护。没有问题。
	* 售票方法内部看成是原子操作，摸个线程执行时，其他线程不能执行。保证了票源的独立性。
	*/ 
	public void sellTicket(){
		//两个线程（两个售票窗口）不停的卖票
		while(balance>0){//(0)
			int temp = balance-1;//(1)
			balance = temp;//(2)
			System.out.println(Thread.currentThread().getName()+":balance="+balance);
		}
		System.out.println(Thread.currentThread().getName()+":票卖完了！");
	}
	
	public void run(){
		sellTicket();
	}
	
	public static void main(String[] args) {
		Runnable target = new OfficeRunnableRight12306(100);
		normalStart(target);//输出结果：线程t1,t2交替执行。共用总票源100，共享票源。将一次递减售完
	}
	
	/** 
	* @Title: normalStart 
	* @Description: 正常启动线程 
	* @param @param t1
	* @param @param t2    设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void normalStart(Runnable targ){
		Thread t1 = new Thread(targ,"t1");
		Thread t2 = new Thread(targ,"t2");
		t1.start();
		t2.start();
	}

}
