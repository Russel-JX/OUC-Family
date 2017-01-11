package webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.brilliance.service.CustomizationTempAddressService;


/**
* @ClassName: HttpUrlConnection
* @Package webservice
* @Description: HttpClient和HttpUrlConnection方式请求服务
* @author Russell Xun Jiang
* @date 2016年12月18日 上午9:55:40
* HttpClient
	HttpClient封装了访问http的请求头，参数，内容体，响应等等，对Java提供方法的一些封装。需要commons-httpclient.jar包和commons-codec.jar包。
  HttpURLConnection
	HttpURLConnection是java的标准类原始的，什么都没封装。所以只要jdk就行。
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springMVC.xml")//配置上下文context，用于加载Spring配置文件
public class HttpClientAndHttpUrlConnection{
	Log logger = LogFactory.getLog(HttpClientAndHttpUrlConnection.class);
	@Resource
	private CustomizationTempAddressService customizationTempAddressService;
	//聚合数据key
	public static final String KEY = "54f63743c855563987cde82ce288c9a7";
	
	/**
	 * @throws UnsupportedEncodingException  
	* @Title: getWeatherByHttpClient 
	* @Description: HttpClient方式请求服务
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	@Test
	public void getWeatherByHttpClient() throws UnsupportedEncodingException{
		String cityName = URLEncoder.encode("苏州市","UTF-8");//HttpClient请求参数包含中文要转码
		String urlStr = "http://v.juhe.cn/weather/index?cityname="+cityName+"&key="+KEY;//api来自“聚合数据”  jx_russell
		HttpClient httpClient = new HttpClient();
		GetMethod  method = new GetMethod(urlStr);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3,false));//请求设置参数
		String responseString;
		try {
			int statusCode = httpClient.executeMethod(method);
			if(statusCode==HttpStatus.SC_OK){//200
				responseString = method.getResponseBodyAsString();
				logger.info("HttpClient响应结果是："+responseString);
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/** 
	* @Title: getWeatherByHttpUrlConnection 
	* @Description: HttpUrlConnection方式请求服务
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	@Test
	public void getWeatherByHttpUrlConnection(){
		String cityName = "苏州市";
		String urlStr = "http://v.juhe.cn/weather/index?cityname="+cityName+"&key="+KEY;
		System.out.println("URL是："+urlStr);
		URL url;
		InputStream urlStream = null;
		BufferedReader bufferedReader = null;
		HttpURLConnection httpConn = null;
		String sTotalString = "";
		try {
			url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod(HttpMethod.GET.toString());//请求方法默认为get（不写此句也行）
			int responseCode = httpConn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				urlStream = httpConn.getInputStream();
				bufferedReader = new BufferedReader(
						new InputStreamReader(urlStream));
				String sCurrentLine = "";
				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					sTotalString += sCurrentLine;
				}
				logger.info("HttpUrlConnection响应结果是："+sTotalString);
			}else{
				logger.error("连接聚合数据——获取天气响应 失败");
			}
		} catch(ConnectException e){
			logger.error("连接聚合数据——获取天气连接 失败");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpConn.disconnect();
			if(urlStream !=null){
				try {
					urlStream.close();
				} catch (IOException e) {
					logger.error("关闭输入流失败");
					e.printStackTrace();
				}
			}
			if(bufferedReader!=null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error("关闭bufferedReader失败");
					e.printStackTrace();
				}
			}
		}
	}
}
