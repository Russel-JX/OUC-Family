package com.igate.izone.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.igate.izone.dto.FileDTO;
import com.igate.izone.entity.FileMeta;
import com.igate.izone.service.FileSharingServiceIntf;
import com.igate.izone.util.DateUtil;
import com.igate.izone.util.PageBean;

@Controller("fileSharingController")
@Scope("prototype")
/**
 * 由于客户端的文件共享可以被客户完全控制，所以必须在服务器端再进行验证****
 * 
 */
public class FileSharingControllerImpl{

	@Resource
	public FileSharingServiceIntf fileSharingService;
	
	//上传成功后的文件集合
	LinkedList<FileMeta> uploaded_files = new LinkedList<FileMeta>();
    FileMeta fileMeta = null;
    List<FileDTO> filesDTO = new ArrayList<FileDTO>();
    
    List<FileDTO> filesDTO2 = new ArrayList<FileDTO>();
    
    PageBean pageBean = new PageBean();
	
//    //分页查询
//	@RequestMapping(value="/searchByPage.do",method=RequestMethod.POST)
//  	public @ResponseBody PageBean searchFileByPage(HttpServletRequest request) throws IllegalStateException, IOException {
//  		
//  		String fileType = request.getParameter("file_type");
//  		String keywords = request.getParameter("key_words");
//  		String curPage = request.getParameter("cur_page");
//  		String pageSize = request.getParameter("page_size");
//  		System.out.println("文件类型是： "+fileType+" 关键字是： "+keywords+" 当前页是： "+curPage+" 页面大小是: "+pageSize);
//  		
//  		pageBean = fileSharingService.fetchByPage(curPage, pageSize);
//  		
//  		//关键字为空，只按照文件类型查询；否则按照关键字，进行模糊查询。
//  		return pageBean;
//  	}
    
    //**************查询某个类型的文件**************
  	@RequestMapping(value="/search.do",method=RequestMethod.POST)
  	public @ResponseBody PageBean searchFile(HttpServletRequest request) throws IllegalStateException, IOException {
  		
  		String fileType = request.getParameter("file_type");
  		String keywords = request.getParameter("key_words");
  		String curPage = request.getParameter("cur_page");
  		String pageSize = request.getParameter("page_size");
  		System.out.println("文件类型是： "+fileType+" 关键字是： "+keywords+" 当前页是： "+curPage+" 页面大小是: "+pageSize);
  		
  		//关键字为空，只按照文件类型查询；否则按照关键字，进行模糊查询。
  		if(keywords.equalsIgnoreCase("")||keywords==null){
  			pageBean = fileSharingService.fetchAllFile(fileType,curPage,pageSize);
  		}else{
  			pageBean = fileSharingService.fetchFuzzy(keywords,curPage,pageSize);
  		}
  		return pageBean;
  	}
    
//    //查询某个类型的文件
//  	@RequestMapping(value="/fetch.do",method=RequestMethod.POST)
//  	public @ResponseBody List<FileDTO> fetchFile(HttpServletRequest request) throws IllegalStateException, IOException {
//  		
//  		String fileType = request.getParameter("file_type");
//  		filesDTO2 = fileSharingService.fetchAllFile(fileType);
//  		
//  		return filesDTO2;
//  	}
    
//  	//模糊查询文件
//  	@RequestMapping(value="/fuzzy.do",method=RequestMethod.POST)
//  	public @ResponseBody List<FileDTO> fetchFuzzy(HttpServletRequest request) throws IllegalStateException, IOException {
//  		
//  		String keywords = request.getParameter("keywords");
//  		filesDTO2 = fileSharingService.fetchFuzzy(keywords, curPage, pageSize);
//  		
//  		return filesDTO2;
//  	}
  	
