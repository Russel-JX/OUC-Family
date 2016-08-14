package com.igate.izone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igate.izone.dao.FileSharingDaoIntf;
import com.igate.izone.dto.FileDTO;
import com.igate.izone.service.FileSharingServiceIntf;
import com.igate.izone.util.PageBean;

@Service
public class FileSharingServiceImpl implements FileSharingServiceIntf{

	@Autowired
	private FileSharingDaoIntf fileSharingDao; 
	
	// 分页查询
	public PageBean fetchByPage(String curPage,String pageSize){
		int currentPage = 0;
		//设置默认当前页（第一次访问null）
		if(curPage==null){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(curPage);
		}
		//查询范围
		int from = (currentPage-1)*Integer.parseInt(pageSize);
		return fileSharingDao.fetchByPage(currentPage, from, Integer.parseInt(pageSize));
	}
	
	// 模糊查询
	public PageBean fetchFuzzy(String keywords,String curPage,String pageSize){
		int currentPage = 0;
		//设置默认当前页（第一次访问null）
		if(curPage==null){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(curPage);
		}
		//查询范围
		int from = (currentPage-1)*Integer.parseInt(pageSize);
		return fileSharingDao.fetchFuzzy(keywords,currentPage,from,Integer.parseInt(pageSize));
	}
	
	// 按类别查询所有文件
	public PageBean fetchAllFile(String fileType,String curPage,String pageSize){
		int currentPage = 0;
		//设置默认当前页（第一次访问null）
		if(curPage==null){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(curPage);
		}
		//查询范围
		int from = (currentPage-1)*Integer.parseInt(pageSize);
		return fileSharingDao.fetchAllFile(fileType, currentPage,from, Integer.parseInt(pageSize));
	}

	// 查询单个文件
	public FileDTO fetchFile(String fileRealName){
		return null;
	}

	// 新建文件记录
	public boolean saveFile(FileDTO file){
		System.out.println("ser  "+file.getFileName());
		return fileSharingDao.saveFile(file);
	}
	
	// 批量新建文件记录
	public boolean saveBatchFile(List<FileDTO> files) {
		return fileSharingDao.saveBatchFile(files);
	}

	// 删除单个文件
	public boolean removeFile(int fileID){
		return fileSharingDao.removeFile(fileID);
	}

	// 修改单个文件（文件标题和描述）
	public boolean editfile(String id,String fieldName,String newValue){
		return fileSharingDao.editfile(id, fieldName, newValue);
	}


}
