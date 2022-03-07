package native_test;

public class DateTimeUtils {
    //java中自定义了getSystemTime方法，并标记为native。表示此方法由具体某一种语言实现。
    public native String getSystemTime();

    static {
        //引入C++的nativedatetimeutils库。此库中有getSystemTime方法的具体实现
        System.loadLibrary("nativedatetimeutils");
    }

    public static void main(String[] args){
        DateTimeUtils dt = new DateTimeUtils();
        System.out.println(dt.getSystemTime());//output:Wed Dec 19 11:34:02 2018
    }
}