	//上传要共享的文件
	@RequestMapping(value="/upload.do",method=RequestMethod.POST)
	public @ResponseBody LinkedList<FileMeta> uploadFile(MultipartHttpServletRequest request,HttpServletRequest req) throws IllegalStateException, IOException {
		
		/*//获取当前的session
		HttpSession session = req.getSession();
		//当前的用户
		session.getAttribute("emp");*/
		
		
		//**********IE9中，上传报错；谷歌没有
		String file_loaction = req.getSession().getServletContext().getRealPath("/WEB-INF/file_location.properties");  
		//System.out.println("文件位置是：             "+file_loaction);
		//MultipartFile file = request.getFile("file");
		List<MultipartFile> files = request.getFiles("file");
		System.out.println("。。。。。。。。。。。文件的数量是："+files.size()+"...是否为empty:"+files.isEmpty()+"...."+files.get(0).getOriginalFilename());
		//选择的文件类型
		String file_upload_type = request.getParameter("file_upload_type");
		//文件描述
		String file_description = request.getParameter("file_description");
		System.out.println("用户选择的文件类型是:"+file_upload_type+" 文件描述是： "+file_description);
		
		Iterator<MultipartFile> ite = files.iterator();
		MultipartFile file_item ;
		while(ite.hasNext()){
			file_item = ite.next();
			
			//文件真实名称
			String fileRealName = file_item.getOriginalFilename();
			//重新定义文件的唯一的文件名
			String fileName = String.valueOf(System.nanoTime());
			System.out.println("文件大小是：             "+file_item.getSize()+" 文件类型是："+file_item.getContentType()+"  文件名是："+fileRealName+"  文件是否为空："+file_item.isEmpty());
			//获取图片后缀名
			String suffix = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
			fileName += suffix; 
			//文件将要保存的位置
			String filePath = req.getSession().getServletContext().getRealPath("/uploadedFile/"+file_upload_type);
			//文件的完整路径
			String fileFullPath = filePath+"\\"+ fileName;
			//复制文件到服务器中
		    file_item.transferTo(new File(fileFullPath));
			
			//将文件记录记入数据库
			FileDTO fileDTO = new FileDTO();
			fileDTO.setFileName(fileName);
			fileDTO.setFileType(file_upload_type);
			fileDTO.setUploadedDate(new Date());
			fileDTO.setFileFrom(5);//从session中获取此人的id
			fileDTO.setFileDescription(file_description);
			fileDTO.setFileRealName(fileRealName);
			
			System.out.println("con  "+fileDTO.getFileName()+"用户所选的文件类型："+fileDTO.getFileType());
			
			filesDTO.add(fileDTO);
			
			//util.Date转string日期("yyyy-MM-dd")
			String uploadedDate = DateUtil.date2String(new java.sql.Date(fileDTO.getUploadedDate().getTime()));
			
			//返回文件集合(文件唯一名称,文件类型，上传日期，上传者，文件描述，文件真实名称)
	        fileMeta = new FileMeta(fileDTO.getFileName(),fileDTO.getFileType(),uploadedDate,fileDTO.getFileFrom(),fileDTO.getFileDescription(),fileDTO.getFileRealName());
	        uploaded_files.add(fileMeta);
		}
		System.out.println("file number is :"+filesDTO.size());
		fileSharingService.saveBatchFile(filesDTO);
		
		// 转型为MultipartHttpRequest：   
        //MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)req;   
//        MultipartResolver resolver = new CommonsMultipartResolver(req.getSession().getServletContext());
//        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(req);
        // 获得文件：   
        //MultipartFile file = multipartRequest.getFile("files");  
        
		
		Properties properties = new Properties();  
	       try  
	       {  
	           InputStream inputStream = new FileInputStream(file_loaction);  
	           properties.load(inputStream);  
	           inputStream.close(); //关闭流  
	       }  
	       catch (IOException e)  
	       {  
	           e.printStackTrace();  
	       }  
	    String doc_location = properties.getProperty("doc_location");  
	    
		
        System.out.println("文档的存储路径是：        "+doc_location); 
        
        
		return uploaded_files;
	}
	
	//文件下载
	@RequestMapping(value="/download.do",method=RequestMethod.GET)
	public void downloadFile(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//响应内容类型
		response.setContentType("multipart/form-data");    
		//响应头。设置以附件形式下载，文件名。
        response.setHeader("Content-Disposition", "attachment;fileName="+request.getParameter("fileRealName"));
        
        //选择的文件类型
      	String fileType = request.getParameter("fileType");
      	//文件名
      	String fileName = request.getParameter("fileName");
        //文件的位置
		String filePath = request.getSession().getServletContext().getRealPath("/uploadedFile/"+fileType);
		//文件的完整路径
		String fileFullPath = filePath+"\\"+ fileName;
        File file=new File(fileFullPath);    
        System.out.println(file.getAbsolutePath());    
        InputStream inputStream=new FileInputStream(file);    
        OutputStream os=response.getOutputStream();    
        byte[] b=new byte[1024];    
        int length;    
        while((length=inputStream.read(b))>0){    
            os.write(b,0,length);    
        }    
        inputStream.close();  
	}
	
	//文件修改
	@RequestMapping(value="/edit.do",method=RequestMethod.POST)
	public @ResponseBody boolean editFile(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//修改的文件id
      	String id = request.getParameter("pk");
        //修改的字段名
      	String fieldName = request.getParameter("name");
      	//修改的新值
      	String newValue = request.getParameter("value");
      	System.out.println("文件ID是： "+id+" 文件字段是：  "+fieldName+" 新值是：  "+newValue);
      	
      	return fileSharingService.editfile(id, fieldName, newValue);
	}
	
	//删除单个文件
	@RequestMapping(value="/remove.do",method=RequestMethod.POST)
	public @ResponseBody boolean removeFile(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//要删除的文件id
      	int id = (Integer.parseInt(request.getParameter("id")));
      	System.out.println("文件ID是： "+id);
      	
      	return fileSharingService.removeFile(id);
	}


}
