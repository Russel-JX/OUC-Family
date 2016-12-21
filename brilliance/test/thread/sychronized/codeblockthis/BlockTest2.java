package thread.sychronized.codeblockthis;

/**
* @ClassName: BlockTest
* @Package thread.sychronized.codeblockthis
* @Description: synchronized修饰代码块——this
* @author Russell Xun Jiang
* @date 2016年12月19日 上午11:31:08
* 各个线程独立，没有争对象夺锁，对象中的属性也不会其他线程修改。
*/
public class BlockTest2 {
	public static void main(String[] args) {
		//测试多线程处理独立的对象时。对象没有共享各自独立，不必加锁同步
		ThreadBlock2 target2 = new ThreadBlock2();
		ThreadBlock2 target3 = new ThreadBlock2();
		Thread t3 = new Thread(target2,"t3");
		Thread t4 = new Thread(target3,"t4");
		t3.start();
		t4.start();
	}
}
