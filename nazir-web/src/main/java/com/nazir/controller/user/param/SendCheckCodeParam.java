package com.nazir.controller.user.param;

import com.nazir.validate.annotation.RequireAnno;

/**
 * @author luogm
 *
 */
public class SendCheckCodeParam {
	
	@RequireAnno
	private String mobile;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
