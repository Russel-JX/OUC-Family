package com.igate.izone.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao {
	
	@Autowired 
    protected DataSource dataSource;
	
	@Autowired 
    protected JdbcTemplate jdbcTemplate;
	
	//日志记录对象
	public static Logger log; 

	@SuppressWarnings("rawtypes")
	public static Logger getLog(Class cclass) {
		log = Logger.getLogger(cclass);
		return log;
	}

}
