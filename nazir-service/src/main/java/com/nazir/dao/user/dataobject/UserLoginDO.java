package com.nazir.dao.user.dataobject;

import java.util.Date;

/**
 * @author luogm
 *
 */
public class UserLoginDO {
	
	private String loginId;
    private String accessToken;
    private Date gmtModified;
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
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
    
}
