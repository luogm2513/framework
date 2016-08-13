package com.nazir.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nazir.controller.base.BaseController;
import com.nazir.controller.base.SimpleFlagModel;
import com.nazir.controller.user.model.RegisterModel;
import com.nazir.controller.user.model.UserItem;
import com.nazir.controller.user.model.UserLoginModel;
import com.nazir.controller.user.model.UserModel;
import com.nazir.controller.user.param.CheckCodeParam;
import com.nazir.controller.user.param.LoginParam;
import com.nazir.controller.user.param.RegisterParam;
import com.nazir.controller.user.param.SendCheckCodeParam;
import com.nazir.dao.user.dataobject.UserAccountDO;
import com.nazir.dao.user.dataobject.UserLoginDO;
import com.nazir.model.enums.HttpCodeEnum;
import com.nazir.service.base.ResponseDO;
import com.nazir.service.common.CheckCodeService;
import com.nazir.service.user.AccessTokenService;
import com.nazir.service.user.UserAccountService;
import com.nazir.service.user.UserLoginService;
import com.nazir.service.user.dataobject.RegisterPO;
import com.nazir.utils.PinYinUtil;

/**
 * @author luogm
 *
 */
@Component
public class UserProcess extends BaseController {
	
	@Autowired
    private UserAccountService userAccountService;
	@Autowired
    private UserLoginService userLoginService;
	@Autowired
	private AccessTokenService tokenService;
	@Autowired
	private CheckCodeService checkCodeService;
	
	public RegisterModel doRegister(RegisterParam registerParam) {
		RegisterModel registerModel = new RegisterModel();
		// 验证手机号码是否已被注册
		if(!checkMobile(registerParam.getMobile())) {
			registerModel.setCode(HttpCodeEnum.ERROR.getCode());
			registerModel.setMessage("该手机号已被注册，请直接登录");
			return registerModel;
		}
		RegisterPO registerPO = new RegisterPO();
		registerPO.setMobile(registerParam.getMobile());
		registerPO.setUserName(registerParam.getUserName());
		registerPO.setPassword(registerParam.getPassword());
		registerPO.setEmail(registerParam.getEmail());
		registerPO.setPaltType(registerParam.getPaltType());
		registerPO.setOpenId(registerParam.getOpenId());
		// 判断头像是否为空，若为空生成姓名首字母的默认头像
		if(registerParam.getAvatar() == null) {
			registerPO.setAvatar("/avatar/"+PinYinUtil.getFirstHeadChar(registerParam.getUserName())+".png");
		} else {
			registerPO.setAvatar(registerParam.getAvatar());
		}
		registerPO.setAccessToken(tokenService.creatToken());
		ResponseDO<Long> responseDO = userAccountService.doRegister(registerPO);
		if(responseDO != null && responseDO.isSuccess()) {
			registerModel.setUserId(responseDO.getDataResult());
			registerModel.setAvatar(registerPO.getAvatar());
			registerModel.setAccessToken(registerPO.getAccessToken());
			return registerModel;
		} else {
			registerModel.setCode(HttpCodeEnum.ERROR.getCode());
			registerModel.setMessage(responseDO.getMessage());
    		return registerModel;
		}
	}
	
	public UserLoginModel doLogin(LoginParam loginParam) {
		UserLoginModel loginModel = new UserLoginModel();
		ResponseDO<UserLoginDO> responseDO = userLoginService.doLogin(loginParam.getMobile(), loginParam.getPassword());
    	if(responseDO != null && responseDO.isSuccess()) {
    		UserLoginDO loginDO = responseDO.getDataResult();
    		if(loginDO != null) {
    			loginModel.setLoginId(loginDO.getLoginAccount());
    			loginModel.setAccessToken(loginDO.getAccessToken());
    		}
    		return loginModel;
    	} else {
    		loginModel.setCode(HttpCodeEnum.ERROR.getCode());
    		loginModel.setMessage(responseDO.getMessage());
    		return loginModel;
    	}
	}
	
	public UserModel getUserAccount(String mobile) {
		UserModel userModel = new UserModel();
		ResponseDO<UserAccountDO> responseDO = userAccountService.getUserByMobile(mobile);
    	if(responseDO != null && responseDO.isSuccess()) {
    		UserAccountDO userDO = responseDO.getDataResult();
    		if(userDO != null) {
    			UserItem item = new UserItem();
    			item.setUserId(userDO.getUserId());
    			item.setLoginAccount(userDO.getMobile());
    			item.setUserName(userDO.getUserName());
    			userModel.setItem(item);
    		}
    		return userModel;
    	} else {
    		userModel.setCode(HttpCodeEnum.ERROR.getCode());
    		userModel.setMessage(responseDO.getMessage());
    		return userModel;
    	}
	}
	
	public SimpleFlagModel sendCheckCode(SendCheckCodeParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		ResponseDO<String> responseDO = checkCodeService.sendCheckCode(param.getMobile());
		if(responseDO != null && !responseDO.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(responseDO.getMessage());
		}
		return model;
	}
	
	public SimpleFlagModel checkCode(CheckCodeParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		ResponseDO<Boolean> responseDO = checkCodeService.checkCode(param.getMobile(), param.getCheckCode());
		if(responseDO != null && !responseDO.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(responseDO.getMessage());
		}
		return model;
	}
	
	/**
	 * 验证手机是否已经被注册
	 * @return
	 */
	private boolean checkMobile(String mobile) {
		ResponseDO<UserAccountDO> responseDO = userAccountService.getUserByMobile(mobile);
		if(responseDO != null && responseDO.isSuccess()) {
    		return false;
    	} else {
    		return true;
    	}
	}
}
