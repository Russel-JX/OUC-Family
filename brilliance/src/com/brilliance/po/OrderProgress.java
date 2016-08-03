package com.brilliance.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="orderProgress")
@Table(name="ORDER_PROGRESS")
public class OrderProgress implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7594725757696250225L;
	private Integer id;
	private String userId;
	private String expressNo;
	private String expressCode;
	private String source;
	private String latestStatusInfo;
	private Date creationDate;
	private String imageUrl;
	
	private String expressName;
	private String postId;
	
	//private ExpressInfo expressInfo;
	
	@Id 
	@GeneratedValue(strategy=IDENTITY)
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
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/*@OneToOne
	@JoinColumn(name="expressCode")
	@Transient
	public ExpressInfo getExpressInfo() {
		return expressInfo;
	}
	public void setExpressInfo(ExpressInfo expressInfo) {
		this.expressInfo = expressInfo;
	}*/
	
	@Transient
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	
	@Transient
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	@Transient
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getLatestStatusInfo() {
		return latestStatusInfo;
	}
	public void setLatestStatusInfo(String latestStatusInfo) {
		this.latestStatusInfo = latestStatusInfo;
	}
	
}
