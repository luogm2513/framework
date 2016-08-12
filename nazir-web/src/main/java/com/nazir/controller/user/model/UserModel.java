package com.nazir.controller.user.model;

import com.nazir.controller.base.AbstractFlagModel;

/**
 * @author luogm
 *
 */
public class UserModel extends AbstractFlagModel {

	UserItem item;

	public UserItem getItem() {
		return item;
	}

	public void setItem(UserItem item) {
		this.item = item;
	}
	
}
