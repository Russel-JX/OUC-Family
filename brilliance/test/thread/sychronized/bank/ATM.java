package thread.sychronized.bank;

public class ATM {
	public static BankAccount account; 
	
	public static int TRANSACTIONS_PER_MACHINE=5;
	
	private static void deposit() {
		BankAccount.setBalance(BankAccount.getBalance()+1);
	}
	private static void withdraw() {
		BankAccount.setBalance(BankAccount.getBalance()-1);
	}
	
	private static void deposit2() {
		synchronized (BankAccount.getBalance()) {
			BankAccount.setBalance(BankAccount.getBalance()+1);
		}
	}
	private static void withdraw2() {
		synchronized (BankAccount.getBalance()) {
			BankAccount.setBalance(BankAccount.getBalance()-1);
		}
	}
	
	private static synchronized void deposit3() {
			BankAccount.setBalance(BankAccount.getBalance()+1);
	}
	private static synchronized void withdraw3() {
			BankAccount.setBalance(BankAccount.getBalance()-1);
	}
	
//	private synchronized void deposit3_2() {
//		BankAccount.setBalance(BankAccount.getBalance()+1);
//	}
//	private synchronized void withdraw3_2() {
//		BankAccount.setBalance(BankAccount.getBalance()-1);
//	}
	
	private static void deposit4() {
			BankAccount.setBalance(BankAccount.getBalance()+1);
	}
	private static void withdraw4() {
			BankAccount.setBalance(BankAccount.getBalance()-1);
	}
	
	private static void deposit5() {
		BankAccount.setBalance(BankAccount.getBalance()+1);
	}
	private static void withdraw5() {
		BankAccount.setBalance(BankAccount.getBalance()-1);
	}
	
	private static void deposit6(Object obj) {
		synchronized(obj){
			BankAccount.setBalance(BankAccount.getBalance()+1);
		}
	}
	private static void withdraw6(Object obj) {
		synchronized(obj){
			BankAccount.setBalance(BankAccount.getBalance()-1);
		}
	}
	
	// each ATM does a bunch of transactions that
	// modify balance, but leave it unchanged afterward
	public static void cashMachine1() {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	ATM.deposit(); // put a dollar in
	            	ATM.withdraw(); // take it back out
	            }
	        }
	    }).start();
	}
	
	public static void cashMachine2() {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	ATM.deposit2(); // put a dollar in
	            	ATM.withdraw2(); // take it back out
	            }
	        }
	    }).start();
	}
	
	public static void cashMachine3() {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	ATM.deposit3(); // put a dollar in
	            	ATM.withdraw3(); // take it back out
	            }
	        }
	    }).start();
	}
	
	public static void cashMachine3_2() {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	deposit3_2(); // put a dollar in
	            	withdraw3_2(); // take it back out
	            }
	        }

			private synchronized void deposit3_2() {
				BankAccount.setBalance(BankAccount.getBalance()+1);
			}
			private synchronized void withdraw3_2() {
				BankAccount.setBalance(BankAccount.getBalance()-1);
			}
	    }).start();
	}
	
	public static void cashMachine4() {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	atmWork4();
	            }
	        }
	    }).start();
	}
	
	//让存钱取钱操作外部原子化，结果正确。
	public static synchronized void atmWork4() {
		ATM.deposit4(); 
    	ATM.withdraw4(); 
	}
	
	public static void cashMachine5(Object obj) {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	atmWork5(obj);
	            }
	        }
	    }).start();
	}
	
	//TODO 让存钱取钱操作原子化，并没有让结果正确。
	public static void atmWork5(Object obj) {
		//TODO 4和5的区别在lock锁加在外部方法上和外部方法内，为什么前者正确，或者不正确？？
		synchronized (obj) {
			ATM.deposit5(); 
	    	ATM.withdraw5(); 
		}
	}
	
	public static void cashMachine6(Object obj) {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	ATM.deposit6(obj); // put a dollar in
	            	ATM.withdraw6(obj); // take it back out
	            }
	        }
	    }).start();
	}
}
