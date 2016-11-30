package thread.sychronized.object;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ThreadB extends Thread{
	Log logger = LogFactory.getLog(ThreadB.class);
	
	private BankService bankService = new BankService();
	private Account account;
	
	//非静态方法同步锁
	public  void run(){
		logger.info(this.getName()+"run TB...");
		try {
			bankService.withdraw(account);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public ThreadB(Account account){
		super();
		this.account = account;
	}


}
