package com.nazir.service.user.dataobject;

/**
 * @author luogm
 *
 */
public class UserBehaviorPO {
	
	private Long userId;
	private String loginAccount;
    private Integer behaviorType;
    private String behaviorDesc;
    private String ip;
    private String agent;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public Integer getBehaviorType() {
		return behaviorType;
	}
	public void setBehaviorType(Integer behaviorType) {
		this.behaviorType = behaviorType;
	}
	public String getBehaviorDesc() {
		return behaviorDesc;
	}
	public void setBehaviorDesc(String behaviorDesc) {
		this.behaviorDesc = behaviorDesc;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
    
}
