package com.igate.izone.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.igate.izone.dao.BaseDao;
import com.igate.izone.dao.FileSharingDaoIntf;
import com.igate.izone.dto.FileDTO;
import com.igate.izone.util.PageBean;

@Repository("fileSharingDao")
public class FileSharingDaoImpl extends BaseDao implements FileSharingDaoIntf{
	
	private static final String sql_query_all = "select id,fileName,fileType,uploadedDate,fileFrom,fileDescription,fileRealName from file_sharing where fileType=? and id limit ?,? ";
	private static final String sql_delete_one = "delete from file_sharing where id=";
	//查询总记录数
	//private static final String sql_query_rowNumber = "select count(*) from file_sharing";
	private static final String sql_query_byPage = "select id,fileName,fileType,uploadedDate,fileFrom,fileDescription,fileRealName from file_sharing where id limit ?,? ";
	//private static final String sql_query_fuzzy = "select id,fileName,fileType,uploadedDate,fileFrom,fileDescription,fileRealName from file_sharing where concat(fileRealName,fileType,fileDescription,uploadedDate) like ? and id limit ?,? ";
	
	//private static final String sql_update_one = "UPDATE file_sharing SET fileName=?,fileType=?,fileDescription='ccc',fileRealName='英语' WHERE id=20";
	
