/**
 * 
 */
package com.brilliance.po;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author mx801343 订单表
 */
public class OrderInfo {
	private Integer id;            // ID
	private String userId;         // 用户ID
	private String orderCode;      // 订单ID
	private String expressCode;    // 快递公司代号
	private String operatorId;     // 操作员ID
	private String expressSerialNO;// 快递单号
	private float amount;     // 费用
	private Integer source;            // 来源，1手工录入,2通过系统下单
	private Date createTime;       // 创建时间
	private Integer status;            //DEFAULT '1', 1新增单，2处理中，3配送中，4已收货
	private String createTimeStr;
	private String expressName;
	private Date startDt;
	private Date endDt;
	
	@OneToOne
	@JoinColumn(name="expressCode")
	private ExpressInfo expressInfo;
	
	private String f_contact;      //发货人
	private String from_addr;
	private String f_streetName;
	private String f_companyName;
	private String f_provinceId;
	private String f_cityId;
	private String f_areaId;
	private String f_houseNo;
	private String f_mobileNo;
	private String f_subexpress;
	private String t_contact;
	private String t_companyName;
	private String t_provinceId;
	private String t_cityId;
	private String t_areaId;
	private String t_streetName;
	private String t_houseNo;
	private String to_addr;
	private String t_mobileNo;
	private String t_officeNo;
	private String cargoName;
	private String comment;
	private Integer deliverDays;
	private String startDate;
	private String endDate;
	
	public OrderInfo(){
		
	}
	
	public OrderInfo(Integer id, String userId, String orderCode,
			String expressCode, String expressName, String operatorId,
			String expressSerialNO, float amount, Integer source, Date createTime,
			Integer status) {
		this.id=Integer.valueOf(id);
		this.userId=userId;
		this.orderCode=orderCode;
		this.expressCode=expressCode;
		this.expressName=expressName;
		this.operatorId=operatorId;
		this.expressSerialNO=expressSerialNO;
		this.amount=amount;
		this.source=source;
		this.createTime=createTime;
		this.status=status;
	}
	
	public OrderInfo(String orderCode,String expressCode, String expressName){
		this.orderCode=orderCode;
		this.expressCode=expressCode;
		this.expressName=expressName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getExpressSerialNO() {
		return expressSerialNO;
	}

	public void setExpressSerialNO(String expressSerialNO) {
		this.expressSerialNO = expressSerialNO;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getF_contact() {
		return f_contact;
	}

	public void setF_contact(String f_contact) {
		this.f_contact = f_contact;
	}

	public String getFrom_addr() {
		return from_addr;
	}

	public void setFrom_addr(String from_addr) {
		this.from_addr = from_addr;
	}

	public String getF_streetName() {
		return f_streetName;
	}

	public void setF_streetName(String f_streetName) {
		this.f_streetName = f_streetName;
	}

	public String getF_companyName() {
		return f_companyName;
	}

	public void setF_companyName(String f_companyName) {
		this.f_companyName = f_companyName;
	}

	public String getF_provinceId() {
		return f_provinceId;
	}

	public void setF_provinceId(String f_provinceId) {
		this.f_provinceId = f_provinceId;
	}

	public String getF_cityId() {
		return f_cityId;
	}

	public void setF_cityId(String f_cityId) {
		this.f_cityId = f_cityId;
	}

	public String getF_areaId() {
		return f_areaId;
	}

	public void setF_areaId(String f_areaId) {
		this.f_areaId = f_areaId;
	}

	public String getF_houseNo() {
		return f_houseNo;
	}

	public void setF_houseNo(String f_houseNo) {
		this.f_houseNo = f_houseNo;
	}

	public String getF_mobileNo() {
		return f_mobileNo;
	}

	public void setF_mobileNo(String f_mobileNo) {
		this.f_mobileNo = f_mobileNo;
	}

	public String getF_subexpress() {
		return f_subexpress;
	}

	public void setF_subexpress(String f_subexpress) {
		this.f_subexpress = f_subexpress;
	}

	public String getT_contact() {
		return t_contact;
	}

	public void setT_contact(String t_contact) {
		this.t_contact = t_contact;
	}

	public String getT_companyName() {
		return t_companyName;
	}

	public void setT_companyName(String t_companyName) {
		this.t_companyName = t_companyName;
	}

	public String getT_provinceId() {
		return t_provinceId;
	}

	public void setT_provinceId(String t_provinceId) {
		this.t_provinceId = t_provinceId;
	}

	public String getT_cityId() {
		return t_cityId;
	}

	public void setT_cityId(String t_cityId) {
		this.t_cityId = t_cityId;
	}

	public String getT_areaId() {
		return t_areaId;
	}

	public void setT_areaId(String t_areaId) {
		this.t_areaId = t_areaId;
	}

	public String getT_mobileNo() {
		return t_mobileNo;
	}

	public void setT_mobileNo(String t_mobileNo) {
		this.t_mobileNo = t_mobileNo;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTo_addr() {
		return to_addr;
	}

	public void setTo_addr(String to_addr) {
		this.to_addr = to_addr;
	}

	public String getT_officeNo() {
		return t_officeNo;
	}

	public void setT_officeNo(String t_officeNo) {
		this.t_officeNo = t_officeNo;
	}

	public Integer getDeliverDays() {
		return deliverDays;
	}

	public void setDeliverDays(Integer deliverDays) {
		this.deliverDays = deliverDays;
	}

	public String getT_streetName() {
		return t_streetName;
	}

	public void setT_streetName(String t_streetName) {
		this.t_streetName = t_streetName;
	}

	public String getT_houseNo() {
		return t_houseNo;
	}

	public void setT_houseNo(String t_houseNo) {
		this.t_houseNo = t_houseNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public ExpressInfo getExpressInfo() {
		return expressInfo;
	}

	public void setExpressInfo(ExpressInfo expressInfo) {
		this.expressInfo = expressInfo;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

}
/*
CREATE TABLE `order_info` (
		  `ID` Integer(11) NOT NULL,
		  `USERID` varchar(20) DEFAULT NULL COMMENT '用户id',
		  `OrderCODE` varchar(20) DEFAULT NULL COMMENT '订单id',
		  `Expresscode` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
		  `Operatorid` varchar(20) DEFAULT NULL COMMENT '操作员ID',
		  `Expressserialno` varchar(20) DEFAULT NULL COMMENT '快递单号',
		  `Amount` decimal(20,0) DEFAULT NULL COMMENT '费用',
		  `Source` Integer(11) DEFAULT NULL COMMENT '来源，1手工录入,2通过系统下单',
		  `CREATETIME` timestamp NULL DEFAULT NULL,
		  `STATUS` Integer(11) DEFAULT '1' COMMENT '1新增单，2处理中，3配送中，4已收货',
		  PRIMARY KEY (`ID`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
*/