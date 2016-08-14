package com.brilliance.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.brilliance.base.ControllerReturns;
import com.brilliance.base.MessageContent;
import com.brilliance.base.ServiceReturns;

public class Tools {

	private static Calendar calendar = null;
	static{
		calendar = Calendar.getInstance();
	}
	
	public static Date getToday() throws ParseException{
		return parseToDate(calendar.getTime());
	}
	
	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static <T> T isNull(T obj, T defaultVal) {
		if (obj == null) {
			return defaultVal;
		}
		return obj;
	}

	public static String isNull(Object obj, String defaultVal) {
		if (obj == null) {
			return defaultVal;
		}
		return obj.toString();
	}

	public static String isNullAndEmpty(Object obj, String defaultVal) {
		if (obj == null || "".equals(obj)) {
			return defaultVal;
		}
		return obj.toString();
	}

	public static boolean isEmpty(Object obj) {
		return null == obj || "".equals(obj);
	}

	public static boolean isBlank(String val) {
		if (isNull(val) || "".equals(val)) {
			return true;
		}
		return false;
	}

	public static String decode(String str, String charset) throws Exception {
		return java.net.URLDecoder.decode(str, charset);
	}

	public static String decode(String str) throws Exception {
		return java.net.URLDecoder.decode(str, "UTF-8");
	}

	public static ControllerReturns getErrorsRetruns(String errorId) {
		ControllerReturns returns = new ControllerReturns();
		returns.setSuccess(false);
		returns.setMessageType("error");
		returns.setMessage(new MessageContent(errorId));
		return returns;
	}
	
	public static ControllerReturns getErrorsRetrunsMsg(String messageCode) {
		ControllerReturns returns = new ControllerReturns();
		returns.setSuccess(false);
		returns.setMessageType("error");
		returns.setMessage(messageCode);
		return returns;
	}
	
	public static ControllerReturns getExceptionControllerRetruns(Exception e) {
		ControllerReturns returns = new ControllerReturns();
		returns.setSuccess(false);
		returns.setMessageType("error");
		returns.setMessage(new MessageContent(Constants.ERR_COMMON_SYS_ERROR));
		return returns;
	}

	public static ServiceReturns getExceptionServiceReturns(Exception e) {
		ServiceReturns returns = new ServiceReturns();
		returns.setSuccess(false);
		returns.setMessageType("error");
		returns.setMessage(new MessageContent(Constants.ERR_COMMON_SYS_ERROR));
		return returns;
	}

	public static ModelAndView getExceptionModelAndView(Exception e) {
		return converToModelAndView(getExceptionControllerRetruns(e));
	}

	public static ModelAndView converToModelAndView(ControllerReturns returns) {
		if (returns == null) {// 应该确保returns不为空
			return null;
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName(returns.getForwardUrl());// 跳转路径
		mView.getModel().putAll(returns.getData());// 数据部分
		mView.getModel().put("messageType", returns.getMessageType());// 消息类型
		mView.getModel().put("message", returns.getMessage());// 具体消息
		mView.getModel().put("success", returns.isSuccess());// 操作状态
		return mView;
	}

	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		DateFormatter format = new DateFormatter("yyyy-MM-dd HH:mm:ss");
		return format.print(date, Locale.getDefault());
	}
	
	public static String formatDate(Date date,String patten) {
		if (date == null) {
			return "";
		}
		DateFormatter format = new DateFormatter(patten);
		return format.print(date, Locale.getDefault());
	}

	public static Date parseToDate(String source) throws ParseException {
		return parseToDate(source,Constants.DATE_PATTEN);
	}
	
	public static Date parseToDate(Date source) throws ParseException {
		String tmp = formatDate(source,Constants.DATE_PATTEN);
		return parseToDate(tmp);
	}
	
	public static Date parseToDate(String source,String patten) throws ParseException {
		if (isEmpty(source)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		
		return sdf.parse(source);
	}
	
	
	
	
	public static boolean isBlankRow(Row row) {
		return null == row || isEmptyCell(row.getCell(0))
				|| isEmptyCell(row.getCell(1));
	}

	public static String isEmptyCell(Cell cell, String defaultStr) {
		if (isEmptyCell(cell)) {
			return defaultStr;
		}
		//some value looks lie "'0556-7260190 ", need remove the comma
		String tmp = cell.toString();
		if (tmp.startsWith("'")){
		    tmp = tmp.substring(1, tmp.length());
		}
		return trimAllBlanks(tmp);
	}

	public static boolean isEmptyCell(Cell cell) {
		if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK
				|| !StringUtils.hasLength(cell.toString())) {
			return true;
		}
		return false;
	}

