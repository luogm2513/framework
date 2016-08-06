package com.nazir.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nazir.dao.user.UserDao;
import com.nazir.dao.user.UserLoginDao;
import com.nazir.dao.user.dataobject.UserDO;
import com.nazir.dao.user.dataobject.UserLoginDO;
import com.nazir.service.base.ResponseCode;
import com.nazir.service.base.ResponseDO;
import com.nazir.service.user.AccessTokenService;
import com.nazir.service.user.UserService;
import com.nazir.service.user.dataobject.UserLoginPO;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private AccessTokenService tokenService;

	@Override
	public ResponseDO<UserLoginDO> getUserLoginByLoginId(String loginId) {
		ResponseDO<UserLoginDO> responseDO = new ResponseDO<UserLoginDO>();
		UserLoginDO userLoginDO = userLoginDao.getByLoginId(loginId);
		if(userLoginDO != null) {
			responseDO.setDataResult(userLoginDO);
		} else {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("该用户不存在!");
		}
		return responseDO;
	}

	@Override
	public ResponseDO<UserLoginDO> doLogin(UserLoginPO loginPO) {
		ResponseDO<UserLoginDO> responseDO = new ResponseDO<UserLoginDO>();
		UserDO userDO = userDao.getByLoginId(loginPO.getLoginId());
		if(userDO != null) {
			if(userDO.getPassword() != null && loginPO.getPassword().equals(userDO.getPassword())) {
				UserLoginDO userLoginDO = new UserLoginDO();
				userLoginDO.setLoginId(userDO.getLoginId());
				userLoginDO.setAccessToken(tokenService.creatToken(loginPO.getLoginId()));
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

	@Override
	public ResponseDO<UserDO> getUserByLoginId(String loginId) {
		ResponseDO<UserDO> responseDO = new ResponseDO<UserDO>();
		UserDO userDO = userDao.getByLoginId(loginId);
		if(userDO != null) {
			responseDO.setDataResult(userDO);
		} else {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("查询失败，用户不存在!");
		}
		return responseDO;
	}

}
