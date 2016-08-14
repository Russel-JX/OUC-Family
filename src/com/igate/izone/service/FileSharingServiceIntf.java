package com.igate.izone.service;

import java.util.List;

import com.igate.izone.dto.FileDTO;
import com.igate.izone.dto.ReportDTO;
import com.igate.izone.dto.ReportDetailDTO;
import com.igate.izone.util.PageBean;

public interface FileSharingServiceIntf {

	// 分页查询
	public PageBean fetchByPage(String curPage,String pageSize);
	
	// 模糊查询
	public PageBean fetchFuzzy(String keywords,String curPage,String pageSize);
	
	// 按类别查询所有文件
	public PageBean fetchAllFile(String fileType,String curPage,String pageSize);

	// 查询单个文件
	public FileDTO fetchFile(String fileRealName);

	// 新建文件记录
	public boolean saveFile(FileDTO file);
	
	// 批量新建文件记录
	public boolean saveBatchFile(List<FileDTO> files);

	// 删除单个文件
	public boolean removeFile(int fileID);

	// 修改单个文件（文件标题和描述）
	public boolean editfile(String id,String fieldName,String newValue);
}