	public static String isEmptyNumberCell(Cell cell, String defaultStr) {
		if (isEmptyCell(cell)) {
			return defaultStr;
		}
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf((long) cell.getNumericCellValue());
		}
		return cell.toString().trim();
	}

	public static String trimAllBlanks(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static String getRandom(int seed){
		Random rd1 = new Random();
		return String.valueOf(rd1.nextInt(seed));
	}
	
	
	
	public static String getTimeStr() {
		return ((Long)System.nanoTime())+generate();
	}
	
	public static String getCurrentMiillSeq() {
		Date date = calendar.getTime();
		return date.getTime()+generate();
	}
	
	private synchronized static String generate() {
		 
        int n = (int) (Math.random() * 300) + 300;
        return n + "";
    }
	
	/**
	 * 
	 * @param address 地址中决不允许包含有空格，否则将抛出异常
	 * @param city 城市名
	 * @return 经纬度 
	 */
	@SuppressWarnings({ "unchecked"})
	public static Map<String,Object> addrValidation(String address,String city){
		//String city = request.getParameter("city");
		//String output = request.getParameter("output");
		//String urlStr = "http://api.map.baidu.com/geocoder/v2/?address=东渚镇&city=苏州市&output=json&ak=vQKsPsU4CmEfIeBfM13F4RrD";
		String urlStr = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&city="+city+"&output=json"+"&ak="+Constants.BAIMAP_AK;
		URL url;
		//String urlStr ="http://api.map.baidu.com/geocoder/v2/?address=湖北省武汉市武昌区水果湖北环路28号&city=武汉市&output=json&ak=vQKsPsU4CmEfIeBfM13F4RrD";
		InputStream urlStream = null;
		BufferedReader bufferedReader = null;
		String sTotalString = "";
		Map<String,Object> map = null;
		try {
			url = new URL(urlStr);
			URLConnection urlConn = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) urlConn;
			int responseCode = httpConn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				
				//System.err.println("成功");
				urlStream = httpConn.getInputStream();
				bufferedReader = new BufferedReader(
						new InputStreamReader(urlStream));
				String sCurrentLine = "";
				
				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					sTotalString += sCurrentLine;
				}
				map = GsonSingleton.getGsonInstance().fromJson(sTotalString, Map.class);
				
				//Map locationMap = (Map)((Map)map.get("result")).get("location");

				
				//System.out.println("lat == "+locationMap.get("lat"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(urlStream !=null){
				try {
					urlStream.close();
				} catch (IOException e) {
					System.err.println("关闭输入流失败");
					e.printStackTrace();
				}
			}
			if(bufferedReader!=null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					System.err.println("关闭bufferedReader失败");
					e.printStackTrace();
				}
			}
			
			
		}
		return map;
	}
	
	
//唯一门店地址
	/*public synchronized static String getNanoTimeStr(){
		return ((Long)System.nanoTime())+generate();
	}*/
	
	public static Date getTime(){
		return calendar.getTime();
	}
	private static void combineSQL() {
		int cnt = 50001;
		String[] provinces = Constants.PROVINCES_ARY;
		String[] provinces1 = new String[provinces.length];
		System.arraycopy(provinces, 0, provinces1, 0, provinces.length);
		// String ss = "";
		File file = new File("sql.txt");
		int index = 1;
		List<String> ss = new ArrayList<String>();
		try {
			for (int i = 0; i < provinces.length; i++) {
				for (int j = i; j < provinces1.length; j++) {
					ss.add("insert into EXPRESS_DELIVER_INFO (id,DELIVERCODE,F_PROVINCEID,FROMADDR,T_PROVINCEID,TOADDR,CREATETIME) values ("
							+ index
							+ ","
							+ cnt
							+ ", '"
							+ Constants.PROVINCE_MAP.get(provinces[i])
							+ "' ,'"
							+ provinces[i]
							+ "', '"
							+ Constants.PROVINCE_MAP.get(provinces1[j])
							+ "', '" + provinces1[j] + "'," + "now());");
					index++;
					cnt++;

				}
				FileUtils.writeLines(file, ss);
			}
			System.out.println("............finished..................");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getAreaSql() {
		File file = new File("guangdong.txt");

		try {
			List<String> list = FileUtils.readLines(file);
			System.out.println("ddddddd = " + list.size());
			Iterator<String> itor = list.iterator();
			int pos1 = 0;
			String id = "";
			String name = "";
			int pos2 = 0;
			String sql = "";
			List<String> sqls = new ArrayList<String>();
			while (itor.hasNext()) {
				String ss = itor.next();
				pos1 = ss.indexOf("=");
				id = ss.substring(pos1 + 2, pos1 + 11);
				pos2 = ss.indexOf("/");
				name = ss.substring(pos1 + 13, pos2 - 1);
				// System.out.println("rrr = "+ss);
				sql = "insert into areas_info(areaid,area,cityid) values('"
						+ id + "','" + name + "','441900');";
				sqls.add(sql);
				System.out.println(sql);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//addrValidation("","");
		//StringBuilder ss = new StringBuilder("46565fdx8989");
		//ss.replace(0, 2, "eee");
		Map<StringBuilder, String> map = new HashMap<StringBuilder, String>();
		StringBuilder ss = null;
		System.out.println("ddddddd == "+org.apache.commons.lang3.StringUtils.isEmpty(ss));
		//System.out.println(new BigDecimal("3.1429").setScale(Constants.DECIMAL_PLACE_ONE,BigDecimal.ROUND_HALF_UP));
	}

}