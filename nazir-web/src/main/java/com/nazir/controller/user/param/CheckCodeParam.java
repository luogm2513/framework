package com.nazir.controller.user.param;

import com.nazir.validate.annotation.RequireAnno;

/**
 * @author luogm
 *
 */
public class CheckCodeParam {
	
	@RequireAnno
	private String mobile;
	
	@RequireAnno
	private String checkCode;
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

}
