package com.igate.izone.controller.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.igate.izone.dto.EmployeeDTO;
import com.igate.izone.service.EmployeeServiceIntf;
import com.igate.izone.util.DateUtil;
import com.igate.izone.util.DtoVoUtil;
import com.igate.izone.util.JsonUtil;
import com.igate.izone.util.MD5Util;
import com.igate.izone.vo.EmployeeRes;
import com.igate.izone.vo.ReturnMessage;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping(value="/employee")
public class EmployeeManagementController  {

	@Resource
	private EmployeeServiceIntf employeeService;
	
	/**
	 * @param request
	 * @return 增加是否成功的标志,成功为success,失败为fail
	 * @throws ParseException
	 */
	@RequestMapping(value="/createNewEmployee",method=RequestMethod.POST)
	public @ResponseBody String createNewEmployee(HttpServletRequest request) throws ParseException {
		ReturnMessage em = null;
		String empID = request.getParameter("empId");
		String emp_CN_name = request.getParameter("emp_CN_name");
		String emp_EN_name = request.getParameter("emp_EN_name");
		String engageDate = request.getParameter("engageDate");
		String empType = request.getParameter("empType");
		//System.out.println("empId="+empID+",emp_CN_name="+emp_CN_name+",emp_EN_name="+emp_EN_name+",engageDate="+engageDate);
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmpID(empID);
		employeeDTO.setEmp_CN_name(emp_CN_name);
		employeeDTO.setEmp_EN_name(emp_EN_name);
		employeeDTO.setEmpType(empType);
		employeeDTO.setEngageDate(DateUtil.string2Date(engageDate));
		employeeDTO.setPassword(MD5Util.md5("111111"));//给密码加密
		employeeDTO.setEmp_head_portrait("C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg");//指定默认的头像
		employeeDTO.setDeleteFlag(1);//1代表在职，0代表离职
		//调用dao层的增加员工函数
		int updateLines = employeeService.createNewEmployee(employeeDTO);
		if(updateLines == 0){
			em = new ReturnMessage("error", "fail");
			return JsonUtil.formatObject2Json(em);
		}
		em = new ReturnMessage("success", "success");
		System.out.println(JsonUtil.formatObject2Json(em));
		return JsonUtil.formatObject2Json(em);
	}
	
