package com.nazir.service.user;

import com.nazir.dao.user.dataobject.UserAccountDO;
import com.nazir.service.base.ResponseDO;
import com.nazir.service.user.dataobject.RegisterPO;

public interface UserAccountService {
	
	public ResponseDO<Long> doRegister(RegisterPO registerPO);
	
	public ResponseDO<UserAccountDO> getUserByMobile(String mobile);
	
}
