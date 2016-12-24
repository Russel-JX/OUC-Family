package corejava.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import corejava.entity.Worker;

/**
* @ClassName: SerializableTest
* @Package corejava.io
* @Description: java序列化
* @author Russell Xun Jiang
* @date 2016年12月24日 上午11:31:38
* 使用ObjectOutputStream和ObjectInputStream，写/读对象，将对象写/读到文件中——序列化/反序列化
* 对象必须实现Serializable接口，否则抛异常：java.io.NotSerializableException
* 
*/

public class SerializableTest {

	public static void main(String[] args) {
		//对象序列化
		serialize();
		
//		//对象反序列化
//		deSerialize();
		
	}
	
	/** 
	* @Title: serialize 
	* @Description: 对象序列化 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void serialize(){
		try {
			OutputStream os = new FileOutputStream("d:\\serial.txt");
			ObjectOutputStream oos = new ObjectOutputStream(os);
			Worker worker = new Worker("Scott",25,170,"punky");//创建对象
			oos.writeObject(worker);//将对象写入文件中——序列化。将对象字节码写到文件中。
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	* @Title: deSerialize 
	* @Description: 对象反序列化 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* static静态属性hometown，输出的是反序列化这一方设置的值（Lianshui）。说明而不是之前设置的（Big Huaian）。说明静态变量没有序列化和反序列化之说。
	*/ 
	public static void deSerialize(){
			InputStream is;
			try {
				Worker.hometown = "Lianshui";
				is = new FileInputStream("d:\\serial.txt");
				ObjectInputStream ois = new ObjectInputStream(is);
				Object obj = ois.readObject();//读取流中对象——反序列化
				Worker worker = (Worker)obj;//读取流中对象——反序列化
				
				
				System.out.println(worker.toString());//对象的trasient属性nickname无法反序列化，为空。因为没有序列化。
				
				//输出工人姓名是：Scottfff,年龄是：25,身高是：170.0,
				//学校是：Zhengliangmei series school,家乡是：Lianshui,绰号是：null
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
	}

}
