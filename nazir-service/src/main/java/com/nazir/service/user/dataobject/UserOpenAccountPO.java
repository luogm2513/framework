package com.nazir.service.user.dataobject;

/**
 * @author luogm
 *
 */
public class UserOpenAccountPO {
	
	private Long userId;
	private String openId;
    private Integer platType;
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getPlatType() {
		return platType;
	}
	public void setPlatType(Integer platType) {
		this.platType = platType;
	}
    
}
