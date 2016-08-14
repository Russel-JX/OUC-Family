package image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.brilliance.utils.Constants;

public class ImageTest {

	private static final String PREFIX_FLAG = "Local:";

	private static final String ss = "http://locaohost:8080/uploadImg";

	public static void main(String[] args) throws IOException {
		File file = new File("e://test.txt");
		StringBuilder s = new StringBuilder(FileUtils.readFileToString(file));
		// addAppURL(FileUtils.readFileToString(file));
		String appURL = "http://localhost:8080/diary";
		System.out.println(covertHTMLContent(s, "e://fdx",appURL));
		//StringBuilder ss = htmlContentURLReplace(map,new StringBuilder(s));
		// outList(list);
		//System.out.println(addAppURL(FileUtils.readFileToString(file)));

	}

	/**
	 * 将前台传来的图片的附件保存在本地磁盘，并且
	 * @param htmlText HTML内容文本
	 * @param path  存储在磁盘的相对路径
	 * @param imgAppURL  图片资源的请求服务URL
	 * @return
	 */
	private static StringBuilder covertHTMLContent(StringBuilder htmlText, String path,String imgAppURL) {
		String patten = "<img\\s+(?:[^>]*)src\\s*=\\s*[\'\"]([^>]+)";
		//String patten = "<img\\s+(?:[^>]*)src\\s*=\\s*[\'\"]data([^>]+)";
		Pattern myPattern = Pattern.compile(patten, Pattern.CASE_INSENSITIVE
				| Pattern.MULTILINE);
		Matcher matcher = myPattern.matcher(htmlText);
		List<StringBuilder> list = new ArrayList<StringBuilder>();
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
			}
			else {
				tmpStr = tmp.toString().split("\\s")[0];// ??
				tmp.delete(0, tmp.length());

				tmp.append(tmpStr);
			}
			list.add(tmp);

			

		}
		return htmlContentURLReplace(map, htmlText);
	}

	// 获取本地图片的src集合
	public static StringBuilder htmlContentURLReplace(
			Map<StringBuilder, String> map, StringBuilder html) {
		Set<Map.Entry<StringBuilder, String>> set = map.entrySet();
		Iterator<Map.Entry<StringBuilder, String>> itor = set.iterator();
		Map.Entry<StringBuilder, String> entry = null;
		StringBuilder builder = null;
		int pos = 0;
		while (itor.hasNext()) {
			entry = itor.next();
			StringBuilder sbd = entry.getKey();
			String value = entry.getValue();

			pos = html.indexOf(sbd.toString());
			builder = html.replace(pos, sbd.length() + pos, value);
		}
		return builder;
	}
	private static String createImg(String content, String path, String type) {
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
	
	
	/*public static String createImg(String content, String path, String type) {
		BufferedImage image = null;
		BufferedImage [] images = null;
		OutputStream out = null;
		InputStream bis = null;
		byte[] imageByte;
		String imgConvertName = String.valueOf(System.nanoTime());
		String filePath = "";
		try {
			//Base64 decoder = new Base64();
			imageByte = Base64.decodeBase64(content);
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
			
			if("gif".equals(type)){
				Iterator<ImageReader> itor1 =  ImageIO.getImageReadersBySuffix("gif");
				if(itor1.hasNext()){
					ImageReader reader = itor1.next();
					ImageInputStream imageIn = ImageIO.createImageInputStream(bis);
					reader.setInput(imageIn,true);
					//int cunt = reader.getNumImages(false);
					int index = 0;
					boolean flag = true;
					while (flag){
						try{
							images[index] = reader.read(index++);
						}catch(IndexOutOfBoundsException bounds){
							flag = false;
						}
						
					}
					/*images = new BufferedImage[count];
					for (int i = 0; i < count; i++){
						images[i] = reader.read(imageIndex)
					}*/
				/*}
				Iterator<ImageWriter> itor =  ImageIO.getImageWritersByFormatName("gif");
				if(itor.hasNext()){
					ImageWriter writer = itor.next();
					writer.setOutput(ImageIO.createImageOutputStream(imageByte));
					writer.write(new IIOImage(images[0],null,null));
					for(int i =0; i < images.length;i++){
						IIOImage iioImage =  new IIOImage(images[i],null,null);
						if(writer.canInsertImage(i)){
							writer.writeInsert(i, iioImage, null);
						}
					}
				}
				
			}else{
				ImageIO.write(image, type, out);

				out.flush();
				out.close();

				image.flush();

				bis.close();
			}
			
			

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
	}*/

	public static String encodeToString(BufferedImage image, String type) {
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, type, bos);
			byte[] imageBytes = bos.toByteArray();

			BASE64Encoder encoder = new BASE64Encoder();
			imageString = encoder.encode(imageBytes);

			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageString;
	}

	private static String addAppURL(String htmlContont) {
		if (!StringUtils.isEmpty(htmlContont)) {
			// String patten = "<img\\s+(?:[^>]*)src\\s*=\\s*[\'\"]data([^>]+)";
			String patten = "\\s*[\'\"]" + PREFIX_FLAG + "([^>]+)";
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
				htmlContont = htmlContont
						.replace(PREFIX_FLAG + path, ss + path);
			}
		}
		return htmlContont;
	}

}
