package thread.daemon;

public class DaemonTest {

	public static void main(String args[]) {
        Thread userThread = new UserThread();
        Thread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        userThread.start();
        daemonThread.start();
    }
	/**
	 * output:可见，守护线程在用户线程结束后，也结束了。(因为这里只有1个用户线程，所以守护线程等这个结束他就结束)
	 * User Thread starting
Daemon Thread processing
Daemon Thread processing
Daemon Thread processing
Daemon Thread processing
Daemon Thread processing
Daemon Thread processing
Daemon Thread processing
Daemon Thread processing
Daemon Thread processing
Daemon Thread processing
User Thread finishing

	 */
	

}

class UserThread extends Thread {
    public void run() {
        System.out.println("User Thread starting");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("User Thread finishing");
    }
}
class DaemonThread extends Thread {
    public void run() {
        while(true) {
            System.out.println("Daemon Thread processing");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
