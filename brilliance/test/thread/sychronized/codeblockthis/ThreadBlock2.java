package thread.sychronized.codeblockthis;

/**
* @ClassName: ThreadBlock
* @Package thread.sychronized.codeblockthis
* @Description: synchronized修饰代码块——this
* @author Russell Xun Jiang
* @date 2016年12月19日 上午9:58:37
*/
public class ThreadBlock2 implements Runnable{
	public int count = 0;
	/*  
	*大前提：多个线程同时启动，哪个先获得cpu调度先执行是不确定的。t1、t2都可能先执行。运行过程中，t1、t2由于cpu线程切换可能不停的交叉执行。
	*有synchronized同步修饰的代码，存在对象资源争夺了，一旦被执行了，那么这个线程将执行完毕才让别的线程执行，是顺序的。别的某个线程执行时也是一样，自己执行全部完毕了，再给下一个线程执行。
	* 情景一：没有同步代码块，即注释掉(1)
	* 	多个线程的对象不同，不存在争夺问题。各自运行各自的对象。
	* 	输出的结果是：t3、t4线程不停切换，输出t3和t4的结果都是从0到19，只不过因为线程的穿插运行，输出的顺序可能不同。所以对于不同对象，不同享的情况，不影响最终结果，可以不要synchronized同步。
	* 情景二：同步代码块
	* 	多个线程的对象不同，不存在争夺问题。各自运行各自的对象。任何线程都不会因为拿不到锁而等待别的线程先执行释放所。
	* 	输出的结果是：和情景一一样。
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
