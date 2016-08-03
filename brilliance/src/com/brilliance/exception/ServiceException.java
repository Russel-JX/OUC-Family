package com.brilliance.exception;

import java.io.Serializable;


/**
* @ClassName: ServiceException
* @Package com.brilliance.exception
* @Description: service异常处理类
* @author Russell Xun Jiang
* @date 2016年4月9日 上午9:00:05
*/
public class ServiceException extends RuntimeException implements Serializable{
	
	private static final long serialVersionUID = 5485143132546102897L;
	
	protected String errorCode;
	
	
	public ServiceException() {
		super();
	}

	public ServiceException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	public ServiceException(String errorCode,Throwable cause) {
		super(errorCode,cause);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	

}
