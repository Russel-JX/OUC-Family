package com.brilliance.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.DiaryInfoDao;
import com.brilliance.exception.BusinessException;
import com.brilliance.po.DiaryInfo;
import com.brilliance.service.DiaryInfoService;
import com.brilliance.utils.Constants;

import sun.misc.BASE64Decoder;

@Repository
@Transactional(rollbackFor=Exception.class)//propagation=Propagation.REQUIRES_NEW无论何时，此类中的方法都在新的事物中。防止和别的事物为同一个时，一个要回滚，一个要提交，导致异常。
public class DiaryInfoServiceImpl extends BaseService<DiaryInfo> implements
		DiaryInfoService {

	private final String PREFIX_FLAG = "Local:";
	@Resource
	private DiaryInfoDao diaryInfoDao;

	public ServiceReturns addDiaryInfo(DiaryInfo diaryInfo,String appURL) throws Exception {
		serviceReturns = new ServiceReturns();
//		try{
			StringBuilder content = covertHTMLContent(new StringBuilder(diaryInfo.getContent()),Constants.IMG_VIRTUAL_PATH,appURL);
			if(!StringUtils.isEmpty(content)){
				diaryInfo.setContent(content.toString());
			}
			diaryInfoDao.addDiaryInfo(diaryInfo);
			serviceReturns.putData("newDiaryInfo", diaryInfo);
			
			int a = 9/0;
//		}catch(Exception e){
//			throw new Exception();
//			
//		}
		
		
		return serviceReturns;
	}

	/**
	 * 将前台传来的图片的附件保存在本地磁盘，并且
	 * @param htmlText HTML内容文本
	 * @param path  存储在磁盘的相对路径
	 * @param imgAppURL  图片资源的请求服务URL
	 * @return
	 */
	private StringBuilder covertHTMLContent(StringBuilder htmlText, String path,String imgAppURL) {
		String patten = "<img\\s+(?:[^>]*)src\\s*=\\s*[\'\"]([^>]+)";
		//String patten = "<img\\s+(?:[^>]*)src\\s*=\\s*[\'\"]data([^>]+)";
		Pattern myPattern = Pattern.compile(patten, Pattern.CASE_INSENSITIVE
				| Pattern.MULTILINE);
		Matcher matcher = myPattern.matcher(htmlText);
		StringBuilder tmp = null;
		Map<StringBuilder, String> map = new HashMap<StringBuilder, String>();
		String tmpStr = "";
		int pos = 0;
		int pos1 = 0;
		String imgContent = "";
		String srcContent = "";
		while (matcher.find()) {
			tmp = new StringBuilder(102400);
			tmp.append(matcher.group(1));
			//System.out.println("content == "+tmp);

			if (tmp.length() == 0) {
				continue;
			}
			
			if (tmp.toString().startsWith("data:image")) {
				//上传的本地图片的内容
				pos = tmp.indexOf("base64,", 1);
				
				// 判断html文档中是单引号还是双引号
				pos1 = tmp.indexOf("'", 1);
				if (-1 == pos1) {
					pos1 = tmp.indexOf("\"", 1);
				}
				//截取image内容
				imgContent = tmp.substring(pos+("base64,".length()), pos1);
				
				srcContent = tmp.substring(0, pos1);
				
				
				String type = tmp.substring(11, tmp.indexOf(";"));
				String filePath = createImg(imgContent, path, type);
				//System.out.println("file path == " + filePath);
				// key -><img src内容
				// value ->新生成的图片路径(相对路径，相对于server.xml的图片的docbase)
				map.put(new StringBuilder(srcContent), PREFIX_FLAG + filePath);
				tmp.delete(0, tmp.length());
			}else if (tmp.toString().startsWith(imgAppURL)) {
				//对于已经生成过的本地图片（本地图片显示需要加上图片的访问路径）修改后再保存时需要替换为本地的相对址存储
				map.put(new StringBuilder(imgAppURL), PREFIX_FLAG);
				tmp.delete(0, tmp.length());
			} else if (tmp.toString().startsWith(PREFIX_FLAG)){
				pos = tmp.indexOf("\"", 1);
				if (-1 == pos) {
					pos = tmp.indexOf("'", 1);
				}

				srcContent = tmp.substring(0, pos);
				map.put(new StringBuilder(PREFIX_FLAG), imgAppURL);
				tmp.delete(0, tmp.length());
				//htmlContont = htmlContont.replace(PREFIX_FLAG, imgAppURL + path);
			}
			else {
				tmpStr = tmp.toString().split("\\s")[0];// ??
				tmp.delete(0, tmp.length());

				tmp.append(tmpStr);
			}
		}

		return htmlContentURLReplace(map, htmlText);
	}

	// 获取本地图片的src集合
	private StringBuilder htmlContentURLReplace(Map<StringBuilder, String> map,
			StringBuilder html) {
		StringBuilder builder = null;
		if(!CollectionUtils.isEmpty(map)){
			Set<Map.Entry<StringBuilder, String>> set = map.entrySet();
			Iterator<Map.Entry<StringBuilder, String>> itor = set.iterator();
			Map.Entry<StringBuilder, String> entry = null;
			int pos = 0;
			while (itor.hasNext()) {
				entry = itor.next();
				StringBuilder sbd = entry.getKey();
				String value = entry.getValue();

				pos = html.indexOf(sbd.toString());
				builder = html.replace(pos, sbd.length() + pos, value);
			}
		}else{
			builder = new StringBuilder(html);
		}
		return builder;
	}

	private String createImg(String content, String path, String type) {
		BufferedImage image = null;
		OutputStream out = null;
		ByteArrayInputStream bis = null;
		byte[] imageByte;
		String imgConvertName = String.valueOf(System.nanoTime());
		String filePath = "";
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			imageByte = decoder.decodeBuffer(content);
			bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);

			File file = new File(path + Constants.IMG_UPLOADPATH_DIARY
					+ Constants.FILE_SEP);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
			filePath = Constants.IMG_UPLOADPATH_DIARY + Constants.FILE_SEP
					+ imgConvertName + "." + type;
			out = new FileOutputStream(path + filePath);
			ImageIO.write(image, type, out);

			out.flush();
			out.close();

			image.flush();

			bis.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				image.flush();

				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return filePath;
	}

	// 获取当天的日记 - 一个
	public ServiceReturns getDiaryInfoByDate(DiaryInfo diaryInfo,String appImgURL) throws BriException {
		serviceReturns = new ServiceReturns();
		DiaryInfo diary = diaryInfoDao.getDiaryInfoByDate(diaryInfo);
		
		DiaryInfo newDiary = new DiaryInfo();
		
		//拷贝一份是为了防止hibernate session的自动更新表数据
		BeanUtils.copyProperties(diary, newDiary);
		
		if(null != newDiary){
			StringBuilder htmlContent = covertHTMLContent(new StringBuilder(newDiary.getContent()),Constants.IMG_VIRTUAL_PATH,appImgURL);
			newDiary.setContent(htmlContent.toString());
		}
		
		serviceReturns.putData("diary", newDiary);
		return serviceReturns;
	}

	
	/*public String addAppImgURL(String htmlContont,String appImgURL) {
		if (!StringUtils.isEmpty(htmlContont)) {
			// String patten = "<img\\s+(?:[^>]*)src\\s*=\\s*[\'\"]data([^>]+)";
			String patten = "\\s*[\'\"]"+PREFIX_FLAG+"([^>]+)";
			Pattern myPattern = Pattern.compile(patten,
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			StringBuilder html = new StringBuilder(htmlContont);
			Matcher matcher = myPattern.matcher(html);
			StringBuilder tmp = null;
			String path = "";
			int pos = 0;
			while (matcher.find()) {
				tmp = new StringBuilder(Constants.BUFFER_SIZE);
				tmp.append(matcher.group(1));

				// System.out.println("content == "+tmp);

				if (tmp.length() == 0) {
					continue;
				}

				pos = tmp.indexOf("\"", 1);
				if (-1 == pos) {
					pos = tmp.indexOf("'", 1);
				}

				path = tmp.substring(0, pos);
				htmlContont = htmlContont.replace(PREFIX_FLAG + path, appImgURL + path);
			}
		}
		return htmlContont;
	}*/
	
	public ServiceReturns getDiaryList(DiaryInfo diaryInfo,String appImgURL)
			throws BriException, ParseException {
		serviceReturns = new ServiceReturns();
		List<DiaryInfo> list = diaryInfoDao.getDiaryList(diaryInfo);
		List<DiaryInfo> newLst = null;
		if (!CollectionUtils.isEmpty(list)) {
			Iterator<DiaryInfo> itor = list.iterator();
			newLst = new ArrayList<DiaryInfo>();
			StringBuilder htmlContent = new StringBuilder(102400);
			while(itor.hasNext()){
				DiaryInfo diary = itor.next();
				
				DiaryInfo newDiary = new DiaryInfo();
				
				//拷贝一份是为了防止hibernate session的自动更新表数据
				BeanUtils.copyProperties(diary, newDiary);
				
				htmlContent.append(covertHTMLContent(new StringBuilder(newDiary.getContent()),Constants.IMG_VIRTUAL_PATH,appImgURL));
				newDiary.setContent(htmlContent.toString());
				htmlContent.delete(0, htmlContent.length());
				newLst.add(newDiary);
			}
		}
		serviceReturns.putData("lstDiary", newLst);
		return serviceReturns;
	}

	public ServiceReturns deleteDiaryInfo(String[] diaryIds) {
		serviceReturns = new ServiceReturns();
		Integer[] ids = new Integer[diaryIds.length];
		for (int i = 0; i < diaryIds.length; i++) {
			ids[i] = Integer.parseInt(diaryIds[i]);
		}
		int deletedRowNum = diaryInfoDao.deleteDiaryInfo(ids);
		serviceReturns.putData("deletedRowNum", deletedRowNum);
		return serviceReturns;
	}

	public ServiceReturns updateDiaryInfo(DiaryInfo diaryInfo,String appURL) {
		try{
			DiaryInfo originalDiaryInfo = diaryInfoDao.getById2(DiaryInfo.class,
					diaryInfo.getId());
			BeanUtils.copyProperties(originalDiaryInfo, diaryInfo, new String[] {
					"diaryType", "diaryTitle", "source", "author", "diaryDate",
					"content", "lastUpdatedBy", "lastUpdateTime" });
			serviceReturns = new ServiceReturns();
			StringBuilder content = covertHTMLContent(new StringBuilder(diaryInfo.getContent()),Constants.IMG_VIRTUAL_PATH,appURL);
			if(!StringUtils.isEmpty(content)){
				diaryInfo.setContent(content.toString());
			}
			
			diaryInfoDao.updateDiaryInfo(diaryInfo);
			serviceReturns.putData("updatedDiaryInfo", diaryInfo);
		}catch(Exception e){
			throw new BusinessException("修改日记抛出错误2...");
		}
		return serviceReturns;
	}
	
}
