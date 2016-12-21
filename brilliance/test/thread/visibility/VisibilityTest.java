package thread.visibility;

/**
* @ClassName: VisibilityTest
* @Package thread.visibility
* @Description: 测试变量可见性（未总结）
* @author Russell Xun Jiang
* @date 2016年12月21日 下午8:48:40
*/
public class VisibilityTest extends Thread {
    private boolean stop;
    public void run() {
        int i = 0;
        while(!stop) {
            i++;
            System.out.println("in loop,i=" + i);
        }
        System.out.println("finish loop,i=" + i);
    }
    public void stopIt() {
        stop = true;
    }
    public boolean getStop(){
        return stop;
    }
    public static void main(String[] args) throws Exception {
        VisibilityTest v = new VisibilityTest();
        v.start();
 
        Thread.sleep(1000);
        v.stopIt();
        Thread.sleep(2000);
        System.out.println("finish main");
        System.out.println(v.getStop());
    }
 
}
