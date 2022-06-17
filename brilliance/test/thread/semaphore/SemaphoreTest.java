package thread.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(5);
        for(int i=1; i<=10; i++){
            new Parkinglot(semaphore, i).start();
        }
    }
}
