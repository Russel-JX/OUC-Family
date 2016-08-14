package com.igate.izone.dto;

import java.util.Date;

/**
 * @author Xun Jiang
 * @description 文件记录数据传输对象
 */
public class FileDTO {
	
	private int id;
	private String fileName;
	private String fileType;
	private Date uploadedDate;
	private int fileFrom;
	private String fileDescription;
	private String fileRealName;
	
	public FileDTO() {
		super();
	}
	
	public FileDTO(String fileName, String fileType,
			Date uploadedDate, int fileFrom, String fileDescription,String fileRealName) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileRealName = fileRealName;
		this.uploadedDate = uploadedDate;
		this.fileFrom = fileFrom;
		this.fileDescription = fileDescription;
	}
	
	public FileDTO(int id, String fileName, String fileType,
			Date uploadedDate, int fileFrom, String fileDescription,String fileRealName) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileRealName = fileRealName;
		this.uploadedDate = uploadedDate;
		this.fileFrom = fileFrom;
		this.fileDescription = fileDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public int getFileFrom() {
		return fileFrom;
	}

	public void setFileFrom(int fileFrom) {
		this.fileFrom = fileFrom;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
}
