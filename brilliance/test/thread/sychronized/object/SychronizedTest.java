package thread.sychronized.object;

/**
* @ClassName: SychronizedTest
* @Package thread
* @Description: 线程同步——sychronized
* @author Russell Xun Jiang
* @date 2016年11月30日 上午10:47:46
* 
*/
public class SychronizedTest {

	public static void main(String[] args) {
		Account account = new Account("001",500);
//		OperationEntity oe1 = new OperationEntity(account,0,100);
		//非静态方法同步锁
		Thread t1 = new ThreadA(account);//通过构造函数给线程属性传参
		Thread t2 = new ThreadB(account);
		t1.start();
		t2.start();

	}
	
	
	

}
