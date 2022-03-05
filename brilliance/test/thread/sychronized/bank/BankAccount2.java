package thread.sychronized.bank;

public class BankAccount2 {
	// suppose all the cash machines share a single bank account
	private Integer balance = 0;

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	
}
