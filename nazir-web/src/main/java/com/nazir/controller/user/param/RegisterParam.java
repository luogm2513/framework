package com.nazir.controller.user.param;

import com.nazir.validate.annotation.RequireAnno;

/**
 * @author luogm
 *
 */
public class RegisterParam {
	
	@RequireAnno
	private String mobile;
	@RequireAnno
	private String password;
	@RequireAnno
	private String userName;
	private String email;
	private String avatar;
	
	private String openId; // 第三方登录openId
	private Integer paltType; // 第三方平台类型 1:微信 2:QQ
	
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
}
