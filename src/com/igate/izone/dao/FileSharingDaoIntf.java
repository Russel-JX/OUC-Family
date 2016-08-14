package com.igate.izone.dao;

import java.util.List;

import com.igate.izone.dto.FileDTO;
import com.igate.izone.util.PageBean;

public interface FileSharingDaoIntf {

	// 分页查询
	public PageBean fetchByPage(int curPage,int from,int pageSize);
	
	// 模糊查询
	public PageBean fetchFuzzy(String keywords,int curPage,int from,int pageSize);
	
	// 按类别查询所有文件
	public PageBean fetchAllFile(String fileType,int curPage,int from,int pageSize);

	// 查询单个文件
	public FileDTO fetchFile(String fileRealName);

	// 新建文件记录
	public boolean saveFile(FileDTO file);
	
	// 批量新建文件记录
	public boolean saveBatchFile(List<FileDTO> files);

	// 删除单个文件
	public boolean removeFile(int fileID);

	// 修改单个文件（文件标题、文件类型或描述）
	public boolean editfile(String id,String fieldName,String newValue);


}
