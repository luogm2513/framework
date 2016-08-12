package com.nazir.controller.user.param;

import com.nazir.validate.annotation.RequireAnno;

/**
 * @author luogm
 *
 */
public class LoginParam {
	
	@RequireAnno
	private String mobile;
	
	@RequireAnno
	private String password;

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
}
