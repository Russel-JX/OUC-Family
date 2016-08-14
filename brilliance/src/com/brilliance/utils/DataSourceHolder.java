package com.brilliance.utils;

public class DataSourceHolder {
	public static final String READDATASOURCE = "readDataSource";

	public static final String WRITEDATASOURCE = "writeDataSource";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}
}
