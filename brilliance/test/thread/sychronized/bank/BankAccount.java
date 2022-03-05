package thread.sychronized.bank;

public class BankAccount {
	// suppose all the cash machines share a single bank account
	private static Integer balance = 0;

	public static Integer getBalance() {
		return balance;
	}

	public static void setBalance(Integer balance) {
		BankAccount.balance = balance;
	}

	
}
