package com.nazir.service.user.dataobject;

/**
 * @author luogm
 *
 */
public class UserLoginPO {
	
	private Long userId;
    private String loginAccount;
    private String accessToken;
    
    public Long getUserId() {
    	return userId;
    }
    public void setUserId(Long userId) {
    	this.userId = userId;
    }
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
    
}
