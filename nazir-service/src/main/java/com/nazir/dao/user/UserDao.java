package com.nazir.dao.user;

import com.nazir.dao.user.dataobject.UserDO;

public interface UserDao {

	public UserDO getById(Long id);
	
	public UserDO getByLoginId(String LoginId);
}
