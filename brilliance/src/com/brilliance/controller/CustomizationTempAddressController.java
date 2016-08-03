package com.brilliance.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.brilliance.base.BaseController;
import com.brilliance.base.BriException;
import com.brilliance.dao.CustomizationTempAddressDao;
import com.brilliance.dao.impl.CustomizationTempAddressDaoImpl;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.CustomizationTempAddressInfo;
import com.brilliance.po.ExpressInfo;
import com.brilliance.service.AdminInfoService;
import com.brilliance.service.AreasInfoService;
import com.brilliance.service.CitiesInfoService;
import com.brilliance.service.CustomizationTempAddressService;
import com.brilliance.service.ExpressInfoService;
import com.brilliance.service.ProvincesInfoService;
/**
 * 
 * @author Russel
 *批量导入联系人地址信息
 */
@Controller
@Scope("request")
public class CustomizationTempAddressController extends BaseController{

	@Resource
	private CustomizationTempAddressService customizationTempAddressService;
	
	@Resource
	private ExpressInfoService expressInfoService;
	
	@Resource
	private AdminInfoService adminInfoService;
	
	@Resource
	private ProvincesInfoService provincesInfoService;
	
	@Resource
	private CitiesInfoService citiesInfoService;
	
	@Resource
	private AreasInfoService areasInfoService;
	
	public static String ak = "vQKsPsU4CmEfIeBfM13F4RrD";//百度地图ak
	
	/**
	 * @param request
	 * @return 附近地址管理页面。几大快递公司集合，创建人和省份信息。
	 */
	@RequestMapping(value="forwardToCustTempAddrImport", produces = "text/html;charset=utf-8")
	public ModelAndView forwardToCustTempAddrImport(HttpServletRequest request){
		ModelAndView view = new ModelAndView("express/custAddressInfo");
		//获取公司和省份下拉
		try {
			List<ExpressInfo> expresslst = expressInfoService.getAllExpress();
			List<AdminInfo> adminlst = adminInfoService.getAdminLst();
			view.getModel().put("expresslst", expresslst);
			view.getModel().put("adminlst", adminlst);
			/*@SuppressWarnings("unchecked")
			List<ProvincesInfo> provicelst = (List<ProvincesInfo>)provincesInfoService.getAll().getData().get("provinces");
			//北京省下的市
			@SuppressWarnings("unchecked")
			List<CitiesInfo> citylst = (List<CitiesInfo>)citiesInfoService.getCitiesByProvinceId("110000").getData().get("cities");
			//北京市下的区
			@SuppressWarnings("unchecked")
			List<AreasInfo> arealst = (List<AreasInfo>)areasInfoService.getAreasByCityId("110100").getData().get("areas");
			view.getModel().put("expresslst", expresslst);
			view.getModel().put("provicelst", provicelst);
			view.getModel().put("citylst", citylst);
			view.getModel().put("arealst", arealst);*/
		} catch (BriException e) {
			e.printStackTrace();
		}
		
		return view;//单个增加 
		//return "upload/custAddrImport";//批量导入
	}
	
