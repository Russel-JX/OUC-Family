package com.brilliance.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.brilliance.po.CustomizationAddressInfo;

public class DataTransferUtils {

	
	
	/**
	 * 将结果集转成CustomizationAddressInfo集合
	 * @param list
	 * @return
	 * @throws ParseException 
	 */
	public static List<CustomizationAddressInfo> convertData(List<?> list) throws ParseException{
		List<CustomizationAddressInfo> lstAddr = new ArrayList<CustomizationAddressInfo>();
		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			while(itor.hasNext()){
				Object[] obj = (Object[])itor.next();
				CustomizationAddressInfo addrInfo = new CustomizationAddressInfo();
				addrInfo.setId(Integer.parseInt(obj[0].toString()));
				
				addrInfo.setParentId(String.valueOf(obj[1]));
				addrInfo.setDataType(obj[2].toString());
				addrInfo.setArchiveFlag(obj[3].toString());
				
				addrInfo.setExpressCode(String.valueOf(obj[4]));
//				addrInfo.setExpressName(String.valueOf(obj[5]));
				addrInfo.setHotName(String.valueOf(obj[5]));
				addrInfo.setContactName(String.valueOf(obj[6]));
				addrInfo.setMobile(String.valueOf(obj[7]));
				addrInfo.setOfficeNo(String.valueOf(obj[8]));
				addrInfo.setAddressId(String.valueOf(obj[9]));
				addrInfo.setMemo(String.valueOf(obj[10]));
				addrInfo.setCreateTime(Tools.parseToDate(String.valueOf(obj[11]), Constants.DATE_PATTEN_SEC));
				addrInfo.setAddressDetail(String.valueOf(obj[12]));
				addrInfo.setSource(obj[13].toString());
				addrInfo.setCreatedBy(String.valueOf(obj[14]));
				addrInfo.setProvinceId(String.valueOf(obj[15]));
				addrInfo.setCityId(String.valueOf(obj[16]));
				addrInfo.setAreaId(String.valueOf(obj[17]));
				addrInfo.setTailAddress(String.valueOf(obj[18]));
				addrInfo.setLongitude(String.valueOf(obj[19]));
				addrInfo.setLatitude(String.valueOf(obj[20]));
				
				lstAddr.add(addrInfo);
			}
		}
		return lstAddr;
	}
}
