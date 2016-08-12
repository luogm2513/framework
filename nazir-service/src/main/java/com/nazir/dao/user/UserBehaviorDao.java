package com.nazir.dao.user;

import com.nazir.dao.user.dataobject.UserBehaviorDO;
import com.nazir.service.user.dataobject.UserBehaviorPO;

/**
 * @author luogm
 *
 */
public interface UserBehaviorDao {
	
	public UserBehaviorDO getByUserId(Long userId);
	
	public UserBehaviorDO getByLoginId(String loginId);
	
	public Integer insert(UserBehaviorPO userBehaviorPO);
}
