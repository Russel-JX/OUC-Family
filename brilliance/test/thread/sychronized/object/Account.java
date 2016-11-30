package thread.sychronized.object;

/**
* @ClassName: Account
* @Package thread.sychronized
* @Description: 银行账户
* @author Russell Xun Jiang
* @date 2016年11月30日 上午11:42:42
*/
public class Account {
	private String accountID;//账号
	private double balance;//账户余额
	
	public Account(String accountId,double balance){
		this.accountID = accountId;
		this.balance = balance;
	}
	
	public String getAccountId() {
		return accountID;
	}
	public void setAccountId(String accountId) {
		this.accountID = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
