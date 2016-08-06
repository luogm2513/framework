package com.nazir.controller.base;

/**
 * @author luogm
 *
 */
public abstract class AbstractFlagModel {
	
    public static String SUCCESS = "200";
    
    public static String BIZ_SUCCESS = "0";

    /**
     * 系统接口状态编码,包括权限,异常等异常提示
     */
    private String code = SUCCESS;
    
    /**
     * 业务标识符，用于业务行为的标识
     */
    private String flag = BIZ_SUCCESS;

    /**
     * 输出信息
     */
    private String message = "";
    
    /**
     * 成功标识
     * 
     * @return
     */
    public boolean successCode() {
    	return SUCCESS.equals(getCode());
    }
    
    /**
     * 成功标识
     * 
     * @return
     */
    public boolean bizSuccessCode() {
    	return BIZ_SUCCESS.equals(getFlag());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

