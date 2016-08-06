package com.nazir.controller.user.param;

import com.nazir.validate.annotation.RequireAnno;

/**
 * @author luogm
 *
 */
public class UserParam {
	
	@RequireAnno
	private String loginId;
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
