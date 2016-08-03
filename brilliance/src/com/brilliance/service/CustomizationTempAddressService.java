package com.brilliance.service;

import java.util.List;

import com.brilliance.base.ServiceReturns;
import com.brilliance.po.CustomizationTempAddressInfo;

public interface CustomizationTempAddressService {
	public ServiceReturns save(List<CustomizationTempAddressInfo> custTempAddrInfoList);
}
