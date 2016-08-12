package com.nazir.controller.user.model;

import com.nazir.controller.base.AbstractFlagModel;

/**
 * @author luogm
 *
 */
public class RegisterModel extends AbstractFlagModel {

	private Long userId;
	private String avatar;
	private String accessToken;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
