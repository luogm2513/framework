package com.nazir.service.user.dataobject;

/**
 * @author luogm
 *
 */
public class RegisterPO {
	private Long userId;
	private String mobile;
	private String password;
	private String userName;
	private String email;
	private String avatar;
	private String openId; // 第三方登录openId
	private Integer paltType; // 第三方平台类型 1:微信 2:QQ
	
	private String accessToken;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getPaltType() {
		return paltType;
	}
	public void setPaltType(Integer paltType) {
		this.paltType = paltType;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
