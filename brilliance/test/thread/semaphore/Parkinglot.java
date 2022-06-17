package thread.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
public class Parkinglot extends Thread{

    //车位资源
    private Semaphore semaphore;
    //车子序号
    private int carIndex;

    public Parkinglot(Semaphore semaphore, int carIndex){
        this.carIndex = carIndex;
        this.semaphore = semaphore;
    }

    public void run(){
        try {
            semaphore.acquire();
            log.info("第{}量车进入停车场", this.carIndex);
            Thread.sleep(1000);
            semaphore.release();
            log.info("第{}量车离开停车场", this.carIndex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
