package com.brilliance.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.brilliance.base.BaseController;
import com.brilliance.base.BriException;
import com.brilliance.base.ControllerReturns;
import com.brilliance.po.ExpressInfo;
import com.brilliance.service.DataImportService;
import com.brilliance.utils.Constants;
import com.brilliance.utils.GsonSingleton;
import com.brilliance.utils.Tools;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
@Scope("request")
public class DataUploadController extends BaseController {

	@Resource
	private DataImportService dataImportService;

	@RequestMapping(value = "/forwardToAreas")
	public ModelAndView forwardToAreas() {
		ModelAndView view = new ModelAndView("/upload/deliverAreasUpload");
		return view;
	}

	/**
	 * 派送范围数据上传
	 * 
	 * @param fileUpload
	 *            文件名称
	 */
	@RequestMapping(value = "/deliverAreasUpload")
	public ModelAndView deliverAreasUpload(HttpServletRequest request,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		ModelAndView view = new ModelAndView("/upload/deliverAreasUpload");
		String filePath = request.getSession().getServletContext()
				.getRealPath(Constants.TEMP_PATH);
		try {
			File file = copyFileToServer(filePath, fileUpload, view);
			dataImportService.deliverAreasImport(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BriException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.getModel().put("msg", "导入成功！");
		return view;
	}

	@RequestMapping(value = "/forwardToExpressDeliver")
	public ModelAndView forwardToExpressDeliver() {
		ModelAndView view = new ModelAndView("/upload/expressDeliverUpload");
		return view;
	}

	/**
	 * 价格和配送天数数据上传
	 * 
	 * @param fileUpload
	 *            文件名称
	 */
	@RequestMapping(value = "/expressDeliverUpload")
	public ModelAndView expressDeliverUpload(HttpServletRequest request,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		ModelAndView view = new ModelAndView("/upload/expressDeliverUpload");
		String filePath = request.getSession().getServletContext()
				.getRealPath(Constants.TEMP_PATH);
		try {
			File file = copyFileToServer(filePath, fileUpload, view);
			dataImportService.expressDeliverImport(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BriException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		view.getModel().put("msg", "导入成功！");

		return view;
	}

	private File copyFileToServer(String filePath, MultipartFile fileUpload,
			ModelAndView view) throws IOException {
		File file = null;
		/**
		 * 判断是否有文件
		 */
		if (!fileUpload.isEmpty()) {

			String fileName = fileUpload.getOriginalFilename();
			/**
			 * 获取文件后缀
			 */
			String[] suffixs = fileName.split("\\.");
			String suffix = "." + suffixs[suffixs.length - 1];

			/**
			 * 判断上传的文件格式是否正确
			 */
			if ((".xlsx".indexOf(suffix.toLowerCase()) != -1)
					|| (".xls".indexOf(suffix.toLowerCase()) != -1)) {
				byte[] bytes = fileUpload.getBytes();
				Integer fileSize = (int) fileUpload.getSize() / 1024;

				/**
				 * 如果文件小于10M，则上传文件，否则提示用户不能超过10M
				 */
				if (fileSize <= Constants.FILE_MAX_SIZE) {

					File tmp = new File(filePath);
					if (!tmp.exists()) {
						tmp.mkdirs();
					}
					file = new File(filePath + "\\" + fileName);

					/**
					 * 文件开始上传到服务器上
					 */
					FileCopyUtils.copy(bytes, file);

				} else {
					System.out.println("上传的文件太大，文件大小不能超过2M");
				}
			} else {
				System.out.println("上传的文件格式不支持");
			}

		}
		return file;
	}
	
	
	@RequestMapping(value = "/dataUpload/forwardToImportCustAddr")
	public ModelAndView forwardToImportCustAddr() {
		ModelAndView view = new ModelAndView("/upload/importCustAddr");
		return view;
	}
	
	/**
	 * 数据上传 - 数据库导入
	 * 
	 * @param request
	 *            Http请求
	 */
	@RequestMapping(value = "/dataUpload/importFrmDB", produces = "text/html;charset=utf-8")
	public @ResponseBody
	ModelAndView importFrmDB(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/upload/importCustAddr");
		try {
			dataImportService.dbimportToCustAddr();
			view.getModel().put("msg1", "数据库导入成功！");
		} catch (Exception e) {
			e.printStackTrace();
			view.getModel().put("msg1", "数据库导入失败！");
			returns = Tools.getExceptionControllerRetruns(e);
		}
		
		return view;
	}
	
	
	/**
	 * 门店数据上传 - 导入文件
	 * 
	 * @param fileUpload
	 *            文件名称
	 */
	@RequestMapping(value = "/dataUpload/importCustAddr")
	public ModelAndView importCustAddr(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		ModelAndView view = new ModelAndView("/upload/importCustAddr");
		String filePath = request.getSession().getServletContext()
				.getRealPath(Constants.TEMP_PATH);
		try {
			File file = copyFileToServer(filePath, fileUpload, view);
			Map<String, Object> map = dataImportService.importToCustAddress(filePath,file);
			view.getModel().put("msg", map.get("importStatus"));
			
			//导入部分成功，把出错提示Excel放在session中。
			File invalidFile = (File) map.get("invalidFile");
			if(!Tools.isNull(invalidFile)){
				request.getSession().setAttribute("hasInvalidFile", true);
				request.getSession().setAttribute("invalidFile", invalidFile);
			}else{
				request.getSession().setAttribute("hasInvalidFile", false);
				request.getSession().setAttribute("invalidFile", null);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			view.getModel().put("msg", "导入文件失败！");
			returns = Tools.getExceptionControllerRetruns(e);
		} catch (BriException e) {
			e.printStackTrace();
			view.getModel().put("msg", "导入文件失败！");
			returns = Tools.getExceptionControllerRetruns(e);
		}
		
		
		return view;
	}
	
	/**
	 * 门店错误数据下载 - 导入文件
	 * 
	 * @param fileUpload
	 *            文件名称
	 */
	@RequestMapping(value = "/dataUpload/exportInvalidCustAddr")
	public void exportInvalidCustAddr(HttpServletRequest request,HttpServletResponse response) {
		try {
			File invalidFile = (File)request.getSession().getAttribute("invalidFile");
			
			if(!Tools.isNull(invalidFile)){
				response.setHeader("Content-Disposition", "attachment; filename=\"" + invalidFile.getName());  
				response.setContentType("application/vnd.ms-excel; charset=UTF-8");  
				long fileLength = invalidFile.length(); 
				response.setHeader("Content-Length", String.valueOf(fileLength));  
				
				BufferedInputStream bis = null;  
				BufferedOutputStream bos = null;
				
				bis = new BufferedInputStream(new FileInputStream(invalidFile)); 
				bos = new BufferedOutputStream(response.getOutputStream());  
				byte[] buff = new byte[2048];  
				int bytesRead;  
			    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
				      bos.write(buff, 0, bytesRead); 
			    }
			    bis.close();  
			    bos.close();
			    
			    //下载后，删除Excel
			    invalidFile.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