	@RequestMapping(value="/uploadEmployeeExecl",method=RequestMethod.POST)
	public @ResponseBody String uploadEmployeeExecl(@RequestParam("chooseUoloadEmployeeExecl")MultipartFile multipartFile,
			HttpSession session){
		//设置字符编码
		String[] temp = multipartFile.getOriginalFilename().split("\\.");
		String fileType = temp[temp.length-1];//xls或者xlsx,xls要用HSSFWorkBook处理，xlsx要用XSSFWorkBookc处理
		InputStream is = null;
		ReturnMessage em = null;
		try {
			is = multipartFile.getInputStream();//上传execl的输入流
			String jsonData = poiManage(fileType, is,session);
			System.out.println(jsonData);
			return jsonData;
			//return new String(jsonData.getBytes(),"ISO-8859-1");//@ResponseBody内部编码是采用ISO-8859-1编码
		} catch (IOException e) {
			e.printStackTrace();
			em = new ReturnMessage("error", "fail");;
			return JsonUtil.formatObject2Json(em);
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param session 从session中读取已上传的execl文件的流
	 * @return success代表正确返回，fail代表数据问题，serverError代表服务器问题
	 */
	@RequestMapping(value="/confirmUploadEmployeeExecl",method=RequestMethod.POST)
	public @ResponseBody String confirmUploadEmployeeExecl(HttpSession session){
		@SuppressWarnings("unchecked")
		ReturnMessage em = null;
		List<EmployeeRes> employeeResList = (List<EmployeeRes>) session.getAttribute("uploadEmployeeList");
		if(employeeResList == null){
			em = new ReturnMessage("error", "fail");
			return JsonUtil.formatObject2Json(em);
		}
		List<EmployeeDTO> employeeDTOList = null;
		try{
			employeeDTOList = new ArrayList<EmployeeDTO>();
			for(EmployeeRes employeeRes : employeeResList){
				String empId = employeeRes.getEmpID();
				String emp_CN_name = employeeRes.getEmp_CN_name();
				String emp_EN_name = employeeRes.getEmp_EN_name();
				String empType = employeeRes.getEmpType();
				String engageDate = employeeRes.getEngageDate();
				
				EmployeeDTO employeeDTO = new EmployeeDTO();
				
				employeeDTO.setEmpID(empId);
				employeeDTO.setEmp_CN_name(emp_CN_name);
				employeeDTO.setEmp_EN_name(emp_EN_name);
				employeeDTO.setEmpType(empType);
				employeeDTO.setEngageDate(DateUtil.string2Date(engageDate));
				employeeDTOList.add(employeeDTO);
			}
			int updateLines = employeeService.createUploadEmployees(employeeDTOList);
			if(updateLines == 0){
				em = new ReturnMessage("error", "fail");
				return JsonUtil.formatObject2Json(em);
			}
			em = new ReturnMessage("success", "success");
			return JsonUtil.formatObject2Json(em);
		}catch(Exception e){
			e.printStackTrace();
			em = new ReturnMessage("error", "serverError");
			return JsonUtil.formatObject2Json(em);
		}
	}
	
	/**
	 * 从数据库中查询所有的Employee数据
	 * @return
	 */
	@RequestMapping(value="/findAllEmployee")
	public @ResponseBody String findAllEmployee(){
		List<EmployeeDTO> employeeDTOs = employeeService.findAllEmployee();
		List<EmployeeRes> employeeRess = new ArrayList<EmployeeRes>();
		ReturnMessage em = null;
		for(EmployeeDTO employeeDTO : employeeDTOs){
			employeeRess.add(DtoVoUtil.employeeDTO2EmployeeRes(employeeDTO));
		}
		String jsonData = JsonUtil.formatList2Json(employeeRess, EmployeeRes.class);
		try {
			//return new String(jsonData.getBytes(),"ISO-8859-1");//@ResponseBody内部编码是采用ISO-8859-1编码
			return jsonData;
		} catch (Exception e) {
			e.printStackTrace();
			em = new ReturnMessage("error", "fail");
			return JsonUtil.formatObject2Json(em);
		}
	}
	/**
	 *根据EmpId查找要编辑的Employee信息，之后显示模态框
	 * @param empId
	 * @return
	 */
	@RequestMapping(value="/editEmployee",method=RequestMethod.POST)
	public @ResponseBody String editEmployee(@RequestParam("empId")String empId){
		System.out.println(empId);
		ReturnMessage em = null;
		if(empId == null){
			em = new ReturnMessage("error", "fail");
			return JsonUtil.formatObject2Json(em);
		}
		String jsonData = findEmployeeByEmpId(empId);
		return jsonData;
	}
	/**
	 * 获取要删除的人的信息
	 * @param emp 传入要删除的emp
	 * @return
	 */
	@RequestMapping(value="/deleteEmployee",method=RequestMethod.POST)
	public @ResponseBody String deleteEmployee(@RequestParam(value="empId")String empId){
		System.out.println(empId);
		ReturnMessage em = null;
		if(empId == null){
			em = new ReturnMessage("error", "fail");
			return JsonUtil.formatObject2Json(em);
		}
		String jsonData = findEmployeeByEmpId(empId);
		return jsonData;
	}
	
	/**
	 *  更新employee的信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateEmployeeInfo",method=RequestMethod.POST)
	public @ResponseBody String updateEmployeeInfo(HttpServletRequest request){
		ReturnMessage em = null;
		String empID = request.getParameter("empId");
		String emp_CN_name = request.getParameter("emp_CN_name");
		String emp_EN_name = request.getParameter("emp_EN_name");
		String engageDate = request.getParameter("engageDate");
		String empType = request.getParameter("empType");
		System.out.println("empId="+empID+",emp_CN_name="+emp_CN_name+",emp_EN_name="+emp_EN_name+",engageDate="+engageDate);
		EmployeeDTO employeeDTO = employeeService.findEmployeeByEmpId(empID);
		try{
			//employeeDTO.setEmpID(empID);
			employeeDTO.setEmp_CN_name(emp_CN_name);
			employeeDTO.setEmp_EN_name(emp_EN_name);
			employeeDTO.setEmpType(empType);
			employeeDTO.setEngageDate(DateUtil.string2Date(engageDate));
			int updateLines = employeeService.updateEmployeeInfo(employeeDTO);
			em = new ReturnMessage("success", "success");
			return JsonUtil.formatObject2Json(em);
		}catch(Exception e){
			e.printStackTrace();
			em = new ReturnMessage("error", "serverError");
			return JsonUtil.formatObject2Json(em);
		}
	}
	
	/**
	 * 根据Empid查找信息
	 * @param empId
	 * @return
	 */
	private String findEmployeeByEmpId(String empId){
		ReturnMessage em = null;
		EmployeeDTO employeeDTO = employeeService.findEmployeeByEmpId(empId);
		EmployeeRes employeeRes = DtoVoUtil.employeeDTO2EmployeeRes(employeeDTO);
		String jsonData = JsonUtil.formatObject2Json(employeeRes);
		try{
			//jsonData = new String(jsonData.getBytes(),"ISO-8859-1");
		}catch(Exception e){
			e.printStackTrace();
			em = new ReturnMessage("error", "fail");
			return JsonUtil.formatObject2Json(em);
		}
		System.out.println(jsonData);
		return jsonData;
	}
	
	/**
	 * 删除一个或多个员工信息
	 * @param emp
	 * @return
	 */
	@RequestMapping(value="/confirmDelete",method=RequestMethod.POST)
	public @ResponseBody String confirmDelete(@RequestParam(value="empId")String emp){
		System.out.println(emp);
		ReturnMessage em = null;
		//如果是数组项数大于1的话，那么则中间会有逗号分离,否则只有一个项
		String[] empId = null;
		if(emp.indexOf(",") > -1){//不止一项
			empId = emp.split(",");
		}else{//只有一项
			empId = new String[1];
			empId[0] = emp;
		}
		//执行删除操作，传入一个带删除的员工的数组为参数
		System.out.println(Arrays.toString(empId));
		int updateLines = employeeService.deleteEmployees(empId);
		if(updateLines == 0){
			em = new ReturnMessage("error", "fail");
			return JsonUtil.formatObject2Json(em);
		}
		em = new ReturnMessage("success", "success");
		return JsonUtil.formatObject2Json(em);
	}
	
	/**
	 * 查看员工基本信息
	 * @param empId
	 * @return
	 */
	@RequestMapping(value="/viewEmployee",method=RequestMethod.POST)
	public @ResponseBody String viewEmployee(@RequestParam(value="empId")String empId){
		System.out.println(empId);
		ReturnMessage em = null;
		if(empId == null){
			em = new ReturnMessage("error", "fail");
			return JsonUtil.formatObject2Json(em);
		}
		String jsonData = findEmployeeByEmpId(empId);
		return jsonData;
	}
	
	private String poiManage(String fileType,InputStream is,HttpSession session) throws IOException{
		//用接口定义
		Workbook execlBook = null;
		Sheet sheet = null;
		Row row = null;
		
		//根据fileType判断用哪个类是HSSFWorkBook还是XSSFWorkBook对execl进行处理	
		if("xlsx".equals(fileType)){//2007版本的Execl
			execlBook = new XSSFWorkbook(is);
			sheet =  execlBook.getSheetAt(0);
		}else if("xls".equals(fileType)){//2003和之前的Execl
			execlBook = new HSSFWorkbook(is);
			sheet =  execlBook.getSheetAt(0);
		}else{//execl类型错误，无法处理的execl类型
			return "typeError";
		}
		
		//读取传输过来的Execl文件
		EmployeeRes employeeRes = null;
		List<EmployeeRes> employeeList = new ArrayList<EmployeeRes>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//遍历表格的所有行
		for(int i=0;i<sheet.getPhysicalNumberOfRows();i++){
			row = sheet.getRow(i);//获得一行数据
			//EmpId	Emp_CN_name	Emp_EN_name	EngageDate	EmpType
			if(i == 0){//获得每个标题属性
//				titles[0] = row.getCell(0).getStringCellValue();
//				titles[0] = row.getCell(1).getStringCellValue();
//				titles[0] = row.getCell(2).getStringCellValue();
//				titles[0] = row.getCell(3).getStringCellValue();
//				titles[0] = row.getCell(4).getStringCellValue();
				continue;
			}else{//具体的员工信息
				String empId = row.getCell(0).getStringCellValue();
				String emp_CN_name = row.getCell(1).getStringCellValue();
				String emp_EN_name = row.getCell(2).getStringCellValue();
				String empType = row.getCell(3).getStringCellValue();
				Cell cell4 = row.getCell(4);
				String engageDate = null;
				
				if(cell4.getCellType() == Cell.CELL_TYPE_NUMERIC){    //先判断是数字类型
	                if (HSSFDateUtil.isCellDateFormatted(cell4)) {    //判断是日期类型
	                          Date dt = HSSFDateUtil.getJavaDate(cell4.getNumericCellValue());//获取成DATE类型   
	                          engageDate = sdf.format(dt); 
	                  }
				}
				//String engageDate = row.getCell(4).getRichStringCellValue().toString();
				employeeRes = new EmployeeRes(empId,emp_CN_name,emp_EN_name,empType,engageDate);
				employeeList.add(employeeRes);
			}
		}
		session.setAttribute("uploadEmployeeList", employeeList);//将员工信息写入session中
		String jsonData = JsonUtil.formatList2Json(employeeList,EmployeeRes.class);
		return jsonData;
	}
	public static void main(String[] args) {
		
	}
}
