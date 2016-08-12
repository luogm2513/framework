package com.nazir.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nazir.UserBehaviorTypeEnum;
import com.nazir.dao.user.UserAccountDao;
import com.nazir.dao.user.UserBehaviorDao;
import com.nazir.dao.user.UserLoginDao;
import com.nazir.dao.user.UserOpenAccountDao;
import com.nazir.dao.user.dataobject.UserAccountDO;
import com.nazir.service.base.ResponseCode;
import com.nazir.service.base.ResponseDO;
import com.nazir.service.user.UserAccountService;
import com.nazir.service.user.dataobject.RegisterPO;
import com.nazir.service.user.dataobject.UserBehaviorPO;
import com.nazir.service.user.dataobject.UserLoginPO;
import com.nazir.service.user.dataobject.UserOpenAccountPO;

/**
 * @author luogm
 *
 */
@Component("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountDao userAccountDao;
	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private UserBehaviorDao userBehaviorDao;
	@Autowired
	private UserOpenAccountDao userOpenAccountDao;


	@Override
	public ResponseDO<UserAccountDO> getUserByMobile(String mobile) {
		ResponseDO<UserAccountDO> responseDO = new ResponseDO<UserAccountDO>();
		UserAccountDO userDO = userAccountDao.getByMobile(mobile);
		if(userDO != null) {
			responseDO.setDataResult(userDO);
		} else {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("查询失败，用户不存在!");
		}
		return responseDO;
	}

	@Override
	@Transactional
	public ResponseDO<Long> doRegister(RegisterPO registerPO) {
		ResponseDO<Long> responseDO = new ResponseDO<Long>();
		// 创建用户
		userAccountDao.creatUser(registerPO);
		Long userId = registerPO.getUserId();
		if(userId == null) {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("用户注册异常");
			return responseDO;
		}
		// 增加用户登录数据
		UserLoginPO userLoginPO = new UserLoginPO();
		userLoginPO.setUserId(userId);
		userLoginPO.setLoginAccount(registerPO.getMobile());
		userLoginPO.setAccessToken(registerPO.getAccessToken());
		Integer i = userLoginDao.insert(userLoginPO);
		if(i <= 0) {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("用户注册异常");
			return responseDO;
		}
		// 增加用户行为记录
		UserBehaviorPO registerBehaviorPO = new UserBehaviorPO();
		registerBehaviorPO.setUserId(userId);
		registerBehaviorPO.setLoginAccount(registerPO.getMobile());
		registerBehaviorPO.setBehaviorType(UserBehaviorTypeEnum.USER_REGISTER.getCode());
		registerBehaviorPO.setBehaviorDesc(UserBehaviorTypeEnum.USER_REGISTER.getDesc());
		userBehaviorDao.insert(registerBehaviorPO);
		UserBehaviorPO loginBehaviorPO = new UserBehaviorPO();
		loginBehaviorPO.setUserId(userId);
		loginBehaviorPO.setLoginAccount(registerPO.getMobile());
		loginBehaviorPO.setBehaviorType(UserBehaviorTypeEnum.USER_LOGIN.getCode());
		loginBehaviorPO.setBehaviorDesc("用户首次登录");
		userBehaviorDao.insert(loginBehaviorPO);
		// 增加第三方账户记录
		if(registerPO.getPaltType() != null && registerPO.getOpenId() != null) {
			UserOpenAccountPO userOpenAccountPO = new UserOpenAccountPO();
			userOpenAccountPO.setUserId(userId);
			userOpenAccountPO.setPlatType(registerPO.getPaltType());
			userOpenAccountPO.setOpenId(registerPO.getOpenId());
			userOpenAccountDao.insert(userOpenAccountPO);
		}
		
		responseDO.setDataResult(userId);
		return responseDO;
	}

}
