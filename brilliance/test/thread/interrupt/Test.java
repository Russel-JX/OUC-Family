package thread.interrupt;

public class Test {
    public static void main(String[] args){
        Runnable r = ()->{
            System.out.println("1111"+Thread.currentThread().getName());
            System.out.println("2222");
            Thread.currentThread().interrupt();
            //虽然之前已经调用interrupt()方法，但还是会正常执行下去，
            //因为interrupt()方法只是给线程状态设置成Interrupted。
            System.out.println("3333 Thread.currentThread().isInterrupted()"+Thread.currentThread().isInterrupted());
            System.out.println("4444 Thread.interrupted()"+Thread.interrupted());

        };
        System.out.println("0000"+Thread.currentThread().getName());
        new Thread(r).start();
        System.out.println("9999");
        /**
         * outout:
         * 0000main
         1111Thread-0
         2222
         3333 Thread.currentThread().isInterrupted()true
         9999
         4444 Thread.interrupted()true
         */


    }
}

