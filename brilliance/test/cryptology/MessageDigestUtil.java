package cryptology;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

//MD是单向的。只能加密，不能解密！
public class MessageDigestUtil {

	//Encrypt with MD5 algorithm
	public static byte[] encrypt(String param,String algoName) throws NoSuchAlgorithmException{
		MessageDigest md5 = MessageDigest.getInstance(algoName);//MD5,SHA-256,SHA-384,SHA-512. Name - case insensitive
		md5.update(param.getBytes());
		byte[] bytes = md5.digest();
		return bytes;
	} 
	
	//加密密钥。
	public static byte[] hmacEncrypt(byte[] key,byte[] data,String algoName) throws NoSuchAlgorithmException, InvalidKeyException{
		SecretKey secretKey = new SecretKeySpec(key,algoName);//这个密钥key是通信双方私密保存的。
		Mac mac = Mac.getInstance(algoName);//创新MAC算法对象用于加密
		mac.init(secretKey);//使用密钥初始化加密算法。即后续使用mac加密算法，并结合密钥来加密原信息。而HmacMD5对原信息计算哈希值/摘要值，底层还是调用MD5消息摘要算法，只不过底层调用封装在HmacMd5算法中。
		//用加密算法对象加密原信息;**在HmacCore.java中 dofinal方法调用了 byte[] tmp = md.digest();使用了消息只要算法计算摘要。**
		return mac.doFinal(data);
	}
}
