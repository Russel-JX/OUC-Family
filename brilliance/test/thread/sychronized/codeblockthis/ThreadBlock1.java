package thread.sychronized.codeblockthis;

/**
* @ClassName: ThreadBlock
* @Package thread.sychronized.codeblockthis
* @Description: synchronized修饰代码块——this
* @author Russell Xun Jiang
* @date 2016年12月19日 上午9:58:37
*/
public class ThreadBlock1 implements Runnable{
	public int count = 0;
	/*  
	*大前提：多个线程同时启动，哪个先获得cpu调度先执行是不确定的。t1、t2都可能先执行。运行过程中，t1、t2由于cpu线程切换可能不停的交叉执行。
	*有synchronized同步修饰的代码，存在对象资源争夺了，一旦被执行了，那么这个线程将执行完毕才让别的线程执行，是顺序的。别的某个线程执行时也是一样，自己执行全部完毕了，再给下一个线程执行。
	* 情景一：没有同步代码块，即注释掉(1)
	* 	多个线程共享同一个对象main函数中的target，即共享了ThreadBlock对象，也共享了实例的count属性。
	* 	当多个线程同时进入(2)时，导致count值都是同一个，(3)处输出的结果也一样，就少了一次count自增操作。出现存在多个线程输出的结果有一样的情况。
	* （可通过debug断点，切换线程的执行）
	* 输出的结果是：t1、t2线程不停切换，count值是同步的，count值有的相同，有的不同，从0到39之间，当2个线程有一样输出时，到不了39。
	* 情景二：同步代码块
	* 	多个线程执行时，还是共享ThreadBlock实例对象（这里是main函数中的target对象），但同一时间在cpu执行时，只有获得实例的锁能够执行，其他需要此对象锁的线程进入锁池等待，
	* 等到当前线程执行完，放弃对象锁，由cpu调度才能执行。所以执行的顺序是一个线程全部跑完了，下个线程才能继续跑。
	* 	这里有t1、t2两个线程，当t1先被cpu执行时，t1获得了ThreadBlock实例对象的锁，他会一直占有cpu执行，t2无法穿插进来执行，
	* 因为t2没有对象锁，只有等到t1执行完了，释放对象锁了，才能执行。反过来，如果一开始t2先被cpu执行，情况是类似的：t2全部执行后，t1再执行。
	* 输出的结果是：t1、t2线程按顺序执行，count值是同步的，count值严格从0到39之间。
	*/ 
	public void run() {
		synchronized(this){//(1)
			for(int i=0;i<20;i++){//(2)
				System.out.println(Thread.currentThread().getName()+":"+count);//(3)
				count++;//(4)
			}
		}
	}
}
