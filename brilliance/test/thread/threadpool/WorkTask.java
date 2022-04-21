package thread.threadpool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkTask implements Runnable{
    @Override
    public void run() {
        log.info("running id {} name {}",Thread.currentThread().getId(),Thread.currentThread().getName());

    }
}