	// 分页查询
	public PageBean fetchByPage(int curPage,int from,int pageSize){
		//查询总记录数
		int totalRowNumber = jdbcTemplate.queryForInt("select count(*) from file_sharing where id limit "+from+" ,"+pageSize);
		//总页数
		int totalPageNumber = (totalRowNumber%pageSize==0)?totalRowNumber/pageSize:totalRowNumber/pageSize+1;
		
		Object[] args = new Object[]{from,pageSize};
		//int[] types =new int[]{Types.INTEGER,Types.INTEGER,Types.INTEGER};   
		System.out.println("查询的当前页是："+curPage+" 从哪开始：  "+from+" 查几条： "+pageSize);
		
		List<FileDTO> files = jdbcTemplate.query(sql_query_byPage, args, new RowMapper<FileDTO>(){
			public FileDTO mapRow(ResultSet rs, int index) throws SQLException {
				FileDTO file = new FileDTO();
				file.setId(rs.getInt("id"));
				file.setFileName(rs.getString("fileName"));
				file.setFileType(rs.getString("fileType"));
				file.setUploadedDate(rs.getDate("uploadedDate"));
				file.setFileFrom(rs.getInt("fileFrom"));
				file.setFileDescription(rs.getString("fileDescription"));
				file.setFileRealName(rs.getString("fileRealName"));
				return file;
			}
		});
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(curPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPages(totalPageNumber);
		pageBean.setTotalRows(totalRowNumber);
		pageBean.setData(files);
		
		return pageBean;
	}
	
	// 对文件真实名称、文件类型、文件描述和上传时间字段模糊查询
	public PageBean fetchFuzzy(String keywords,int curPage,int from,int pageSize){
		//查询总记录数
		String sql_query_fuzzyNumber  = "select count(*) from file_sharing where concat(fileRealName,fileType,fileDescription,uploadedDate) like '%"+keywords+"%'";
		System.out.println("模糊查找个数。。。关键字是： "+keywords+" 从哪开始：  "+from+" 查几条： "+pageSize+" sql:"+sql_query_fuzzyNumber);
		int totalRowNumber = jdbcTemplate.queryForInt(sql_query_fuzzyNumber);
		System.out.println("总记录数是： ...."+totalRowNumber);
		
		//总页数
		int totalPageNumber = (totalRowNumber%pageSize==0)?totalRowNumber/pageSize:totalRowNumber/pageSize+1;
		//Object[] args = new Object[]{keywords,from,pageSize};
		
		String sql_query_fuzzy = "select id,fileName,fileType,uploadedDate,fileFrom,fileDescription,fileRealName from file_sharing where concat(fileRealName,fileType,fileDescription,uploadedDate) like '%"+keywords+"%' and id limit "+from+","+pageSize;
		System.out.println("关键字是： "+keywords+" 从哪开始：  "+from+" 查几条： "+pageSize+"模糊查询语句是："+sql_query_fuzzy);
		
		List<FileDTO> files = jdbcTemplate.query(sql_query_fuzzy, new RowMapper<FileDTO>(){
			public FileDTO mapRow(ResultSet rs, int index) throws SQLException {
				FileDTO file = new FileDTO();
				file.setId(rs.getInt("id"));
				file.setFileName(rs.getString("fileName"));
				file.setFileType(rs.getString("fileType"));
				file.setUploadedDate(rs.getDate("uploadedDate"));
				file.setFileFrom(rs.getInt("fileFrom"));
				file.setFileDescription(rs.getString("fileDescription"));
				file.setFileRealName(rs.getString("fileRealName"));
				return file;
			}});
		
		for(FileDTO file:files){
			System.out.println("======="+file.getFileName());
			
		}
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(curPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPages(totalPageNumber);
		pageBean.setTotalRows(totalRowNumber);
		pageBean.setData(files);
		
		return pageBean;
		
		
	}
	
	// 修改单个文件（文件标题、文件类型或描述）
	public boolean editfile(String id,String fieldName,String newValue){
		String sql_update_one = "UPDATE file_sharing SET "+fieldName+"="+"'"+newValue+"'"+" where id="+id;
		System.out.println("----"+sql_update_one);
		int affectedRowNum = jdbcTemplate.update(sql_update_one);
		if(affectedRowNum>0){
			return true;
		}
		return false;
	}
	// 按类别查询所有文件
	public PageBean fetchAllFile(String fileType,int curPage,int from,int pageSize){
		//查询总记录数
		int totalRowNumber = jdbcTemplate.queryForInt("select count(*) from file_sharing where fileType='"+fileType+"'");
		//总页数
		int totalPageNumber = (totalRowNumber%pageSize==0)?totalRowNumber/pageSize:totalRowNumber/pageSize+1;
		
		Object[] args = new Object[]{fileType,from,pageSize};
		//int[] types =new int[]{Types.INTEGER,Types.INTEGER,Types.INTEGER};   
		System.out.println("按类型查询的当前页是："+curPage+" 从哪开始：  "+from+" 查几条： "+pageSize+" 总记录条数是："+totalRowNumber);
		
		List<FileDTO> files = jdbcTemplate.query(sql_query_all, args, new RowMapper<FileDTO>(){
			public FileDTO mapRow(ResultSet rs, int index) throws SQLException {
				FileDTO file = new FileDTO();
				file.setId(rs.getInt("id"));
				file.setFileName(rs.getString("fileName"));
				file.setFileType(rs.getString("fileType"));
				file.setUploadedDate(rs.getDate("uploadedDate"));
				file.setFileFrom(rs.getInt("fileFrom"));
				file.setFileDescription(rs.getString("fileDescription"));
				file.setFileRealName(rs.getString("fileRealName"));
				return file;
			}
		});
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(curPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPages(totalPageNumber);
		pageBean.setTotalRows(totalRowNumber);
		pageBean.setData(files);
		
		return pageBean;
	}

	// 查询单个文件
	public FileDTO fetchFile(String fileRealName){
		return null;
	}

	// 新建文件记录 
	public boolean saveFile(FileDTO file){
		String sql = "insert into file_sharing(fileName,fileType,uploadedDate,fileFrom,fileDescription,fileRealName) values(?,?,?,?,?,?)";
		Object[] args = new Object[]{file.getFileName(),file.getFileType(),file.getUploadedDate(),file.getFileFrom(),file.getFileDescription(),file.getFileRealName()};
		int[] types =new int[]{Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.INTEGER,Types.VARCHAR,Types.VARCHAR};   
		System.out.println("文件名："+file.getFileName());
		int affectedRowNum = jdbcTemplate.update(sql, args,types);
		if(affectedRowNum>0){
			return true;
		}
		return false;
	}

	// 批量新建文件记录
	public boolean saveBatchFile(List<FileDTO> files) {
		String sql = "insert into file_sharing(fileName,fileType,uploadedDate,fileFrom,fileDescription,fileRealName) values(?,?,?,?,?,?)";
		return operateBatchFile(sql,files);
	}
	
	// 批量修改文件记录??
	public boolean editBatchFile(List<FileDTO> files) {
		String sql = "insert into file_sharing(fileName,fileType,uploadedDate,fileFrom,fileDescription,fileRealName) values(?,?,?,?,?,?)";
		return operateBatchFile(sql,files);
	}
	
	// 批量删除文件记录??
	public boolean deleteBatchFile(List<FileDTO> files) {
		String sql = "insert into file_sharing(fileName,fileType,uploadedDate,fileFrom,fileDescription,fileRealName) values(?,?,?,?,?,?)";
		return operateBatchFile(sql,files);
	}
	
	// 批量增、删、改文件记录 
	public boolean operateBatchFile(String sql,List<FileDTO> files){
		final List<FileDTO> tempFiles = files;
		int[] affectedRowNum = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
			public int getBatchSize() {
				return tempFiles.size();
			}
			public void setValues(PreparedStatement ps, int index)throws SQLException {
				ps.setString(1, tempFiles.get(index).getFileName());
				ps.setString(2, tempFiles.get(index).getFileType());
				ps.setDate(3, (new java.sql.Date(tempFiles.get(index).getUploadedDate().getTime())));
				ps.setInt(4, tempFiles.get(index).getFileFrom());
				ps.setString(5, tempFiles.get(index).getFileDescription());
				ps.setString(6, tempFiles.get(index).getFileRealName());
			}
		});
		
		if(affectedRowNum[0]>0){
			return true;
		}
		return false;
	}

	// 删除单个文件
	public boolean removeFile(int fileID){
		int affectedRowNum = jdbcTemplate.update(sql_delete_one+fileID);
		if(affectedRowNum>0){
			return true;
		}
		return false;
	}




}
