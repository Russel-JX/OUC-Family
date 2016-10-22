package com.igate.izone.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.naming.java.javaURLContextFactory;
/**
 * 时间的转化工具
 * @author Yicheng Zhou
 *
 */
public class DateUtil {
	
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * @param stringDate 字符串的日期
	 * @return 转化成功之后的java.sql.Date
	 * @throws ParseException 转化错误
	 */
	public static Date string2Date(String stringDate) throws ParseException{
		//util date -> sql date
		return new Date(format.parse(stringDate).getTime());
	}
	/**
	 * @param date sql date 类型的时间
	 * @return 字符串时间
	 */
	public static String date2String(Date date){
		//sql date -> util date
		return format.format(new java.util.Date(date.getTime()));
	}
	
}
