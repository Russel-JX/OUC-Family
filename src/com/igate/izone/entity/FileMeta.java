package com.igate.izone.entity;

import java.util.Date;

/**
 * @author Xun Jiang
 * @description 返回上传成功后的文件集集合
 *
 */
public class FileMeta {
	
	private String fileName;//文件唯一名称
    private String fileType;
    private String uploadedDate;
    private int fileFrom;
    private String fileDescription;
    private String fileRealName;//文件真实名称
 
    private byte[] bytes;

    public FileMeta() {
		super();
	}
	
	public FileMeta(String fileName, String fileType, String uploadedDate,
			int fileFrom, String fileDescription, String fileRealName
			) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.uploadedDate = uploadedDate;
		this.fileFrom = fileFrom;
		this.fileDescription = fileDescription;
		this.fileRealName = fileRealName;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getFileRealName() {
		return fileRealName;
	}
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	public String getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(String uploadedDate) {
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

}
