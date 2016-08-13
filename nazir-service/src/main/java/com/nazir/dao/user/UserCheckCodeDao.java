package com.nazir.dao.user;

import java.util.HashMap;

/**
 * @author luogm
 *
 */
public interface UserCheckCodeDao {
	
	public Integer insert(HashMap<String, String> map);
	
	public String getLastCheckCode(String mobile);
}
