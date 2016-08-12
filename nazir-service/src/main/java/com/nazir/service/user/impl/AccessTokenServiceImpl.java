package com.nazir.service.user.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.nazir.service.user.AccessTokenService;

/**
 * @author luogm
 *
 */
@Component("tokenService")
public class AccessTokenServiceImpl implements AccessTokenService{

	@Override
	public String creatToken() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
