package thread.sychronized.codeblockthis;

/**
* @ClassName: BlockTest
* @Package thread.sychronized.codeblockthis
* @Description: synchronized修饰代码块——this
* @author Russell Xun Jiang
* @date 2016年12月19日 上午11:31:08
* 
*ThreadBlock1中的count会被多个线程共享，每个线程操作count时，都会在其他线程操作后的基础上操作，不独立，导致count很大。
*/
public class BlockTest1 {
	public static void main(String[] args) {
		//测试多线程共享一个对象时。共享的对象要加锁同步
		ThreadBlock1 target = new ThreadBlock1();
		Thread t1 = new Thread(target,"t1");
		Thread t2 = new Thread(target,"t2");
		t1.start();
		t2.start();
	}
}
