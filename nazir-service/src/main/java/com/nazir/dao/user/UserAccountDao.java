package com.nazir.dao.user;

import com.nazir.dao.user.dataobject.UserAccountDO;
import com.nazir.service.user.dataobject.RegisterPO;

public interface UserAccountDao {

	public UserAccountDO getById(Long userId);
	
	public UserAccountDO getByMobile(String mobile);
	
	public Long creatUser(RegisterPO registerPO);
}
