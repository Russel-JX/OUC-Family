package clalendar;

import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
//		cal.set(Calendar.DATE, Calendar.DATE-1);
		cal.add(Calendar.DATE, -1);
		System.out.println(cal.getTime());
	}

}