	//调用百度地图API（Geocoding API）验证地址有效性.
	@RequestMapping(value="/favouriteAddress/custAddrValidation", produces = "text/html;charset=utf-8")
	public @ResponseBody String custAddrValidation(HttpServletRequest request){
		String address = request.getParameter("tailAddress");
		String city = request.getParameter("city");
		String output = request.getParameter("output");
		//String urlStr = "http://api.map.baidu.com/geocoder/v2/?address=东渚镇&city=苏州市&output=json&ak=vQKsPsU4CmEfIeBfM13F4RrD";
		String urlStr = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&city="+city+"&output="+output+"&ak="+ak;
		System.out.println("URL是："+urlStr);
		URL url;
		InputStream urlStream = null;
		BufferedReader bufferedReader = null;
		HttpURLConnection httpConn = null;
		String sTotalString = "";
		try {
			url = new URL(urlStr);
			URLConnection urlConn = url.openConnection();
			httpConn = (HttpURLConnection) urlConn;
			int responseCode = httpConn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				urlStream = httpConn.getInputStream();
				bufferedReader = new BufferedReader(
						new InputStreamReader(urlStream));
				String sCurrentLine = "";
				
				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					sTotalString += sCurrentLine;
				}
				logger.info(sTotalString);
			}else{
				logger.error("连接百度地图API - GeoCoding 响应 失败");
			}
		} catch(ConnectException e){
			logger.error("连接百度地图API - GeoCoding失败");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpConn.disconnect();
			if(urlStream !=null){
				try {
					urlStream.close();
				} catch (IOException e) {
					logger.error("关闭输入流失败");
					e.printStackTrace();
				}
			}
			if(bufferedReader!=null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error("关闭bufferedReader失败");
					e.printStackTrace();
				}
			}
			
			
		}
		return sTotalString;
	}
	
	
	
	public static void main(String[] args) {
		// 创建工作簿
		Workbook wb = new HSSFWorkbook();
		// 输入流
		File fileIn = new File("C:\\Users\\hp\\Desktop\\顺丰-业务员(供实习生核实输入系统）.xls");
		InputStream fis;
		// 输出流
		File file = new File("C:\\Users\\hp\\Desktop\\out.xls");
		FileOutputStream fos;
		try {
			//读取Excel
			fis = new FileInputStream(fileIn);
			try {
				wb = WorkbookFactory.create(fis);
				//第一个sheet页
				Sheet firstSheet = wb.getSheetAt(0);
				int totalRowNumber = firstSheet.getLastRowNum();
				//遍历第一页
				List<CustomizationTempAddressInfo> custTempAddrInfoList = new ArrayList<CustomizationTempAddressInfo>();
				
				for(int i=0;i<=totalRowNumber;i++){
					Row row  = firstSheet.getRow(i);
					CustomizationTempAddressInfo custTempAddrInfo = new CustomizationTempAddressInfo();
					custTempAddrInfo.setExpressCode("1008");
					custTempAddrInfo.setHotName("aa");
					custTempAddrInfo.setContact((row.getCell(1)==null?"":row.getCell(1).toString()));
					custTempAddrInfo.setOfficeNo(row.getCell(4)==null?"":row.getCell(4).toString());
					custTempAddrInfo.setMobile(row.getCell(5)==null?"":row.getCell(5).toString());
					custTempAddrInfo.setAddressDetail(row.getCell(6)==null?"":row.getCell(6).toString());
					
					custTempAddrInfoList.add(custTempAddrInfo);
				}
				
//				for(int i=0;i<custTempAddrInfoList.size();i++){
//					System.out.println("rowNumber： "+(i+1)+" 联系人： "+custTempAddrInfoList.get(i).getContactName()+
//							" 办公电话："+custTempAddrInfoList.get(i).getOfficeNo()+" 手机： "+custTempAddrInfoList.get(i).getMobile()+
//							" 完整地址是： "+custTempAddrInfoList.get(i).getAddressDetail());
//				}
				
				// 关闭流
				fis.close();
				
				//信息保存
//				customizationTempAddressService.save(custTempAddrInfoList);
				CustomizationTempAddressDao dao = new CustomizationTempAddressDaoImpl();
				for(int i=0;i<=custTempAddrInfoList.size();i++){
					dao.save(custTempAddrInfoList.get(i));
				}
			} catch (InvalidFormatException e) {
				logger.error("输入流格式不正确！不是Excel！");
				e.printStackTrace();
			}
			
//			fos = new FileOutputStream(file);
//			// 创建sheet页
//			Sheet sheet1 = wb.createSheet("SheetOne");
//			Sheet sheet2 = wb.createSheet("SheetTwo");
//			// 创建creationhelper
//			CreationHelper creationHelper = wb.getCreationHelper();
//			// 创建一行
//			Row row1 = sheet1.createRow(0);
//			Row row2 = sheet1.createRow(1);
//			// 创建单元格
//			Cell r1_cell1 = row1.createCell(0);
//			Cell r1_cell3 = row1.createCell(2);
//			Cell r2_cell1 = row2.createCell(0);
//			// 设置单元格内容
//			r1_cell1.setCellValue("Hello World");
//			row1.createCell(1).setCellValue(3.14);
//
//			// 设置单元格日期格式
//			CellStyle cellStyle = wb.createCellStyle();
//			cellStyle.setDataFormat(creationHelper.createDataFormat()
//					.getFormat("yyyy-MM-dd"));
//			r1_cell3.setCellValue(new Date());
//			r1_cell3.setCellStyle(cellStyle);
//
//			// 设置单元格数据类型
//			r2_cell1.setCellValue("你好");
//			row2.createCell(1).setCellValue(true);
//			row2.createCell(2).setCellValue(true);
//			row2.createCell(3).setCellValue(3.1415926);
//			row2.createCell(4).setCellValue("我很好");
//			row2.createCell(5).setCellType(HSSFCell.CELL_TYPE_ERROR);
//
//			// 将输出流写入文件
//			wb.write(fos);
//			// 关闭流
//			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("文件没有找到！！" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件写出错误！" + e.getMessage());
		}
	}
	
	@RequestMapping(value = "/importCustAddress", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String importCustAddress(HttpServletRequest request,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		
		// 输入流
		InputStream fis;
		//创建工作簿
		Workbook wb = new HSSFWorkbook();
		// 输出流
		try {
			//接受导入的文件
			fis = fileUpload.getInputStream();
			try {
				wb = WorkbookFactory.create(fis);
				//第一个sheet页
				Sheet firstSheet = wb.getSheetAt(0);
				int totalRowNumber = firstSheet.getLastRowNum();// start form 0.
				//遍历第一页
				List<CustomizationTempAddressInfo> custTempAddrInfoList = new ArrayList<CustomizationTempAddressInfo>();
				
				for(int i=0;i<=totalRowNumber;i++){
					Row row  = firstSheet.getRow(i);
					CustomizationTempAddressInfo custTempAddrInfo = new CustomizationTempAddressInfo();
					custTempAddrInfo.setExpressCode("1008");
					custTempAddrInfo.setHotName("aa");
					custTempAddrInfo.setContact((row.getCell(1)==null?"":row.getCell(1).toString()));
					custTempAddrInfo.setOfficeNo(row.getCell(4)==null?"":row.getCell(4).toString());
					custTempAddrInfo.setMobile(row.getCell(5)==null?"":row.getCell(5).toString());
					custTempAddrInfo.setAddressDetail(row.getCell(6)==null?"":row.getCell(6).toString());
					
					custTempAddrInfoList.add(custTempAddrInfo);
				}
				
//				for(int i=0;i<custTempAddrInfoList.size();i++){
//					System.out.println("rowNumber： "+(i+1)+" 联系人： "+custTempAddrInfoList.get(i).getContactName()+
//							" 办公电话："+custTempAddrInfoList.get(i).getOfficeNo()+" 手机： "+custTempAddrInfoList.get(i).getMobile()+
//							" 完整地址是： "+custTempAddrInfoList.get(i).getAddressDetail());
//				}
				
				// 关闭流
				fis.close();
				
				//信息保存
				customizationTempAddressService.save(custTempAddrInfoList);
			} catch (InvalidFormatException e) {
				logger.error("输入流格式不正确！不是Excel！");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("文件没有找到！！" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件写出错误！" + e.getMessage());
		}
		
		
		return null;
	}
	
	
}
