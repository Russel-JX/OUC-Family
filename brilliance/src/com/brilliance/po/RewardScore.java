package com.brilliance.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="rewardScore")
@Table(name="REWARD_SCORE")
public class RewardScore implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1060828297405680636L;
	
	private Integer id;
	private String userId;
	private String rewardType;
	private String score;
	private Date creationTime;
	
	private String mobileNo;
	
	
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
	public String getRewardType() {
		return rewardType;
	}
	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	@Transient
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	

}
