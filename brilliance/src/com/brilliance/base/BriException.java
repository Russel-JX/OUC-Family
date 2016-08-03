package com.brilliance.base;

public class BriException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6630039721128833321L;

	public BriException(Exception e,String msg)
    {  
        super(msg);  
    }  
}
