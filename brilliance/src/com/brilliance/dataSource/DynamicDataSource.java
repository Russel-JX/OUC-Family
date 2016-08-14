package com.brilliance.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.brilliance.utils.DataSourceHolder;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceHolder.getCustomerType();
	}

}
