package com.nazir.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nazir.dao.user.UserAccountDao;
import com.nazir.dao.user.UserLoginDao;
import com.nazir.dao.user.dataobject.UserAccountDO;
import com.nazir.dao.user.dataobject.UserLoginDO;
import com.nazir.service.base.ResponseCode;
import com.nazir.service.base.ResponseDO;
import com.nazir.service.user.AccessTokenService;
import com.nazir.service.user.UserLoginService;

/**
 * @author luogm
 *
 */
@Component("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserAccountDao userDao;
	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private AccessTokenService tokenService;

	@Override
	public ResponseDO<UserLoginDO> getUserLoginByLoginId(String loginId) {
		ResponseDO<UserLoginDO> responseDO = new ResponseDO<UserLoginDO>();
		UserLoginDO userLoginDO = userLoginDao.getByLoginAccount(loginId);
		if(userLoginDO != null) {
			responseDO.setDataResult(userLoginDO);
		} else {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("该用户不存在!");
		}
		return responseDO;
	}

	@Override
	@Transactional
	public ResponseDO<UserLoginDO> doLogin(String mobile, String password) {
		ResponseDO<UserLoginDO> responseDO = new ResponseDO<UserLoginDO>();
		UserAccountDO userDO = userDao.getByMobile(mobile);
		if(userDO != null) {
			if(userDO.getPassword() != null && password.equals(userDO.getPassword())) {
				UserLoginDO userLoginDO = new UserLoginDO();
				userLoginDO.setUserId(userDO.getUserId());
				userLoginDO.setAccessToken(tokenService.creatToken());
				userLoginDao.update(userLoginDO);
				responseDO.setDataResult(userLoginDO);
			} else {
				responseDO.setCode(ResponseCode.ERROR);
				responseDO.setMessage("登录失败，密码错误!");
			}
		} else {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("登录失败，用户不存在!");
		}
		return responseDO;
	}

}
