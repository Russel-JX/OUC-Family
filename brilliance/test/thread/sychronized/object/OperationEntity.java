package thread.sychronized.object;

/**
* @ClassName: OperationEntity
* @Package thread.sychronized
* @Description: 银行操作（查账、存钱、取钱、转账）实体
* @author Russell Xun Jiang
* @date 2016年11月30日 下午12:25:29
*/
public class OperationEntity {
	private Account account;//银行账户
	private double depositeAmount;//存钱金额
	private double withdrawAmount;//取钱金额
	
	public OperationEntity(Account account,double depositeAmount,double withdrawAmount){
		this.account = account;
		this.depositeAmount = depositeAmount;
		this.withdrawAmount = withdrawAmount;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public double getDepositeAmount() {
		return depositeAmount;
	}
	public void setDepositeAmount(double depositeAmount) {
		this.depositeAmount = depositeAmount;
	}
	public double getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
}
