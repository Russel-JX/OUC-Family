package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CustomLocalClassLoader extends ClassLoader{
    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        //传入类名，开始，结束，调用ClassLoader类的defineClass方法，返回类对象Class
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName)  {
        /**
         * ClassLoader.getResourceAsStream()和ClassLoader.getResource()返回的都是项目的classpath路径
         * 如当前项目的classpath配置为(即最终.class文件位置)：/Users/jiangxun/IdeaProjects/OUC-Family2/out/test/brilliance
         * 实际的当前类所在物理位置(IDE中位置)是：/Users/jiangxun/IdeaProjects/OUC-Family2/brilliance/test/classloader/CustomClassLoader.class
         * Animal类所在物理位置(IDE中位置)是：/Users/jiangxun/IdeaProjects/OUC-Family2/brilliance/test/duotai/lei/Animal.class
         * 所以最终按照classpath位置来，即传入classloader/CustomClassLoader.java或duotai.lei.Animal等来访问。
         * duotai.lei.Animal将被解析成classpath下的：/Users/jiangxun/IdeaProjects/OUC-Family2/out/test/brilliance/duotai/lei/Animal.class
         */

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}
