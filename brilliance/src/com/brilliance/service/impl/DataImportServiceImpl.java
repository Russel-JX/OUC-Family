package com.brilliance.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.dao.CommonDao;
import com.brilliance.dao.DeliverAreasImportDao;
import com.brilliance.dao.ExpressDeliverDao;
import com.brilliance.dao.impl.CommonDaoImpl;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.po.DeliverAreas;
import com.brilliance.po.ExpressDeliverDetail;
import com.brilliance.po.InvalidRow;
import com.brilliance.service.CommonService;
import com.brilliance.service.CustomizationAddressService;
import com.brilliance.service.DataImportService;
import com.brilliance.service.ExpressInfoService;
import com.brilliance.utils.Constants;
import com.brilliance.utils.GsonSingleton;
import com.brilliance.utils.Tools;

@Service
@Transactional
public class DataImportServiceImpl extends BaseService<DeliverAreas> implements DataImportService {

	@Resource
	private ExpressInfoService expressInfoService;
	
	@Resource
	private DeliverAreasImportDao areasImportDao;
	
	@Resource
	private ExpressDeliverDao  expressDeliverDao;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private CustomizationAddressService customizationAddressService;
	
	public void deliverAreasImport(File file) throws BriException{
		logger.debug("=== file path :"+file.getAbsolutePath());
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheetAt(0);
			String expressName = sheet.getSheetName().trim();
			
			//fetch expresscode by name
			String expressCode = commonService.findExpressCodeByName(expressName);
			
			Row row = null;
			DeliverAreas deliverAreas = null;
			String name = "";
			String id = "";
			Map<String,String> provinceMap = new HashMap<String,String>();
			Map<String,String> cityMap = new HashMap<String,String>();
			Map<String,String> areaMap = new HashMap<String,String>();
			List<DeliverAreas> list = new ArrayList<DeliverAreas>();
			Calendar cal = Calendar.getInstance();
			int rowsNumber = sheet.getLastRowNum();
			int cnt = 0;
			String tmp = "";
			for (int i= 1; i <= rowsNumber; i++) {
				row = sheet.getRow(i);
				if(Tools.isBlankRow(row)){
					continue;
				}
				cnt++;
				deliverAreas = new DeliverAreas();
				deliverAreas.setExpressCode(expressCode);
				//province column
				name = Tools.isEmptyCell(row.getCell(0), "");
				
				if(provinceMap.containsKey(name)){
					id = provinceMap.get(name);
				}else{
					//fetch from DB
					id = commonService.findProvinceIdByName(name);
					provinceMap.put(name, id);
				}
				deliverAreas.setProvince(name);
				deliverAreas.setProvinceId(id);
				
				//city column
				name = Tools.isEmptyCell(row.getCell(1), "");
				
				if (cityMap.containsKey(name)){
					id = cityMap.get(name);
				}else{
					//fetch from DB
					id = commonService.findCityIdByName(name);
					cityMap.put(name, id);
				}
				deliverAreas.setCity(name);
				deliverAreas.setCityId(id);
				
				//area column
				name = Tools.isEmptyCell(row.getCell(2), "");
				if (areaMap.containsKey(name)){
					id = areaMap.get(name);
				}else{
					//fetch from DB
					id = commonService.findAreaIdByName(name);
					areaMap.put(name, id);
				}
				
				tmp = Tools.isEmptyCell(row.getCell(4), "");
				
				deliverAreas.setArea(name);
				deliverAreas.setAreaId(id);
				deliverAreas.setTown(Tools.isEmptyCell(row.getCell(3), ""));
				deliverAreas.setOfficeNumber(tmp);
				deliverAreas.setMobile(Tools.isEmptyCell(row.getCell(5), ""));
				deliverAreas.setSubExpressName(Tools.isEmptyCell(row.getCell(6), ""));
				deliverAreas.setSubExpressAddress(Tools.isEmptyCell(row.getCell(7), ""));
				deliverAreas.setDeliverAreas(Tools.isEmptyCell(row.getCell(8), ""));
				deliverAreas.setNonDeliverAreas(Tools.isEmptyCell(row.getCell(9), ""));
				deliverAreas.setCreateTime(cal.getTime());
				
				list.add(deliverAreas);
			}
			logger.debug("=== amout is :"+cnt);
			areasImportDao.saveDeliverAreaData(list);
			
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (null != file){
					file.delete();
				}
			}
		}
	}

	public void expressDeliverImport(File file) throws BriException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheetAt(0);
			String expressName = sheet.getSheetName().trim();
			
			//fetch expresscode by name
			String expressCode = commonService.findExpressCodeByName(expressName);
			
			Row row = null;
			ExpressDeliverDetail deliverDetail = null;
			String from = "";
			String to = "";
			String code = "";
			String key = "";
			List<ExpressDeliverDetail> list = new ArrayList<ExpressDeliverDetail>();
			Map<String,String> deliveMapping = new HashMap<String, String>();
			
			Calendar cal = Calendar.getInstance();
			
			int rowsNumber = sheet.getLastRowNum();
			int cnt = 0;
			for (int i= 2; i <= rowsNumber; i++) {
				row = sheet.getRow(i);
				if(Tools.isBlankRow(row)){
					continue;
				}
				cnt++;
				deliverDetail = new ExpressDeliverDetail();
				deliverDetail.setExpressCode(expressCode);
				
				from = Tools.isEmptyCell(row.getCell(0), "");
				to = Tools.isEmptyCell(row.getCell(1), "");
				
				key = from+","+to;
				if(deliveMapping.containsKey(key)){
					code = deliveMapping.get(key);
				}else{
					//fetch from DB
					code = commonService.findExpDeliverMap(from, to);
					deliveMapping.put(key, code);
				}
				deliverDetail.setDeliverCode(code);
				
				
				deliverDetail.setCharge(Tools.isEmptyCell(row.getCell(2), ""));
				deliverDetail.setDeliverDays(Integer.parseInt(Tools.isEmptyNumberCell(row.getCell(3), String.valueOf(Constants.DELIVER_DAYS))));
				deliverDetail.setCreateTime(cal.getTime());
				deliverDetail.setDesc(from+"->"+to);
				
				list.add(deliverDetail);
			}
			logger.debug("=== amout is :"+cnt);
			expressDeliverDao.saveExpDeliverData(list);
			
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (null != file){
					file.delete();
				}
			}
		}
		
	}
	
	/**
	 * 数据上传 - 数据库导入
	 * 
	 * @param request
	 *            Http请求
	 */
	@SuppressWarnings("rawtypes")
	public void dbimportToCustAddr() throws BriException {
		List<DeliverAreas> areas = areasImportDao.getAllAddressInfo();
		if (!CollectionUtils.isEmpty(areas)) {
			Iterator<DeliverAreas> itor = areas.iterator();
			Map map = null;
			Map locationMap = null;
			int status = 0;
			List<CustomizationAddressInfo> list = new ArrayList<CustomizationAddressInfo>();
			int cnt = 0;
			int batch = 0;
			while (itor.hasNext()) {
				DeliverAreas tmp = itor.next();
				String city = tmp.getCity();
				String preAddress = tmp.getProvince()+Constants.BLANK+tmp.getCity()+Constants.BLANK+tmp.getArea()+Constants.BLANK;
				String tmpPreAddress = tmp.getProvince()+tmp.getCity()+tmp.getArea();
				String tailAddress = tmp.getSubExpressAddress();
				String hotName = tmp.getSubExpressName();
				map = Tools.addrValidation(tmpPreAddress+tailAddress, city);
				if (null != map) {
					status = (int) Float.parseFloat(map.get("status")
							.toString());
					if (0 == status) {
						locationMap = (Map) ((Map) map.get("result"))
								.get("location");
						String latitude = locationMap.get("lat").toString();
						String longitude = locationMap.get("lng").toString();

						String tmpOfficeNo = tmp.getOfficeNumber();
						String tmpMobile = tmp.getMobile();
						String officeNo = "";
						String mobile = "";
						if(!StringUtils.isEmpty(tmpOfficeNo)){
							officeNo = StringUtils.split(tmpOfficeNo,",")[0];
						}
						if(!StringUtils.isEmpty(tmpMobile)){
							mobile = StringUtils.split(tmpMobile, ",")[0];
						}
						CustomizationAddressInfo cust = new CustomizationAddressInfo();
						cust.setCreateTime(Tools.getTime());
						cust.setAddressDetail(preAddress + tailAddress);
						cust.setTailAddress(tailAddress);
						cust.setAddressId(Tools.getTimeStr());
						// cust.setStoreAddressId(Tools.getCurrentMiillSeq());
						// customizationAddressInfo.setContactName(contactName);
						cust.setOfficeNo(officeNo);
						cust.setMobile(mobile);
						cust.setExpressCode(tmp.getExpressCode());
						cust.setHotName(hotName);
						cust.setCreatedBy(Constants.ADMIN_ID);
						cust.setLongitude(longitude);
						cust.setArchiveFlag(Constants.UNARCHIVED);
						cust.setLatitude(latitude);
						cust.setProvinceId(tmp.getProvinceId());
						cust.setCityId(tmp.getCityId());
						cust.setAreaId(tmp.getAreaId());
						cust.setDataType(Constants.DATATYPE_STORE);
						cust.setSource(Constants.SOURCE_DBIMPORT);

						list.add(cust);
						if (cnt == 999) {
							System.out.println((batch++)+"saving...............");
							customizationAddressService.saveAll(list);
							System.out.println((batch)+"saved completed...............");
							list.clear();
						}
					}
					cnt++;
				}else{
					logger.equals("error data id= "+tmp.getId());
				}
				//把最后不足一千条的存入数据库
				if(!CollectionUtils.isEmpty(list)){
				    customizationAddressService.saveAll(list);
				}

			}

		}
	}
	
	/*private String getAddress(DeliverAreas areas) {
		String address = "";
		if (null != areas) {
			address = areas.getSubExpressAddress();

			address = areas.getProvince() + Constants.BLANK + areas.getCity()
					+ Constants.BLANK + areas.getArea() + Constants.BLANK
					+ address;
		}
		return address;
	}*/
	
	/*@SuppressWarnings({ "unchecked"})
	public Map<String,Object> addrValidation(String address,String city){
		//String address = request.getParameter("tailAddress");
		//String city = request.getParameter("city");
		//String output = request.getParameter("output");
		//String urlStr = "http://api.map.baidu.com/geocoder/v2/?address=东渚镇&city=苏州市&output=json&ak=vQKsPsU4CmEfIeBfM13F4RrD";
		String urlStr = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&city="+city+"&output=json"+"&ak="+Constants.BAIMAP_AK;
		URL url;
		//String urlStr ="http://api.map.baidu.com/geocoder/v2/?address=湖北省武汉市水果湖北环路28号&city=武汉市&output=json&ak=vQKsPsU4CmEfIeBfM13F4RrD";
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
	}*/
	
	//测试用门店Excel上传
	public Map<String,Object> importToCustAddress(String filePath,File file) throws BriException{
		logger.debug("=== file path :"+file.getAbsolutePath());
		Map<String,Object> importStatusMap = new HashMap<String,Object>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheetAt(0);
			String expressName = sheet.getSheetName().trim();
			
			//fetch expresscode by name
			String expressCode = commonService.findExpressCodeByName(expressName);
//			CommonDao commonDao = new CommonDaoImpl();
//			String expressCode = commonDao.findExpressCodeByName(expressName);
			
			//Wrong express name(sheet name), stop importing.
			if(Tools.isBlank(expressCode)){
				importStatusMap.put("importStatus", "导入失败！ ‘"+expressName+"’"+Constants.WRONG_SHEET_NAME);
				return importStatusMap;
			}
			
			
			
			Row row = null;
			CustomizationAddressInfo cust = null;
			String provinceName = "";
			String cityName = "";
			String areaName = "";
			String id = "";
			Map<String,String> provinceMap = new HashMap<String,String>();
			Map<String,String> cityMap = new HashMap<String,String>();
			Map<String,String> areaMap = new HashMap<String,String>();
			List<CustomizationAddressInfo> list = new ArrayList<CustomizationAddressInfo>();
			int rowsNumber = sheet.getLastRowNum();
			int cnt = 0;
			String officeNo = "";
			String address = "";
			String tmpAddr = "";
			String city = "";
			String province = "";
			String area = "";
			String contact = "";
			String hotName = "";
			String latitude = "";
			String longitude ="";
			Map map = null;
			Map locationMap = null;
			List<InvalidRow> invalidRowLst = new ArrayList<InvalidRow>();
			String msgDesc = "";//提示消息
			boolean flagValid = true;//此行记录是否有效
			for (int i = 1; i <= rowsNumber; i++) {
				InvalidRow invalidRow = new InvalidRow();
				row = sheet.getRow(i);
				if (Tools.isBlankRow(row)) {
					continue;
				}
				cnt++;

				tmpAddr = Tools.isEmptyCell(row.getCell(7), "");
				city = Tools.isEmptyCell(row.getCell(1), "");
				province = Tools.isEmptyCell(row.getCell(0), "");
				area = Tools.isEmptyCell(row.getCell(2), "");
				address = province+Constants.BLANK+city+Constants.BLANK+area+Constants.BLANK+tmpAddr;
				contact = Tools.isEmptyCell(row.getCell(8),null);
				hotName = Tools.isEmptyCell(row.getCell(9),Tools.isEmptyCell(row.getCell(6),""));;
				
				map = Tools.addrValidation(tmpAddr, city);
				
				provinceName = province;
				if (provinceMap.containsKey(provinceName)) {
					id = provinceMap.get(provinceName);
				} else {
					// fetch from DB
					id = commonService.findProvinceIdByName(provinceName);
//					id = commonDao.findProvinceIdByName(provinceName);
					provinceMap.put(provinceName, id);
				}

				cityName = city;

				if (cityMap.containsKey(cityName)) {
					id = cityMap.get(cityName);
				} else {
					// fetch from DB
					id = commonService.findCityIdByName(cityName);
//					id = commonDao.findCityIdByName(cityName);
					cityMap.put(cityName, id);
				}

				areaName = area;
				if (areaMap.containsKey(areaName)) {
					id = areaMap.get(areaName);
				} else {
					// fetch from DB
					id = commonService.findAreaIdByName(areaName);
//					id = commonDao.findAreaIdByName(areaName);
					areaMap.put(areaName, id);
				}
				
				if (null != map) {
					int status = (int) Float.parseFloat(map.get("status")
							.toString());
					//Got the right location info.
					if (0 == status) {
						cust = new CustomizationAddressInfo();
						locationMap = (Map) ((Map) map.get("result"))
								.get("location");
						latitude = locationMap.get("lat").toString();
						longitude = locationMap.get("lng").toString();

						officeNo = Tools.isEmptyCell(row.getCell(4), "");
						cust.setProvinceId(provinceMap.get(provinceName));
						cust.setCityId(cityMap.get(cityName));
						cust.setAreaId(areaMap.get(areaName));
						cust.setCreateTime(Tools.getTime());
						cust.setAddressDetail(address);
						cust.setTailAddress(tmpAddr);
						cust.setAddressId(Tools.getTimeStr());
						// cust.setStoreAddressId(Tools.getCurrentMiillSeq());
						cust.setOfficeNo(officeNo);
						cust.setMobile(Tools.isEmptyCell(row.getCell(5), ""));
						cust.setExpressCode(expressCode);
						cust.setContactName(contact);
						cust.setHotName(hotName); 
						cust.setCreatedBy("101");
						cust.setLongitude(longitude);
						cust.setLatitude(latitude);
						cust.setDataType(Constants.DATATYPE_STORE);
						cust.setArchiveFlag(Constants.UNARCHIVED);
						cust.setSource(Constants.SOURCE_FILE_IMPORT);
					}else{//Got invalid location info.
						flagValid = false;
						invalidRow.setRowIndex(i);
						invalidRow.setRowInfo(row);
						msgDesc += Constants.WRONG_DETAIL_ADDRESS+";";
					}
					
					//validate province, city and area.
					if(Tools.isEmpty(provinceMap.get(provinceName))||Tools.isEmpty(cityMap.get(cityName))||Tools.isEmpty(areaMap.get(areaName))){
						flagValid = false;
						invalidRow.setRowIndex(i);
						invalidRow.setRowInfo(row);
						if(Tools.isEmpty(provinceMap.get(provinceName))){
							msgDesc += Constants.WRONG_PROVINCE_NAME+";";
						}
						if(Tools.isEmpty(cityMap.get(cityName))){
							msgDesc += Constants.WRONG_CITY_NAME+";";
						}
						if(Tools.isEmpty(areaMap.get(areaName))){
							msgDesc += Constants.WRONG_AREA_NAME+";";
						}
					}
					if(flagValid==true){
						list.add(cust);
					}
					flagValid=true;
					invalidRow.setProblemDesc(msgDesc);
					msgDesc = "";
					if(!Tools.isEmpty(invalidRow.getProblemDesc())){
						invalidRowLst.add(invalidRow);
					}
				}
			}
			//generate invalid Excel.
			if(invalidRowLst.size()>0){
				//加入表头
				InvalidRow firstInvalidRow = new InvalidRow();
				firstInvalidRow.setRowInfo(sheet.getRow(0));
				firstInvalidRow.setProblemDesc("导入出错提示");
				invalidRowLst.add(0,firstInvalidRow);
				
				importStatusMap.put("importStatus", Constants.IMPORT_PARTLLYSUCCESS);
				File invalidFile = generatInvalidFile(filePath,expressName,invalidRowLst);
				importStatusMap.put("invalidFile", invalidFile);
			}else{
				importStatusMap.put("importStatus", Constants.IMPORT_SUCCESS);
			}
			customizationAddressService.saveBulk(list);
			
		}catch(IOException e){
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (null != file){
					//删除输入的文件流
					file.delete();
				}
			}
		}
		
		return importStatusMap;
	}
	
	/**导出Excel
	POI 行和列都从0开始
	1.创建新的row时，不能用赋值的方式创建新row，否则无法创建成功；必须要创建row下的cell，set单元格的value。
	即，必须手动创建row，cell,以及设置cell的value，样式...
	2.不同workbook中的cellstyle不能相互设置，需使用newStyle.cloneStyleFrom(originalStyle)方法;
	*/
	public static File generatInvalidFile(String filePath,String sheetName,List<InvalidRow> invalidRowLst){
		File file = new File(filePath+"\\"+Tools.getTimeStr()+"Excel.xlsx");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(sheetName);
			
			//列数
			short colNumber = ((Row) invalidRowLst.get(0).getRowInfo()).getLastCellNum();//从1开始
			
			for(int k=0;k<invalidRowLst.size();k++){
				Row newRow = sheet.createRow(k);
				InvalidRow invalidRowInfo = null;
				Row invalidRow = null;
				invalidRowInfo = invalidRowLst.get(k);
				invalidRow = (Row)(invalidRowInfo.getRowInfo());
				
				
				//还原原有的表格数据
				for(int i=0;i<colNumber;i++){
					Cell newCell = newRow.createCell(i);
					Cell invalidCell = null;
					
					invalidCell = invalidRow.getCell(i);
					//之前的cell不存在，则新的Excel中此位置的cell也没有
					if(Tools.isEmptyCell(invalidCell)){
						continue;
					}
					CellStyle newStyle = workbook.createCellStyle();
					newStyle = workbook.createCellStyle();
					CellStyle invalidStyle = invalidCell.getCellStyle();
					newStyle.cloneStyleFrom(invalidStyle);
					newCell.setCellStyle(newStyle);
					
					newCell.setCellType(invalidCell.getCellType());
					//???写一个方法，根据单元格的value类型，选择合适的value。？？？？
					newCell.setCellValue(invalidCell.getStringCellValue());
				}
				//为有问题的行，添加新cell
				CellStyle additionalStyle = workbook.createCellStyle();
				Cell additionalCell = newRow.createCell(colNumber);
				Font newFont = workbook.createFont();
				newFont.setColor(HSSFColor.RED.index);
				additionalStyle.setFont(newFont);
				additionalCell.setCellStyle(additionalStyle);
				additionalCell.setCellType(Cell.CELL_TYPE_STRING);
				additionalCell.setCellValue(invalidRowInfo.getProblemDesc());
//				for(int i=0;i<newRow.getLastCellNum();i++){
//					System.out.println("此行单元格值是: "+newRow.getCell(i));
//				}
			}
			workbook.write(fos);
		} catch(IOException e){
			System.out.println("文件导出出错！");
			e.printStackTrace();
		}
		finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
//			if(file!=null){
//				file.delete();
//			}
		}
		
		return file;
	}
	
	/*private String getAddress(Row row){
		String address = "";
		String province = Tools.isEmptyCell(row.getCell(0), "");
		if(!StringUtils.isEmpty(province)){
			address += province+Constants.BLANK;
		}
		String city = Tools.isEmptyCell(row.getCell(1), "");
		if(!StringUtils.isEmpty(city)){
			address += city+Constants.BLANK;
		}
		String area = Tools.isEmptyCell(row.getCell(2), "");
		if(!StringUtils.isEmpty(area)){
			address += area+Constants.BLANK;
		}
		return address;
	}*/
}
