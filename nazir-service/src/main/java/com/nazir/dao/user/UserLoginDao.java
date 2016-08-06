package com.nazir.dao.user;

import com.nazir.dao.user.dataobject.UserLoginDO;

/**
 * @author luogm
 *
 */
public interface UserLoginDao {
	
	public UserLoginDO getByLoginId(String loginId);
	
	public Integer update(UserLoginDO userLoginDO);
}
