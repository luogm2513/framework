package com.nazir.dao.user;

import com.nazir.dao.user.dataobject.UserLoginDO;
import com.nazir.service.user.dataobject.UserLoginPO;

/**
 * @author luogm
 *
 */
public interface UserLoginDao {
	
	public UserLoginDO getByLoginAccount(String loginId);
	
	public Integer update(UserLoginDO userLoginDO);
	
	public Integer insert(UserLoginPO userLoginPO);
}
