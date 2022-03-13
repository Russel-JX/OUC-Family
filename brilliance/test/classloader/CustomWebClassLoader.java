package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CustomWebClassLoader extends ClassLoader{
    /**
     * 模拟加载网络上(自己电脑中)/Users/jiangxun/Downloads/apache-tomcat-8.0.47/webapps/music/Animal.class
     * 把Animal.class文件放入本地tomcat下的music项目，并启动tomcat.
     */
    String webUrl = "http://localhost:8080/music";
    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromWeb(webUrl, name);
        //传入类名，开始，结束，调用ClassLoader类的defineClass方法，返回类对象Class
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromWeb(String webUrl, String fileName)  {
        InputStream is=null;
        try
        {
            String path=webUrl+"/"+fileName.replace(".", "/")+".class";
            URL url=new URL(path);
            byte[]buff=new byte[1024];
            int len=-1;
            is=url.openStream();
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            while((len=is.read(buff))!=-1)
            {
                baos.write(buff,0,len);
            }
            System.out.println("now we have downloaded the class file");
            return baos.toByteArray();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(is!=null)
            {
                try
                {
                    is.close();
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }

        return null;
    }
}
