package cryptology;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import javax.crypto.SecretKey;

public class Main {

	public static void main(String[] args) throws Exception {
		final String GREETING = "How are you doing";
		final String PATH_MYSQL = "/mysql-installer-web-community-5.6.22.0.msi";
		
		//(1) Test Base64
//		String encoded = Base64Util.encrypt(GREETING);
//		System.out.println(GREETING+"-->"+encoded);//How are you doing-->SG93IGFyZSB5b3UgZG9pbmc=
//		String text = Base64Util.decrypt(encoded);
//		System.out.println(encoded+"-->"+text);//SG93IGFyZSB5b3UgZG9pbmc=-->How are you doing
//		
//		//Send email
//		Base64Util.sendMail();
		
		//(2) Test Message Digest
		String algoName = "MD5"; 
		byte[] bytes = MessageDigestUtil.encrypt(GREETING.getBytes(),algoName);
		System.out.println(algoName+". "+GREETING+">>>"+HexUtil.Byte2HexString(bytes)+",output length>>>"+HexUtil.Byte2HexString(bytes).length());
		
		String algoName2 = "SHA-256"; 
		byte[] bytes2 = MessageDigestUtil.encrypt(GREETING.getBytes(),algoName2);
		System.out.println(algoName2+". "+GREETING+">>>"+HexUtil.Byte2HexString(bytes2)+",output length>>>"+HexUtil.Byte2HexString(bytes2).length());
		
		String algoName3 = "SHA-384"; 
		byte[] bytes3 = MessageDigestUtil.encrypt(GREETING.getBytes(),algoName3);
		System.out.println(algoName3+". "+GREETING+">>>"+HexUtil.Byte2HexString(bytes3)+",output length>>>"+HexUtil.Byte2HexString(bytes3).length());
		
		String algoName4 = "SHA-512"; 
		byte[] bytes4 = MessageDigestUtil.encrypt(GREETING.getBytes(),algoName4);
		System.out.println(algoName4+". "+GREETING+">>>"+HexUtil.Byte2HexString(bytes4)+",output length>>>"+HexUtil.Byte2HexString(bytes4).length());
		
//		//“消化”文件成摘要
//		File file = new File(PATH_MYSQL);
//		InputStream is = new FileInputStream(file);
//		//DigestInputStream读取字节流，到
//		DigestInputStream dis = new DigestInputStream(is,MessageDigest.getInstance("MD5"));
//		byte[] file_bytes = new byte[file.]; 
//		is.read(b);
//		byte[] bytes_file = MessageDigestUtil.encrypt(new InputStream(new File(PATH_MYSQL)).,algoName);
//		System.out.println(algoName+". "+GREETING+">>>"+HexUtil.Byte2HexString(bytes)+",output length>>>"+HexUtil.Byte2HexString(bytes).length());
		
		//Hmac用密钥加密摘要
		//TODO 如果生成密钥的算法和计算摘要的算法不一样会怎么样？好像没有报错。
		String algoName5 = "HmacMd5";//HmacMd5,HmacSHA256,HmacSHA-384,HmacSHA-512
		byte[] byteKey = KeyUtil.getKey(algoName5);//get key
		System.out.println("secretKey:"+HexUtil.Byte2HexString(byteKey));
		byte[] outcome = MessageDigestUtil.hmacEncrypt(byteKey, GREETING.getBytes(),algoName5);
		System.out.println(algoName5+". "+GREETING+">>>"+HexUtil.Byte2HexString(outcome)+",output length>>>"+HexUtil.Byte2HexString(outcome).length());
		
		//(4) Test Asymmetry Encryption
		

	}

}
