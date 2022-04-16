package thread.interrupt;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;

/**
 * 实在没办法了，用Thread.stop()方法停止线程,确实可以成功停止线程运行。
 * 输出：
 task1，task2没有运行完，被r线程3秒后杀死
 。。。
 [INFO ] 2022-04-16 18:25:10,078 method:thread.interrupt.KeepRunning.run(TestStop.java:46)
 current i 67048thread  Thread-1
 [INFO ] 2022-04-16 18:25:10,078 method:thread.interrupt.KeepRunning.run(TestStop.java:46)
 current i 67049thread  Thread-1
 [INFO ] 2022-04-16 18:25:10,078 method:thread.interrupt.TestStop.lambda$main$0(TestStop.java:26)
 *****after killing sub tasks
 */
public class TestStop {
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(KeepRunning.class);

    public static void main(String[] args){
        Thread t1 = new Thread(new KeepRunning(300000000L));
        Thread t2 = new Thread(new KeepRunning(200000000));
        t1.start();
        t2.start();

        Runnable r = () -> {
            log.info("|||||||||||Watching sub tasks running.");
            boolean stop = false;
            try {
                Thread.sleep(3000);
                t1.stop();
                t2.stop();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info("$$$$ exception!");
            }
            log.info("*****after killing sub tasks");

        };
        new Thread(r).start();
    }
}

class KeepRunning implements Runnable{
    org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(KeepRunning.class);

    private long num;

    public KeepRunning(long num) {
        this.num = num;
    }

    @Override
    public void run() {
        log.info("=======Start! Thread "+Thread.currentThread().getName());
        for(int i=0; i<num;i++){
            log.info("current i "+i+"thread  "+Thread.currentThread().getName());
        }

        log.info("========finish! Thread  "+Thread.currentThread().getName());
    }
}
