package cryptology;

/**
* @ClassName: HexUtil
* @Package cryptology
* @Description: Convert byte array to hex string 
* @author Russell Xun Jiang
* @date 2017年10月15日 下午8:13:58
*/
public class HexUtil {

	public static String Byte2HexString(byte[] resultBytes){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < resultBytes.length; i++) {
			if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {
				builder.append("0").append(
						Integer.toHexString(0xFF & resultBytes[i]));
			} else {
				builder.append(Integer.toHexString(0xFF & resultBytes[i]));
			}
		}
		return builder.toString();
	}
}
