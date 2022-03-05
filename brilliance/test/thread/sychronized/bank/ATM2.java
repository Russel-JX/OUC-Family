package thread.sychronized.bank;

public class ATM2 {
	public static BankAccount account; 
	
	public static int TRANSACTIONS_PER_MACHINE=5;
	
//	private static void deposit(BankAccount2 obj) {
//		obj.setBalance(obj.getBalance()+1);
//	}
//	private static void withdraw(BankAccount2 obj) {
//		obj.setBalance(obj.getBalance()-1);
//	}
	
//	private static void deposit2(BankAccount2 obj) {
//		synchronized (BankAccount.getBalance()) {
//			obj.setBalance(obj.getBalance()+1);
//		}
//	}
//	private static void withdraw2(BankAccount2 obj) {
//		synchronized (BankAccount.getBalance()) {
//			obj.setBalance(obj.getBalance()-1);
//		}
//	}
	
	/**
	 * 
	 * @param obj
	 */
	private static synchronized void deposit3(BankAccount2 obj) {
		obj.setBalance(obj.getBalance()+1);
	}
	private static synchronized void withdraw3(BankAccount2 obj) {
		obj.setBalance(obj.getBalance()-1);
	}
	
//	private static void deposit4(BankAccount2 obj) {
//			obj.setBalance(obj.getBalance()+1);
//	}
//	private static void withdraw4(BankAccount2 obj) {
//			BankAccount.setBalance(BankAccount.getBalance()-1);
//	}
	
	private static void deposit5(BankAccount2 obj) {
		obj.setBalance(obj.getBalance()+1);
	}
	private static void withdraw5(BankAccount2 obj) {
		obj.setBalance(obj.getBalance()-1);
	}
	
	private static void deposit6(BankAccount2 obj) {
		synchronized(obj){
			obj.setBalance(obj.getBalance()+1);
		}
	}
	private static void withdraw6(BankAccount2 obj) {
		synchronized(obj){
			obj.setBalance(obj.getBalance()-1);
		}
	}
	
	private static void deposit7(BankAccount2 account, Object obj) {
		synchronized(obj){
			account.setBalance(account.getBalance()+1);
		}
	}
	private static void withdraw7(BankAccount2 account, Object obj) {
		synchronized(obj){
			account.setBalance(account.getBalance()-1);
		}
	}
	
	private synchronized static void deposit8(BankAccount2 account) {
		account.setBalance(account.getBalance()+1);
	}
	private synchronized static void withdraw8(BankAccount2 account) {
		account.setBalance(account.getBalance()-1);
	}
	
	// each ATM does a bunch of transactions that
	// modify balance, but leave it unchanged afterward
//	public static void cashMachine1(BankAccount2 obj) {
//	    new Thread(new Runnable() {
//	        public void run() { 
//	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
//	            	ATM2.deposit(obj); // put a dollar in
//	            	ATM2.withdraw(obj); // take it back out
//	            }
//	        }
//	    }).start();
//	}
	
//	public static void cashMachine2(BankAccount2 obj) {
//	    new Thread(new Runnable() {
//	        public void run() { 
//	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
//	            	ATM2.deposit2(); // put a dollar in
//	            	ATM2.withdraw2(); // take it back out
//	            }
//	        }
//	    }).start();
//	}
	
	public static void cashMachine3(BankAccount2 obj) {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	ATM2.deposit3(obj); // put a dollar in
	            	ATM2.withdraw3(obj); // take it back out
	            }
	        }
	    }).start();
	}
	
	public static void cashMachine3_2(BankAccount2 obj) {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	deposit3_2(obj); // put a dollar in
	            	withdraw3_2(obj); // take it back out
	            }
	        }
	        private synchronized void deposit3_2(BankAccount2 obj) {
	        	obj.setBalance(obj.getBalance()+1);
			}
			private synchronized void withdraw3_2(BankAccount2 obj) {
				obj.setBalance(obj.getBalance()-1);
			}
	    }).start();
	}
	
//	public static void cashMachine4(BankAccount2 obj) {
//	    new Thread(new Runnable() {
//	        public void run() { 
//	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
//	            	atmWork4(obj);
//	            }
//	        }
//	    }).start();
//	}
//	
//	//让存钱取钱操作外部原子化，结果正确。
//	public static synchronized void atmWork4(BankAccount2 obj) {
//		ATM2.deposit4(obj); 
//    	ATM2.withdraw4(obj); 
//	}
	
	public static void cashMachine5(BankAccount2 obj) {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	atmWork5(obj);
	            }
	        }
	    }).start();
	}
	
	//TODO 让存钱取钱操作原子化，并没有让结果正确。
	public static void atmWork5(BankAccount2 obj) {
		//TODO 4和5的区别在lock锁加在外部方法上和外部方法内，为什么前者正确，或者不正确？？
		synchronized (obj) {
			ATM2.deposit5(obj); 
	    	ATM2.withdraw5(obj); 
		}
	}
	
	public static void cashMachine6(BankAccount2 obj) {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	ATM2.deposit6(obj); // put a dollar in
	            	ATM2.withdraw6(obj); // take it back out
	            }
	        }
	    }).start();
	}
	
	public static void cashMachine7(BankAccount2 account, Object obj) {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	ATM2.deposit7(account,obj); // put a dollar in
	            	ATM2.withdraw7(account,obj); // take it back out
	            }
	        }
	    }).start();
	}
	
	public static void cashMachine8(BankAccount2 obj) {
	    new Thread(new Runnable() {
	        public void run() { 
	            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
	            	ATM2.deposit8(obj); // put a dollar in
	            	ATM2.withdraw8(obj); // take it back out
	            }
	        }
	    }).start();
	}
	
	
}
