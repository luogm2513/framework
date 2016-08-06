package com.nazir.controller.user.model;

import com.nazir.controller.base.AbstractFlagModel;

/**
 * @author luogm
 *
 */
public class UserModel extends AbstractFlagModel {

	private Long userId;
	private String loginId;
    private String name;
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
