package com.igate.izone.test;

import com.igate.izone.dao.impl.ReportDaoImpl;

public class JDBCTest {

	public static void main(String[] args) {
		ReportDaoImpl reportDaoImpl = new ReportDaoImpl();
		reportDaoImpl.fetchAllReport(101);

	}

}
