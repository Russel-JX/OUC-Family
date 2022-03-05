package thread.sychronized.bank;

/**
 * 
 * 2022.03.03
 * 案例：模拟多个ATM，向同一账户存取款操作。
 * 每个ATM的一次操作，有村1元且取1元。
 * 多个ATM在多次存取后，正常应该账户余额为0.
 * 测试结果：不为0。因为1个ATM的一次存取操作可能被打断。
 * 即可能出现，ATM1在存之后，还没有取时，ATM2也存了。
 * 导致ATM2不知道ATM1已向账户存了1元，账户实际少了1元。
 *
 */
public class BankAccountTest {
	private static int atmNumber=1000;
	public static void main(String[] args) {
		/**
		 * 存钱取钱的具体方法，用static静态方法操作静态变量balance余额
		 */
//		operatorStatic();
		
		/**
		 * 存钱取钱的具体方法，从static静态，改成非静态，synchronized代码块就有效了
		 */
		operatorNonStatic();
	}
	
	public static void operatorStatic() {
		for(int i=0;i<=atmNumber;i++) {
//			ATM.cashMachine1();//结果会不正确。
//			ATM.cashMachine2();//结果会不正确。在取钱，存钱方法内部加lock，没用
//			ATM.cashMachine3();//结果正确。分别在取钱，存钱方法上加lock，可以。
//			ATM.cashMachine3_2();//结果会不正确。将3中改成非静态方法，没用
//			ATM.cashMachine4();//结果正确。在取钱，存钱方法合起来的外部方法上加lock，可以
//			
//			Object obj = new Object();
//			ATM.cashMachine5(obj);//TODO WHY 在取钱，存钱方法合起来的外部方法内部加lock，没用.obj lock没用
//	
			Object obj = new Object();
			ATM.cashMachine6(obj);//TODO WHY 在取钱，存钱各自方法内部加lock，没用.obj lock没用
		}
		System.out.println("balance="+BankAccount.getBalance());
	}
	
	public static void operatorNonStatic() {
		BankAccount2 account = new BankAccount2();
		for(int i=0;i<=atmNumber;i++) {
////		ATM2.cashMachine1();//不存在此场景，无需运行验证。因为要传对象锁进去。
////		ATM2.cashMachine2();//不存在此场景。因为要传对象锁进去。
//			ATM2.cashMachine3(account);//结果正确。分别在取钱，存钱方法上加lock，可以
//			ATM2.cashMachine3_2(account);//结果会不正确,，因为锁在新线程的方法上，而不是公用类的方法上，所以锁不住其他线程调用被锁的方法。
////		ATM2.cashMachine4();//不存在此场景。因为要传对象锁进去。
//			ATM2.cashMachine5(account);//结果正确。TODO WHY 在取钱，存钱方法合起来的外部方法内部加lock。
			//存钱取钱的具体方法，从static静态，改成非静态，synchronized代码块就有效了.TODO WHY?
//			ATM2.cashMachine6(account);//结果正确。
			
			Object obj7 = new Object();
			ATM2.cashMachine7(account,obj7);///结果会不正确.TODO WHY 用其他临界资源对象，做锁，不行
			
			//TODO static synchronized method. 锁在静态方法上，则得到类锁，将锁住整个类的同步静态方法。
			//因为一个类的静态方法，属于整个类的实例共有，属于类级别，非实例级别，在类加载阶段就初始化了。
			//一旦此类中的任何标注了synchronized静态方法，被一个线程调用执行，意味着此方法被当前线程使用，其他线程不能使用此类的此静态方法
			//，意味着当前线程拿到了这个类级别的锁，非类实例级别的锁，所以其他线程不能使用此类的任何标注了synchronized静态方法，因为类界别的锁被当前线程占用。
//			Object obj8 = new Object();
//			ATM2.cashMachine8(account);//结果。


		}
		System.out.println("balance="+account.getBalance());
	}

}
