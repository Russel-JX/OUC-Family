package thread.unsynchronized;

/**
* @ClassName: OfficeRunnable12306
* @Package thread.unsynchronized
* @Description: 
* @author Russell Xun Jiang
* @date 2016年12月21日 下午10:01:50
* 如果一个类继承Thread，则不适合资源共享。如果实现了Runable接口的话，则很容易的实现资源共享。
*/
public class OfficeRunnableWrong12306 implements Runnable{
	//余票总数
	private int balance; 
	
	public OfficeRunnableWrong12306(int bala){
		this.balance = bala;
	}
	
	
	/** 
	* @Title: sellTicket 
	* @Description: 售票方法
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 此方法没有将共享资源——余票数量，保护。可能出现问题。
	* 如：
	* 	t1和t2都执行到(1)，t1执行完(2)后没有往下执行，t2也执行了(2)，则票被卖出去了，但余票少减了一次。
	* 	断点自己看。
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
		Runnable target = new OfficeRunnableWrong12306(100);
		normalStart(target);//输出结果：线程t1,t2交替执行。共用总票源100，共享票源。可能出现余票数量不正确的情况。
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
