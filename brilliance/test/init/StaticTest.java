package init;

public class StaticTest
{
    public static void main(String[] args)
    {
        staticFunction();
    }
    static StaticTest st = new StaticTest();
    static
    {
        System.out.println("1");
    }
    {
        System.out.println("2");
    }
    StaticTest()
    {
        System.out.println("3");
        System.out.println("a="+a+",b="+b+",c="+c);
    }
    public static void staticFunction(){
        System.out.println("4");
    }
    int a=110;
    static int b =112;
    static final int c =114;
}

//2  3  110 0 114 1  4
