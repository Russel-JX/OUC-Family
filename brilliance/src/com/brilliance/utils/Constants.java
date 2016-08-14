package com.brilliance.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Constants {
	
	public static final Properties PROPERTIES = System.getProperties();
	
	//session有效状态
	public static final String SESSION_STATUS = "OK";
	
	//路径系统分隔符
	public static final String FILE_SEP = PROPERTIES.getProperty("file.separator");
	
	//分享链接过期时间(天)
	public static int URL_EXPIRE_TIME = 2;
	public static String LOCALE = "zh_CN";
	public static final String FORWARD_500_PATH="common/500";
	public static final String FORWARD_404_PATH="common/404";
	public static final String FORWARD_LOGIN_PATH="common/login";
	
	public static final String MESSAGE_TYPE_INFO = "info";
	public static final String MESSAGE_TYPE_WARNING = "warning";
	public static final String MESSAGE_TYPE_ERROR = "error";
	
	public static final String ERR_COMMON_SYS_ERROR = "10000";
	public static final String ERR_COMMON_DB_ERROR = "10001";
	public static final String ERR_COMMON_BAD_ACCESS = "10002";
	public static final String ERR_COMMON_DB_NO_RESULTS = "10003";
	public static final String ERR_COMMON_DB_NOT_EXISTS = "10004";
	public static final String ERR_COMMON_DB_REFERENCED = "10005";
	public static final String ERR_NOT_LOGIN = "10010";
	
	public static final int BUFFER_SIZE = 102400;
	
	//登录提示
	public static final String ERR_COMMON_BAD_ACCESS_USERNAME = "10007";
	public static final String ERR_COMMON_BAD_ACCESS_PASSWORD = "10008";
	
	//订单管理
	public static final String ERR_ORDER_NOT_EXISTS = "20000";
	//评价管理
	public static final String ERR_EVALUTION_EXISTS = "30000";
	public static final String ERR_ORDER_NOT_BELONGS = "30001";
	public static final String ERR_EVALUTION_SUCCESS = "30002";
	
	
	public static final int ONLE_SELF = 1;
	public static final int NON_SELF = 2;
	public static final int ALL = 3;
	
	//baiduMap 开发key
	public static final String BAIMAP_AK = "vQKsPsU4CmEfIeBfM13F4RrD";//百度地图ak
	
	//my favourite address source constant
	//新增
	public static final String FAV_ADD = "1";
	//收藏
	public static final String FAV_COL = "2";
	
	//空格
	public static final String BLANK = " " ;
	
	
	//用户状态-激活
	public static final int ACTIVE = 1;
	//未激活
	public static final int INACTIVE = 0;
	
	//订单类型
	public static final int ORDER_SOURCE_MANUAL = 1;
	public static final int ORDER_SOURCE_SYS = 2;
	
	//订单状态-新订单
	public static final int ORDER_STATUS_NEW = 1;
	//订单状态-处理中
	public static final int ORDER_STATUS_PROCESS = 2;
	//订单状态-配送中
	public static final int ORDER_STATUS_DELIVER = 3;
	//订单状态-已结束
	public static final int ORDER_STATUS_DONE = 4;
	
	public static final String DATE_PATTEN = "yyyy-MM-dd";
	public static final String DATE_PATTEN_SEC = "yyyy-MM-dd HH:mm:ss";
	
	//手机端每页显示条数
	public static final int PAGE_COUNT = 20;
	//分隔符(空格)
	public static final String SEPARATOR_BLANK=" ";
	
	//手机端新增
	public static final String SOURCE_MOBILE = "1";
	//电脑端新增
	public static final String SOURCE_PC = "2";
	//数据库表导入
	public static final String SOURCE_DBIMPORT = "3";
	//数据文件导入
	public static final String SOURCE_FILE_IMPORT = "4";
	//常用快递数据已归档
	public static final String ARCHIVED = "1";
	//常用快递数据未归档(未归档数据将不显示在手机端)
	public static final String UNARCHIVED = "2";
	//常用快递数据类型-门店地址
	public static final String DATATYPE_STORE = "1";
	
	//新用户注册积分
	public static final String REWARD_TYPE_NEW_USER = "1";
	//扫描快递积分
	public static final String REWARD_TYPE_SCAN = "2";
	//评价快递员积分
	public static final String REWARD_TYPE_COMMENTS = "3";
	//提交地址积分
	public static final String REWARD_SUBMIT_ADDRESS = "4";
	
	
	//默认积分值
	public static final String REWARD_DEFAULT_SCORE = "10";
	
	//常用快递数据类型-配送范围 
	public static final String DATATYPE_REGION = "2";
	//常用快递数据类型-配送范围地址
	//文件上传的大小限制
	public static final int FILE_MAX_SIZE = 5120;
	
	/*Excel快递数据导入提示*/
	//Excel的sheet name不正确
	public static final String WRONG_SHEET_NAME = " - 快递公司不存在！";
	//数据导入完全成功
	public static final String IMPORT_SUCCESS = "导入文件全部成功！";
	//数据导入部分成功
	public static final String IMPORT_PARTLLYSUCCESS = "导入的Excel文件中，部分数据有误！请点击此处下载查看。";
	//单元格省份不正确
	public static final String WRONG_PROVINCE_NAME = "省份信息不存在";
	//单元格市不正确
	public static final String WRONG_CITY_NAME = "城市信息不存在";
	//单元格区县不正确
	public static final String WRONG_AREA_NAME = "区/县信息不存在";
	//详细地址无法定位经纬度
	public static final String WRONG_DETAIL_ADDRESS = "公司地址无法定位";
	//快递关键字重复
	public static final String WRONG_HOTNAME = "关键字重复"; 
	
	public static final int DECIMAL_PLACE_ONE = 1;
	
	
	//审核类型
	public static final String REVIEW_TYPE_PASS = "PASS"; 
	public static final String REVIEW_TYPE_REJECT = "REJECT"; 
	//快递信息审核状态
	//未开始
	public static final String REVIEW_UNSTART = "0"; 
	//通过
	public static final String REVIEW_PASS = "2"; 
	//拒绝
	public static final String REVIEW_REJECT = "1";
	
	//用户推荐的地址是否激活
	public static final String ADDRESS_ACTIVE = "1"; 
	
	public static final String ADDRESS_INACTIVE = "0"; 
	
	
	public static final String DELIVER_COMPLETED = "4";
	
	/*
	 * 给与用户积分
	 */
	//推荐附近快递信息通过
	public static final int SHARE_ADDRESS_SUCCESS = 5; 
	//推荐附近快递信息拒绝
	public static final int SHARE_ADDRESS_REJECT = 2; 
	
	/*
	 * 是否删除
	 */
	public static final String DELETED = "0";
	public static final String UNDELETED = "1";
	/*
	 * 戴宗日记类型
	 */
	//日记类型
	public static final String DIARY_DIARY = "0";
	//新闻类型
	public static final String DIARY_NEWS = "1";
	//漫画类型
	public static final String DIARY_COMIC = "2";
	//其他类型
	public static final String DIARY_OTHER = "3";
	
	/*
	 * 图片存放相对路径
	 */
	//tomcat中文件资源路径
	public static final String IMG_VIRTUAL_PATH = "F:"+FILE_SEP+"FILESERVER";
	
	//系统上传的图片的虚拟目录
	public static final String DOC_BASE_PATH = "/uploadImg";
		
		
	//快递logo图片
	public static final String IMG_UPLOADPATH_EXPRESS = FILE_SEP+"express";
	//戴宗日记图片存放路径 
	public static final String IMG_UPLOADPATH_DIARY = FILE_SEP+"diary";
	
	//amin的内置账号
	public static final String ADMIN_ID = "101";
	
	public static final String ADMIN = "Admin";
	
	public static final String LASTRECORD = "last";
	public static final String NEXTRECORD = "next";
	
	//删除快递员
	public static final String 	NORMALDELETE = "normalDelete";
	public static final String FORCEDELETE = "forceDelete";
	
	//默认的配置天数值(如果没填值)
	public static final int DELIVER_DAYS = 3;
	
	//用户Id起始值
	public static final String DEFAULT_ID = "200001";
	
	//上传文件的临时目录
	public static final String TEMP_PATH = "upload";
	
	public static final String [] PROVINCES_ARY = {"北京","天津","河北省","山西省","内蒙古自治区","辽宁省","吉林省","黑龙江省","上海","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","广西壮族自治区","海南省","重庆","四川省","贵州省","云南省","西藏自治区","陕西省","甘肃省","青海省","宁夏回族自治区","新疆维吾尔自治区","香港特别行政区","澳门特别行政区"};
	
	public static final Map<String,String> PROVINCE_MAP = new HashMap<String,String>();
	
	public static final Map<Object,String> order_STATUS = new HashMap<Object,String>();
	
	static{
		PROVINCE_MAP.put("北京","110000");
		PROVINCE_MAP.put("天津","120000");
		PROVINCE_MAP.put("上海","310000");
		PROVINCE_MAP.put("重庆","500000");
		PROVINCE_MAP.put("河北省","130000");
		PROVINCE_MAP.put("山西省","140000");
		PROVINCE_MAP.put("辽宁省","210000");
		PROVINCE_MAP.put("吉林省","220000");
		PROVINCE_MAP.put("江苏省","320000");
		PROVINCE_MAP.put("浙江省","330000");
		PROVINCE_MAP.put("安徽省","340000");
		PROVINCE_MAP.put("福建省","350000");
		PROVINCE_MAP.put("江西省","360000");
		PROVINCE_MAP.put("山东省","370000");
		PROVINCE_MAP.put("河南省","410000");
		PROVINCE_MAP.put("湖北省","420000");
		PROVINCE_MAP.put("湖南省","430000");
		PROVINCE_MAP.put("广东省","440000");
		PROVINCE_MAP.put("海南省","460000");
		PROVINCE_MAP.put("四川省","510000");
		PROVINCE_MAP.put("贵州省","520000");
		PROVINCE_MAP.put("云南省","530000");
		PROVINCE_MAP.put("陕西省","610000");
		PROVINCE_MAP.put("甘肃省","620000");
		PROVINCE_MAP.put("青海省","630000");
		PROVINCE_MAP.put("台湾省","710000");
		PROVINCE_MAP.put("西藏自治区","540000");
		PROVINCE_MAP.put("黑龙江省","230000");
		PROVINCE_MAP.put("广西壮族自治区","450000");
		PROVINCE_MAP.put("宁夏回族自治区","640000");
		PROVINCE_MAP.put("新疆维吾尔自治区","650000");
		PROVINCE_MAP.put("内蒙古自治区","150000");
		PROVINCE_MAP.put("香港特别行政区","810000");
		PROVINCE_MAP.put("澳门特别行政区","820000");
		
		
		order_STATUS.put(Integer.valueOf(ORDER_STATUS_NEW), "新增单");
		order_STATUS.put(Integer.valueOf(ORDER_STATUS_PROCESS), "处理中");
		order_STATUS.put(Integer.valueOf(ORDER_STATUS_DELIVER), "配送中");
		order_STATUS.put(Integer.valueOf(ORDER_STATUS_DONE), "已收货");
	}
	
	
	
}
