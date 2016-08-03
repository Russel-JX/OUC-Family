package poi;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.brilliance.base.BaseController;
import com.brilliance.exception.BusinessException;

public class BigDocTest {
	private Logger log = Logger.getLogger(BigDocTest.class);

	public static void main(String[] args) {
//		Logger log = Logger.get
				
		
		System.out.println(BigDecimal.valueOf(522.00).equals(522.00));
		System.out.println(BigDecimal.valueOf(522.00).equals(BigDecimal.valueOf(522)));
		System.out.println(BigDecimal.valueOf(522.00).equals(BigDecimal.valueOf(522.00)));
		System.out.println(BigDecimal.valueOf(522.00).compareTo(BigDecimal.valueOf(522)));
		
		try{
			int uu = 9/0;
		}catch(Exception e){
//			throw new BusinessException("fffffffffff"+e.getMessage());
			throw new BusinessException("fffffffffff",e);
		}
	}

}
