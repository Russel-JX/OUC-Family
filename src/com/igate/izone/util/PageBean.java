package com.igate.izone.util;

import java.util.List;

import com.igate.izone.dto.FileDTO;

public class PageBean {

	private int totalRows;
	private int pageSize;
	private int currentPage;
	private int totalPages;
	private List<FileDTO> data;
	
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<FileDTO> getData() {
		return data;
	}
	public void setData(List<FileDTO> data) {
		this.data = data;
	}
	
	
}
