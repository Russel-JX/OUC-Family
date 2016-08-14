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
import com.igate.izone.dao.QuestionDaoIntf;
import com.igate.izone.dto.FileDTO;
import com.igate.izone.dto.QuestionDTO;
import com.igate.izone.util.PageBean;

@Repository("questionDao")
public class QuestionDaoImpl extends BaseDao implements QuestionDaoIntf{
	
	private static final String sql_query_all = "select id,questionName,answerID,questionDescription,questionFrom,questionTo,status,raiseDate from question where fileType=? and id limit ?,? ";
	private static final String sql_delete_one = "delete from question where id=";
	private static final String sql_query_byPage = "select id,fileName,fileType,uploadedDate,fileFrom,fileDescription,fileRealName from question where id limit ?,? ";
	
	// 查询某个角色下的所有FAQ
	public List<QuestionDTO> fetchAllQuestionByType(int userID){
//		//查询总记录数
//		int totalRowNumber = jdbcTemplate.queryForInt("select count(*) from question where fileType='"+fileType+"'");
//		//总页数
//		int totalPageNumber = (totalRowNumber%pageSize==0)?totalRowNumber/pageSize:totalRowNumber/pageSize+1;
//		
//		Object[] args = new Object[]{fileType,from,pageSize};
//		//int[] types =new int[]{Types.INTEGER,Types.INTEGER,Types.INTEGER};   
//		System.out.println("按类型查询的当前页是："+curPage+" 从哪开始：  "+from+" 查几条： "+pageSize+" 总记录条数是："+totalRowNumber);
//		
//		List<FileDTO> files = jdbcTemplate.query(sql_query_all, args, new RowMapper<FileDTO>(){
//			public FileDTO mapRow(ResultSet rs, int index) throws SQLException {
//				FileDTO file = new FileDTO();
//				file.setId(rs.getInt("id"));
//				file.setFileName(rs.getString("fileName"));
//				file.setFileType(rs.getString("fileType"));
//				file.setUploadedDate(rs.getDate("uploadedDate"));
//				file.setFileFrom(rs.getInt("fileFrom"));
//				file.setFileDescription(rs.getString("fileDescription"));
//				file.setFileRealName(rs.getString("fileRealName"));
//				return file;
//			}
//		});
//		
//		PageBean pageBean = new PageBean();
//		pageBean.setCurrentPage(curPage);
//		pageBean.setPageSize(pageSize);
//		pageBean.setTotalPages(totalPageNumber);
//		pageBean.setTotalRows(totalRowNumber);
//		pageBean.setData(files);
		
		return null;
	}

	// 新建FAQ
	public boolean saveQuestion(QuestionDTO question){
		String sql = "insert into question(questionName,questionDescription,questionFrom,questionTo,status,raiseDate) values(?,?,?,?,?,?)";
		Object[] args = new Object[]{question.getQuestionName(),question.getQuestionDescription(),question.getQuestionFrom(),question.getQuestionTo(),question.getStatus(),question.getRaiseDate()};
		int[] types =new int[]{Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.DATE};   
		System.out.println("问题名："+question.getQuestionName());
		int affectedRowNum = jdbcTemplate.update(sql, args,types);
		if(affectedRowNum>0){
			return true;
		}
		return false;
	}


	// 删除FAQ
	public boolean removeQuestion(int questionID){
		int affectedRowNum = jdbcTemplate.update(sql_delete_one+questionID);
		if(affectedRowNum>0){
			return true;
		}
		return false;
	}




}
