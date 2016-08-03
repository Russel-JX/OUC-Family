package com.brilliance.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="favouriteAddress")
@Table(name = "FAVOURITE_ADDRESS")
public class FavouriteAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2394503284229018874L;

	private Integer id;

	private String userId;
	
	private String source;

	private String addressId;

	private Date createTime;

	private Date lastUpdateTime;
	
	private String expressName;
	private String logoUrl;

	private CustomizationAddressInfo customizationAddressInfo;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
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


	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}


	@Transient
	public CustomizationAddressInfo getCustomizationAddressInfo() {
		return customizationAddressInfo;
	}

	public void setCustomizationAddressInfo(
			CustomizationAddressInfo customizationAddressInfo) {
		this.customizationAddressInfo = customizationAddressInfo;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	

	@Transient
	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	
	@Transient
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/*@Transient
	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
*/
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	/*public String getHotName() {
		return hotName;
	}

	public void setHotName(String hotName) {
		this.hotName = hotName;
	}*/

	
}
