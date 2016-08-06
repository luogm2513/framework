package com.nazir.service.user;

import com.nazir.dao.user.dataobject.UserDO;
import com.nazir.dao.user.dataobject.UserLoginDO;
import com.nazir.service.base.ResponseDO;
import com.nazir.service.user.dataobject.UserLoginPO;

public interface UserService {
	
	public ResponseDO<UserDO> getUserByLoginId(String loginId);
	
	public ResponseDO<UserLoginDO> getUserLoginByLoginId(String loginId);

	public ResponseDO<UserLoginDO> doLogin(UserLoginPO userLoginPO);

}
