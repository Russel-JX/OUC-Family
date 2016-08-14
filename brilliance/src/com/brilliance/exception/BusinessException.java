package com.brilliance.exception;


/**
* @ClassName: BusinessException
* @Package com.brilliance.exception
* @Description: 业务异常处理类
* @author Russell Xun Jiang
* @date 2016年4月9日 上午9:00:30
*/
public class BusinessException extends ServiceException {
	
	private static final long serialVersionUID = 7831130804682618671L;

	public BusinessException() {
		super();
	}

	public BusinessException(String errorCode) {
		super(errorCode);
		super.errorCode = errorCode;
	}
	
	public BusinessException(String errorCode,Throwable cause) {
		super(errorCode,cause);
		super.errorCode = errorCode;
	}

}
