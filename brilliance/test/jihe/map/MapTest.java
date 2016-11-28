package jihe.map;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MapTest {

	private static Log logger = LogFactory.getLog(MapTest.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		map.put("a", "123");
		//理论见HashMap源码
		logger.info("put放入重复的key之后，返回的值原来的value："+map.put("a", "456"));
		logger.info("put放入重复的key之后，get的值是覆盖后的value："+map.get("a"));

	}

}
