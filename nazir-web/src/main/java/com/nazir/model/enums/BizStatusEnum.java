package com.nazir.model.enums;

/**
 * @Desc 定义各种业务错误返回状态及描述
 * @author luogm
 *
 */
public enum BizStatusEnum {
	
	REG_SUCCESS(HttpCodeEnum.SUCCESS, "手机注册成功"),
	LOGIN_SUCCESS(HttpCodeEnum.SUCCESS, "用户登陆成功"),
	SYS_SUCCESS(HttpCodeEnum.SUCCESS, "调用成功"),
	
	REG_HAS_EXISTS(HttpCodeEnum.ERROR, "手机号码已被注册"),
	REG_CHECK_CODE_ERROR(HttpCodeEnum.ERROR, "验证码错误,或者已经过期"),
	LOGIN_NOT_ACTIVE(HttpCodeEnum.ERROR, "用户还未激活"),
	LOGIN_NOT_USER(HttpCodeEnum.ERROR, "登录名或密码错误"),
	LOGIN_ERROR_PWD(HttpCodeEnum.ERROR, "登录名或密码错误"),
	LOGIN_ERROR_COUNT(HttpCodeEnum.ERROR, "重复输错密码超过10次,当日不能再次登录!"),
	LOGIN_USER_SCALLKER(HttpCodeEnum.ERROR, "您的账号有异常操作暂时被冻结"),
	LOGIN_USER_FORBID(HttpCodeEnum.ERROR, "用户作废"),
	LOGIN_EXCEPTION(HttpCodeEnum.ERROR, "未知异常"),
	SYS_NOT_PERMISSION(HttpCodeEnum.ERROR, "没有权限token验证失败"),
	SYS_PARAM_ERROR(HttpCodeEnum.ERROR, "参数异常"),
	SYS_OTHER_LOGIN_ERROR(HttpCodeEnum.ERROR, "当前帐号在其他地方登陆"),
	SYS_MAXUP_lOAD_SIZE(HttpCodeEnum.ERROR, "上传超过最大尺寸"),
	
    SYS_EXCEPTION(HttpCodeEnum.EXCEPTION, "系统异常"),
    SYS_STEAM_ERROR(HttpCodeEnum.EXCEPTION, "错误的连接"),
    SYS_REPAIR_SYSTEM(HttpCodeEnum.EXCEPTION, "系统维护中"),
	UNKNOW_EXCEPTION(HttpCodeEnum.EXCEPTION, "未知异常");
    
	private BizStatusEnum(HttpCodeEnum codeEnum, String message) {
		this.codeEnum = codeEnum;
		this.message = message;
	}

	private HttpCodeEnum codeEnum;
	
	private String message;

	public HttpCodeEnum getCodeEnum() {
		return codeEnum;
	}

	public void setCodeEnum(HttpCodeEnum codeEnum) {
		this.codeEnum = codeEnum;
	}

	public String getMessage() {
		return message;
	}
	
}
