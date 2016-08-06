package com.nazir.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nazir.controller.base.BaseController;
import com.nazir.controller.user.model.UserLoginModel;
import com.nazir.controller.user.model.UserModel;
import com.nazir.controller.user.param.LoginParam;
import com.nazir.controller.user.param.UserParam;
import com.nazir.dao.user.dataobject.UserDO;
import com.nazir.dao.user.dataobject.UserLoginDO;
import com.nazir.model.enums.HttpCodeEnum;
import com.nazir.service.base.ResponseDO;
import com.nazir.service.user.UserService;
import com.nazir.service.user.dataobject.UserLoginPO;

/**
 * @author luogm
 *
 */
@Component
public class UserProcess extends BaseController {
	
	@Autowired
    private UserService userService;
	
	public UserLoginModel doLogin(LoginParam loginParam) {
		UserLoginModel loginModel = new UserLoginModel();
		UserLoginPO userLoginPO = new UserLoginPO();
		userLoginPO.setLoginId(loginParam.getLoginId());
		userLoginPO.setPassword(loginParam.getPassword());
		ResponseDO<UserLoginDO> responseDO = userService.doLogin(userLoginPO);
    	if(responseDO != null && responseDO.isSuccess()) {
    		UserLoginDO loginDO = responseDO.getDataResult();
    		if(loginDO != null) {
    			loginModel.setLoginId(loginDO.getLoginId());
    			loginModel.setAccessToken(loginDO.getAccessToken());
    		}
    		return loginModel;
    	} else {
    		loginModel.setCode(HttpCodeEnum.ERROR.getCode());
    		loginModel.setMessage(responseDO.getMessage());
    		return loginModel;
    	}
	}
	
	public UserModel getUser(UserParam userParam) {
		UserModel userModel = new UserModel();
		ResponseDO<UserDO> responseDO = userService.getUserByLoginId(userParam.getLoginId());
    	if(responseDO != null && responseDO.isSuccess()) {
    		UserDO userDO = responseDO.getDataResult();
    		if(userDO != null) {
    			userModel.setUserId(userDO.getUserId());
    			userModel.setLoginId(userDO.getLoginId());
    			userModel.setName(userDO.getName());
    		}
    		return userModel;
    	} else {
    		userModel.setCode(HttpCodeEnum.ERROR.getCode());
    		userModel.setMessage(responseDO.getMessage());
    		return userModel;
    	}
	}
}
