package thread.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.*;

@Slf4j
/**
 * 场景：线程池开启多个线程，每个线程都模拟大量的log打印过程。
 测试线程池在不同参数定义时，资源的分配和执行情况。
 */
public class ThreadPool {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        BlockingQueue queue = new ArrayBlockingQueue<Runnable>(2);
        RejectedExecutionHandlerImpl handler = new RejectedExecutionHandlerImpl();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,12,5, TimeUnit.SECONDS,
                queue,threadFactory,handler
        );
        for(int i=0;i<10;i++){
            executor.execute(new KeepRunning(100000));
        }

        try {
            executor.shutdown();//子线程都跑完后，销毁线程池

            Runnable r = () -> {
                log.info("|||||||||||Watching sub tasks running.");
                boolean stop = false;
                try {
                    while(true){
                        //TOOD
                        log.info("PoolSize {},CorePoolSize {},ActiveCount {},CompletedTaskCount {}," +
                                        "TaskCount {},isShutdown {},isTerminated {}, queue size{}, MaximumPoolSize{}," +
                                        "LargestPoolSize {}, KeepAliveTime {}",
                                new Object[]{executor.getPoolSize(),
                                executor.getCorePoolSize(),
                                executor.getActiveCount(),
                                executor.getCompletedTaskCount(),
                                executor.getTaskCount(),
                                executor.isShutdown(),
                                executor.isTerminated(),
                                executor.getQueue().size(),
                                executor.getMaximumPoolSize(),
                                executor.getLargestPoolSize(),
                                executor.getKeepAliveTime(TimeUnit.SECONDS)}
                                        );
                        Thread.sleep(3000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.info("$$$$ exception!");
                }
                log.info("*****after killing sub tasks");

            };
            new Thread(r).start();

            executor.awaitTermination(60,TimeUnit.MINUTES);//如果60min子线程没全部跑完，主线程将报中断异常
            long end = System.currentTimeMillis();
            log.info("所有子线程运行时长 {}s",(end-start)/1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("await interrupted! Throw exception to kill current thread.");
            executor.shutdownNow();//尝试停止所有子线程
            throw new Exception(e);
        }

    }
}
@Slf4j
class KeepRunning implements Runnable{
    private long num;
    public KeepRunning(long num) {
        this.num = num;
    }

    @Override
    public void run() {
        log.info("=======Start! Thread {}, id {}",Thread.currentThread().getName(),Thread.currentThread().getId());
        long start = System.currentTimeMillis();
        for(int i=0; i<num;i++){
            log.info("current i {} thread {} ",i,Thread.currentThread().getName());
        }
        long end = System.currentTimeMillis();
        log.info("========Thread {} finish! duration {}s",
                Thread.currentThread().getName(),
                (end-start)/1000);
        log.info("=======id {}",Thread.currentThread().getId());
    }
}


@Slf4j
class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
       log.info(r.toString() + " is rejected");
    }

}
