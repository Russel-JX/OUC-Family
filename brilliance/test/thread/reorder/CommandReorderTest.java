package thread.reorder;

/**
* @ClassName: CommandReorderTest
* @Package thread.reorder
* @Description: 指令重排序
* @author Russell Xun Jiang
* @date 2016年12月21日 下午8:43:04
* 
*/
public class CommandReorderTest implements Runnable{

	private int a = 0;
	private boolean b = false;
	
	public void m1(){
		a = 1;
		b = true;
		System.out.println("m1。"+Thread.currentThread().getName()+":a="+a+",b="+b);
	}
	
	public class ThreadA implements Runnable{
		private CommandReorderTest t;
		public void run() {
			t.m1();
		}
	}
	public class ThreadB implements Runnable{
		private CommandReorderTest t;
		public void run() {
			t.m2();
		}
	}
	
	public void m2(){
		if(b){
			a = 1+100;
			System.out.println("m2。"+Thread.currentThread().getName()+":a="+a+",b="+b);
		}
		System.out.println("m2。"+Thread.currentThread().getName()+":a="+a+",b="+b);
	}
	
	
	
	@Override
	public void run() {
		m1();
		 
	}
	
	
	public static void main(String[] args) {
		CommandReorderTest target = new CommandReorderTest();
		Thread t1 = new Thread(target,"t1");
		Thread t2 = new Thread(target,"t1");
		t1.start();
		t2.start();

	}



	

}
