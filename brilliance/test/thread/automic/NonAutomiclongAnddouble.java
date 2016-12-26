package thread.automic;

/**
* @ClassName: NonAutomiclongAnddouble
* @Package thread.automic
* @Description: 64位的long和double在32位JVM中的读和写操作都是非原子操作
* @author Russell Xun Jiang
* @date 2016年12月26日 下午2:38:48
* 
* http://www.cnblogs.com/louiswong/p/5951895.html
*/
public class NonAutomiclongAnddouble implements Runnable{
	private static long field = 0;

    private volatile long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public NonAutomiclongAnddouble(long value) {
        this.setValue(value);
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 100000) {
            NonAutomiclongAnddouble.field = this.getValue();
            i++;
            long temp = NonAutomiclongAnddouble.field;
            if (temp != 1L && temp != -1L) {
                System.out.println("出现错误结果" + temp);
                System.exit(0);
            }
        }
        System.out.println("运行正确");
    }
	/** 
	* @Title: main 
	* @Description:  
	* @param @param args
	* @param @throws InterruptedException    设定文件 
	* @return void    返回类型 
	* @throws 
	* 在32位JVM中，输出：“出现错误结果-4294967295”
	* 在64位JVM中，输出：“运行正确”
	*/ 
	public static void main(String[] args) throws InterruptedException {
		// 获取并打印当前JVM是32位还是64位的。请分别使用32位JDK和64位JDK的JVM测试，
        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(arch+"-bit");
        NonAutomiclongAnddouble t1 = new NonAutomiclongAnddouble(1);
        NonAutomiclongAnddouble t2 = new NonAutomiclongAnddouble(-1);
        Thread T1 = new Thread(t1);
        Thread T2 = new Thread(t2);
        T1.start();
        T2.start();
        T1.join();
        T2.join();

	}

}
