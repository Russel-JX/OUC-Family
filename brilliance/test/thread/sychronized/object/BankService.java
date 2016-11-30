package thread.sychronized.object;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jihe.map.MapTest;

/**
* @ClassName: BankService
* @Package thread.sychronized
* @Description: 银行系统模拟
* @author Russell Xun Jiang
* @date 2016年11月30日 上午11:38:19
*/
public class BankService {
	private static Log logger = LogFactory.getLog(BankService.class);
	/**
	 * @throws InterruptedException  
	* @Title: withdraw 
	* @Description: 取钱方法
	* @param @param OperationEntity	操作实体
	* @param @param operateFlag	操作权限
	* 							1：高级权限
	* 							0：普通权限
	* @param @return    设定文件 
	* @return double    返回类型 	账户余额
	* @throws 
	*/ 
	//synchronized
	public  double withdraw(Account account) throws InterruptedException{
//		if("1".equals(operateFlag)){
////			Thread.sleep(2000);
//		}else{
//			
//		}
//		synchronized(account){
//			double balance = account.getBalance()-100; 
//			account.setBalance(balance);
//			logger.info("账户："+account.getAccountId()+"，取钱："+100+"。取后余额是："+account.getBalance());
//			return balance;
//		}
		double balance = account.getBalance()-100; 
		account.setBalance(balance);
		logger.info("账户："+account.getAccountId()+"，取钱："+100+"。取后余额是："+account.getBalance());
		return balance;
		
	}

}
