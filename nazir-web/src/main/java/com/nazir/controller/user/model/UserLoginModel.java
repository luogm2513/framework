package com.nazir.controller.user.model;

import com.nazir.controller.base.AbstractFlagModel;

/**
 * @author luogm
 *
 */
public class UserLoginModel extends AbstractFlagModel {

	
	private String loginId;
    private String accessToken;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
