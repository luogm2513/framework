package com.nazir.service.user;

import com.nazir.dao.user.dataobject.UserLoginDO;
import com.nazir.service.base.ResponseDO;

public interface UserLoginService {
	
	public ResponseDO<UserLoginDO> getUserLoginByLoginId(String loginId);

	public ResponseDO<UserLoginDO> doLogin(String loginId, String password);

}
