package classloader;



import org.apache.cxf.annotations.Logging;


import java.net.URL;
import java.util.ArrayList;

public class ClassLoaderSample {

    public static void main(String[] argrs){
        URL path = Thread.currentThread().getContextClassLoader().getResource("classloader/ClassLoaderSample.class");
        System.out.println("path:"+path.getPath());

        //load local class by customer ClassLoader
        CustomLocalClassLoader cl = new CustomLocalClassLoader();
        //注：加载类时一定指定从package到class名的路径，作为类名的全称，否则NoClassDefFoundError失败。
        //因为.class文件中已经指定了路径(package duotai.lei)
        final String classname = "duotai.lei.Animal";
        try {
            Class myClass = cl.loadClass(classname);
            Object obj = myClass.newInstance();
            myClass.getMethod("eat").invoke(obj);
        } catch (Exception e) {//loadClass方法，必须用检查异常，因为可能按名称加载类失败。
            e.printStackTrace();
        }

        //load web class by customer ClassLoader
        CustomWebClassLoader cl2 = new CustomWebClassLoader();
        //注：加载类时一定指定从package到class名的路径，作为类名的全称，否则NoClassDefFoundError失败。
        //因为.class文件中已经指定了路径(package duotai.lei)
        final String classname2 = "duotai.lei.Animal";
        try {
            Class myClass2 = cl2.loadClass(classname2);
            Object obj2 = myClass2.newInstance();
            myClass2.getMethod("laugh").invoke(obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get class loader hierarchy of a class.
        getClassLoaderHierarchy();

        //show what jars that BootStrap classloader will load
        showBootStrapClassLoader();




    }

    public static void getClassLoaderHierarchy(){

        System.out.println("class loader to current thread class is: "+ Thread.currentThread().getContextClassLoader());
        System.out.println("class loader to load String is: "+String.class.getClassLoader());
        System.out.println("class loader to load ArrayList is: "+ArrayList.class.getClassLoader());
        System.out.println("class loader to load Logging is: "+com.sun.javafx.util.Logging.class.getClassLoader());
        System.out.println("class loader to load Logging is: "+org.apache.cxf.annotations.Logging.class.getClassLoader());

        ClassLoader classLoader = ClassLoaderSample.class.getClassLoader();
        while(classLoader != null){
            System.out.println("current class loader is: "+classLoader);
            classLoader = classLoader.getParent();
        }
        System.out.println("current class loader is: "+classLoader);
        /**output:
         * //当前线程类由AppClassLoader加载器加载
         * class loader to current thread class is: sun.misc.Launcher$AppClassLoader@18b4aac2
         * //String和ArrayList类的加载器都是Bootstrap classloader
         * class loader to load String is: null
         * class loader to load ArrayList is: null
         *
         * //因为使用的是jdk中的com.sun.javafx.util.Logging，所以由ExtClassLoader加载器加载
         * class loader to load Logging is: sun.misc.Launcher$ExtClassLoader@1d81eb93
         * //因为Logging类在项目的WEB-INF/lib/cxf-2.6.1.jar中，属于项目的一部分，所以由AppClassLoader加载器去加载Logging类
         * class loader to load Logging is: sun.misc.Launcher$AppClassLoader@18b4aac2
         * //AppClassLoader加载器真正加载ClassLoaderSample类。
         * current class loader is: sun.misc.Launcher$AppClassLoader@18b4aac2
         * //父加载器是ExtClassLoader
         * current class loader is: sun.misc.Launcher$ExtClassLoader@b4c966a
         * //再父加载器是null。null其实表示真正是Bootstrap加载器，因为Bootstrap加载器是C++写的，是最顶层的加载器。
         * current class loader is: null
         */
    }

    public static void showBootStrapClassLoader(){
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(int i=0;i<urls.length;++i){
            System.out.println(urls[i].toExternalForm());
        }

        //
        System.out.println("same way to get bootstrap classloader"+System.getProperty("sun.boot.class.path"));

        /**output:
         file:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/resources.jar
         file:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/rt.jar
         file:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/sunrsasign.jar
         file:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/jsse.jar
         file:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/jce.jar
         file:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/charsets.jar
         file:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/jfr.jar
         file:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/classes

         same way to get bootstrap classloader
         /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/sunrsasign.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/classes

         Process finished with exit code 0
         */
    }


}
