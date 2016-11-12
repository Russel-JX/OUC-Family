package com.brilliance.controller;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brilliance.base.BaseController;
import com.brilliance.base.BriException;
import com.brilliance.exception.ServiceException;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.DiaryInfo;
import com.brilliance.service.DiaryInfoService;
import com.brilliance.utils.CacheUtils;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

/**
* @ClassName: DiaryInfoController
* @Package com.brilliance.controller
* @Description:日记模块控制层
* @author Russell Xun Jiang
* @date 2016年4月7日 上午10:20:32
*/
@Controller
@Scope("request")
public class DiaryInfoController extends BaseController{
	//日志
	Logger log = Logger.getLogger(BaseController.class);

	@Resource
	private DiaryInfoService diaryInfoService;
	
	@RequestMapping(value = "/DiaryInfo/forwardToSearchDiary", produces = "text/html;charset=utf-8")
	public String forwardToEditDiary(HttpServletRequest request){
		return "diary/searchDiary";
	}
	
	@RequestMapping(value = "/DiaryInfo/forwardToNewDiary", produces = "text/html;charset=utf-8")
	public String forwardToNewDiary(HttpServletRequest request){
		return "diary/newDiary";
	}
	
	/** 
	* @Title: getDiaryList 
	* @Description: 查询日志 
	* @param @param request
	* @param @return    json 
	* @return String    返回类型 
	* @throws 
	*/ 
	@RequestMapping(value = "/DiaryInfo/getDiaryList", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String getDiaryList(HttpServletRequest request) {
		log.info("\n------查询日记 开始-----");
		try {
			DiaryInfo diaryInfo = new DiaryInfo();
			String diaryTitle = request.getParameter("diaryTitle");
			String author = request.getParameter("author");
			String source = request.getParameter("source");
			String diaryType = request.getParameter("diaryType");
			String diaryDate = request.getParameter("diaryDate");
			String diaryStartDate = request.getParameter("diaryStartDate");
			String diaryEndDate = request.getParameter("diaryEndDate");
			
			diaryInfo.setDiaryTitle(diaryTitle);
			diaryInfo.setAuthor(author);
			diaryInfo.setSource(source);
			diaryInfo.setDiaryType(diaryType);
			diaryInfo.setDiaryDate(Tools.parseToDate(diaryDate, Constants.DATE_PATTEN));
			diaryInfo.setDiaryStartDate(Tools.parseToDate(diaryStartDate, Constants.DATE_PATTEN));
			diaryInfo.setDiaryEndDate(Tools.parseToDate(diaryEndDate, Constants.DATE_PATTEN));
			String appImgURL = CacheUtils.getAppImgPath(request);
			returns = diaryInfoService.getDiaryList(diaryInfo,appImgURL);
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("\n######查询日记列表出错："+e.getMessage()+"######");
			returns = Tools.getErrorsRetrunsMsg("40003");
		}
		log.info("\n------查询日记 结束-----");
		return returns.generateJsonData();
	}
	
	/**
	 * 新增日记
	 * 不在catch中抛出运行时异常时，在非try,catch中发生的异常spring事物注解默认也会捕获，并回滚
	 * @throws Exception 
	 */
	@RequestMapping(value = "/DiaryInfo/saveDiary",method=RequestMethod.POST, produces = "text/html;charset=utf-8")
//	@Transactional(rollbackFor=Exception.class)//发生任何异常都回滚
	public @ResponseBody String saveDiary(HttpServletRequest request) throws Exception{
		log.info("\n------新增日记 开始-----");
		AdminInfo adminInfo = CacheUtils.getAdmInfo(request);
		DiaryInfo diaryInfo = new DiaryInfo();
		String diaryType = request.getParameter("diaryType");
		String diaryTitle = request.getParameter("diaryTitle");
		String source = request.getParameter("source");
		String author = request.getParameter("author");
		String diaryDate = request.getParameter("diaryDate");
		String content = request.getParameter("content");
		
		diaryInfo.setDiaryType(diaryType);
		diaryInfo.setDiaryTitle(diaryTitle);
		diaryInfo.setSource(source);
		diaryInfo.setAuthor(author);
		try {
			diaryInfo.setDiaryDate(Tools.parseToDate(diaryDate, Constants.DATE_PATTEN));
			diaryInfo.setContent(content);
			diaryInfo.setCreatedBy(adminInfo.getAccountId());
			diaryInfo.setCreateTime(Tools.getTime());
			diaryInfo.setDeleteFlag(Constants.UNDELETED);
			
			returns = diaryInfoService.addDiaryInfo(diaryInfo,CacheUtils.getAppImgPath(request));
//			int ii =9/0;
		} catch (Exception e) {
			
			e.printStackTrace();
			log.error("\n######新增日记出错："+e.getMessage()+"######");
			returns = Tools.getErrorsRetrunsMsg("40000");
			throw new Exception();
			
		}
		log.info("\n------新增日记 结束-----");
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/DiaryInfo/modifyDiary",method=RequestMethod.POST, produces = "text/html;charset=utf-8")
	@Transactional
	public @ResponseBody String modifyDiary(HttpServletRequest request){
		log.info("\n------修改日记 开始-----");
		AdminInfo adminInfo = CacheUtils.getAdmInfo(request);
		
		DiaryInfo diaryInfo = new DiaryInfo();
		String id = request.getParameter("id");
		String diaryType = request.getParameter("diaryType");
		String diaryTitle = request.getParameter("diaryTitle");
		String source = request.getParameter("source");
		String author = request.getParameter("author");
		String diaryDate = request.getParameter("diaryDate");
		String content = request.getParameter("content");
		
		diaryInfo.setId(Integer.parseInt(id));
		diaryInfo.setDiaryType(diaryType);
		diaryInfo.setDiaryTitle(diaryTitle);
		diaryInfo.setSource(source);
		diaryInfo.setAuthor(author);
		try {
			diaryInfo.setDiaryDate(Tools.parseToDate(diaryDate, Constants.DATE_PATTEN));
			diaryInfo.setContent(content);
			diaryInfo.setLastUpdatedBy(adminInfo.getAccountId());
			diaryInfo.setLastUpdateTime(Tools.getTime());
			
			returns = diaryInfoService.updateDiaryInfo(diaryInfo,CacheUtils.getAppImgPath(request));
		} catch (Exception e) {
			log.error("\n######修改日记出错："+e.getMessage()+"######");
			//返回错误消息到前台
			returns = Tools.getErrorsRetrunsMsg("40001");
			//使用spring事物时，必须要在catch里抛出运行时异常或子类异常（或者直接不用try、catch语句，系统会自动抛出运行时异常，spring会捕捉运行时异常并回滚），否则事物不回滚！
			//异步请求主流程不抛出异常，返回前台json相应错误结果
			////throw new ServiceException("修改日记抛出错误...",e);
		}
		log.info("\n------修改日记 结束-----");
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/DiaryInfo/deleteDiaryInfo", produces = "text/html;charset=utf-8")
	@Transactional
	public 	@ResponseBody
	String deleteDiaryInfo(HttpServletRequest request) {
		log.info("\n------删除日记 开始-----");
		try {
			String ids = request.getParameter("ids");
			String[] idArray = null;
			if(!StringUtils.isEmpty(ids)){
				idArray = StringUtils.split(ids,",");
				returns = diaryInfoService.deleteDiaryInfo(idArray);
			}
//			int aa=9/0;
		} catch (Exception e) {
			log.info("\n######删除日记出错："+e.getMessage()+"######");
			returns = Tools.getErrorsRetrunsMsg("40002");
		}
		log.info("\n------删除日记 结束-----");
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/DiaryInfo/previewDiary",method=RequestMethod.POST, produces = "text/html;charset=utf-8")
	public String previewDiary(HttpServletRequest request,HttpServletResponse response){
		System.out.println("日记的内容是："+request.getParameter("content"));
		request.setAttribute("diaryType", request.getParameter("diaryDetailType"));
		request.setAttribute("diaryTypeName", request.getParameter("diaryTypeName"));
		request.setAttribute("diaryTitle", request.getParameter("diaryDetailTitle"));
		request.setAttribute("source", request.getParameter("diaryDetailSource"));
		request.setAttribute("author", request.getParameter("diaryDetailAuthor"));
		request.setAttribute("diaryDate", request.getParameter("diaryDetailDate"));
		request.setAttribute("content", request.getParameter("content"));
		
		
		return "diary/previewDiary";
	}
	
	@RequestMapping(value = "/DiaryInfo/getDiaryByDate", method=RequestMethod.POST,produces = "text/html;charset=utf-8")
	public 	@ResponseBody
	String getDiaryByDate(HttpServletRequest request) {
		try { 
			String dateStr = request.getParameter("date");
			//String appImgURL = request.getParameter("appImgURL");
			Date date = Tools.parseToDate(dateStr);
			DiaryInfo diary = new DiaryInfo();
			diary.setDiaryDate(date);
			//diary.setId(Integer.parseInt(id));
			String appImgURL = CacheUtils.getAppImgPath(request);
			returns = diaryInfoService.getDiaryInfoByDate(diary,appImgURL);
			
		} catch (Exception e) {
			log.error("\n######按日期查询日记出错："+e.getMessage()+"######");
			returns = Tools.getErrorsRetrunsMsg("40004");
		}
		return returns.generateJsonData();
	}
	
	
	@RequestMapping(value = "/diaryInfo/imageUpload",produces = "text/html;charset=utf-8")
	public @ResponseBody String imageUpload(HttpServletRequest request) {
		String data = request.getParameter("data");
		System.out.println("data == "+data);
		return returns.generateJsonData();
	}
	
	//截取所有img标签
	public static String[] getImgSrcFromElems(HttpServletRequest request,String content){
		
		
		for(int i=0;i<3;i++){
			
		}
		
		return null;
	}
	
	//save data in form of string to image.
	/*public static BufferedImage decodeToImage(HttpServletRequest request,String imageString)
	{
	    BufferedImage image = null;
	    byte[] imageByte;
	    try
	    {
	        BASE64Decoder decoder = new BASE64Decoder();
	        imageByte = decoder.decodeBuffer(imageString);
	        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
	        image = ImageIO.read(bis);
	        
	        String imgFilePath = "e://222.jpg";//新生成的图片  
	        OutputStream out = new FileOutputStream(imgFilePath);     
		    ImageIO.write(image, "JPEG", out);
		    
		    //项目在server中的绝对路径
		    String filePath = request.getSession().getServletContext().getRealPath("/");
			try {
				//suffix
				String fileOrignalName = fileUpload.getOriginalFilename();
				String suffix = fileOrignalName.substring(fileOrignalName.lastIndexOf("."),fileOrignalName.length());
				//unique File name
				String fileName = String.valueOf(System.nanoTime())+suffix;
				
				System.out.println("---getContextPath---"+request.getSession().getServletContext().getContextPath()+"------filePath="+filePath+"---fileOrignalName"+fileOrignalName+"---fileName"+fileName);
				
				//�����ļ�
				File file = new File(filePath+"\\"+fileName);
				byte[] fileBytes;
				fileBytes = fileUpload.getBytes();
				FileCopyUtils.copy(fileBytes, file);
				
				ExpressInfo info = new ExpressInfo();
				info.setId(Integer.parseInt(expressId));
				info.setLogoUrl("/images/logo/"+fileName);
	        
	        bis.close();
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    return image;
	}*/
	
}
