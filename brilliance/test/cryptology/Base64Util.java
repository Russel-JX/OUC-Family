package cryptology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	
	public final static String GREETING = "How are you doing";
	
	//Encryption by Base64
	public static String encrypt(String param){
		BASE64Encoder encoder = new BASE64Encoder();
		String encoded = encoder.encode(param.getBytes());
		return encoded;
	}
	
	//Decryption by Base64
	public static String decrypt(String encoded) throws IOException{
		BASE64Decoder decoder = new BASE64Decoder();
		return new String(decoder.decodeBuffer(encoded));
	}

	public static void main(String[] args){
		System.out.println(encrypt("abc"));
	}
	

	public static final String USERNAME = "anolym@126.com";
	public static final String PASSWORD = "anolyjx";
	public static final String RECEIVER = "jxoucjj@126.com";
	
	/**
	 * @throws IOException 
	 * @throws UnknownHostException  
	* @Title: sendMail 
	* @Description: 用SMTP协议发送邮件
	* 过程：用socket建立到邮件服务器的连接。
	*     向服务器发送请求和取的响应，并发送邮件
	*     关闭连接
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void sendMail() throws UnknownHostException, IOException{
		//Connect to port 25, 126 mail domain
		Socket socket = new Socket("smtp.126.com",25);
		//Create ips and ops through socket channel to request and get response
		InputStream ips = socket.getInputStream();
		OutputStream ops = socket.getOutputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(ips));
		PrintWriter pw = new PrintWriter(ops,true);
		System.out.println("Connected to site and get response:"+br.readLine());
		
		//Request and get response
		pw.println("HELO jikexueyuan");
		System.out.println(br.readLine());
		
		//登录
		pw.println("AUTH LOGIN");//请求登录
		System.out.println(br.readLine());//返回用户名输入提示
		pw.println(encrypt(USERNAME.substring(0,USERNAME.indexOf("@"))));//输入发件人用户名（默认用base64加密）
		System.out.println(br.readLine());//返回密码输入提示:334 dXNlcm5hbWU6. 就是发送者用户名的base64编码值。
		pw.println(encrypt(PASSWORD));//输入发件人密码（默认用base64加密）
		System.out.println(br.readLine());//登录结果  334 UGFzc3dvcmQ6
		
		//编辑、发送邮件
		pw.println("MAIL FROM:<"+USERNAME+">");
		System.out.println(br.readLine());
		pw.println("RCPT TO:<"+RECEIVER+">");
		System.out.println(br.readLine());
		
		pw.println("DATA");
		System.out.println(br.readLine());
		pw.println("SUBJECT:"+GREETING+" .Today is "+new Date());
		pw.println("FROM:"+USERNAME);
		pw.println("TO:"+RECEIVER);
		pw.println("Content-Type: text/plain;charset=\"gb2312\"");
		//正文
		pw.println("");
		pw.println("Hi Russell,");
		pw.println("\n Long time no see! Hope you hang out there well.");
		pw.println("Thanks & Regards,");
		pw.println("Sincere Love 'Myself'");
		pw.println(".");//用“.”加换行结束正文
		pw.println("");
		System.out.println(br.readLine());
		
		//关闭连接
		pw.println("RSET");
		System.out.println(br.readLine());
		pw.println("QUIT");
		System.out.println(br.readLine());
		
		socket.close();
	}
}
