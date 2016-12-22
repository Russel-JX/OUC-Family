package thread.reorder;

/**
* @ClassName: CommandReorderTest
* @Package thread.reorder
* @Description: 指令重排序（未完成）
* @author Russell Xun Jiang
* @date 2016年12月21日 下午8:43:04
* 启动两个线程。他们共享变量a和b.
* 	一个线程执行m1方法，一个执行m2方法。这两个方法中对彼此的共享参数都有影响。
* 如果m2中，输出a为
* 
*/
public class CommandReorderTest{

	private static int a = 0;
	private static boolean b = false;
	
	//m1中(1)和(2)可能被重排序。因为(1)和(2)没有依赖关系。
	public static void m1(){
		a = 1;//(1)
		b = true;//(2)
		System.out.println("m1。"+Thread.currentThread().getName()+":a="+a+",b="+b);//(3)
	}
	
	/**
	 *
	 * 
	 * 思考组合调整m1和m2中的条件，使(1)(2)重排序之后，能被验证出来？？？？？？？？？？？？？？？？？？？？？
	 * 
	 * 
	 * 
	 */
	
	//m2中代码是顺序的。因为 (5)依赖(4)
	public static void m2(){
		if(b){//(4)
			a = a+100;//(5)
			System.out.println("m2。"+Thread.currentThread().getName()+":a="+a+",b="+b);//(6)
		}else{
			System.out.println("m2。"+Thread.currentThread().getName()+":a="+a+",b="+b);//(7)
		}
		
	}
	
	//ThreadA和ThreadB共享变量a和b
	public static class ThreadA extends Thread{
		public void run() {
			m1();
		}
	}
	public static class ThreadB extends Thread{
		public void run() {
			m2();
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new ThreadA();
		Thread t2 = new ThreadB();
		t1.start();
		t2.start();

	}


}
