package com.nazir.service.user.impl;

import java.util.UUID;

import com.nazir.service.user.AccessTokenService;

/**
 * @author luogm
 *
 */
public class AccessTokenServiceImpl implements AccessTokenService{

	@Override
	public String creatToken(String loginId) {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
