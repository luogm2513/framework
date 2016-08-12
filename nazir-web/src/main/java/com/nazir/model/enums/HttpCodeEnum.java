/**
 * 
 */
package com.nazir.model.enums;

/**
 * @Desc httpCode
 * @author luogm
 *
 */
public enum HttpCodeEnum {
	
	SUCCESS("200", "请求成功"),
	ERROR("400", "错误的请求"),
	EXCEPTION("500", "服务器异常");
	
	private HttpCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;

	private String desc;

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
