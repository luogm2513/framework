/**
 * 
 */
package com.nazir;

/**
 * @author luogm
 *
 */
public enum UserBehaviorTypeEnum {
	
	USER_REGISTER(0, "用户注册"),
	USER_LOGIN(1, "用户登录"),
	CHANGE_PASSWORD(2, "修改密码");
	
	private UserBehaviorTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private Integer code;

	private String desc;

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
