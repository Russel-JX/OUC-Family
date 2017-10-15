package cryptology;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

//密钥key工具类
public class KeyUtil {
	public static byte[] getKey(String algoName) throws NoSuchAlgorithmException{
		KeyGenerator keyGen = KeyGenerator.getInstance(algoName);
		SecretKey key = keyGen.generateKey();
		return key.getEncoded();//密钥一般存在字节数组中传递和使用
	} 
}
