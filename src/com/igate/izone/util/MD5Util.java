package com.igate.izone.util;
/**
 * 密码加密工具类
 * @author Yicheng Zhou
 *
 */
public class MD5Util {
	/** MD5加密方法
	 * @param source 带加密的字符串
	 * @return 加密后的字符串
	 */
	public static String md5(String source) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		char str[] = new char[16*2];
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source.getBytes());
			byte tmp[] = md.digest();
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(str);
	}
}
