package com.nazir.service.common;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nazir.dao.user.UserCheckCodeDao;
import com.nazir.service.base.ResponseCode;
import com.nazir.service.base.ResponseDO;
import com.nazir.utils.RandomUtil;

/**
 * @author luogm
 *
 */
@Component("checkCodeService")
public class CheckCodeService {
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserCheckCodeDao userCheckCodeDao;

	public ResponseDO<String> sendCheckCode(String mobile){
		String checkCode = RandomUtil.getRandomNumString(6);
		// 将短信验证码保存在mysql受苦中，后期改进，将验证码保存用redis实现
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mobile", mobile);
		map.put("checkCode", checkCode);
		userCheckCodeDao.insert(map);
		ResponseDO<String> response = messageService.sendCheckCode(mobile);
		if(response != null && !response.isSuccess()) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("发送短信失败，请联系管理员!");
			return response;
		}
		response.setDataResult(checkCode);
		return response;
	}
	
	public ResponseDO<Boolean> checkCode(String mobile, String checkCode){
		// 将短信验证码保存在mysql受苦中，后期改进，将验证码保存用redis实现
		ResponseDO<Boolean> response = new ResponseDO<Boolean>();
		String lastCheckCode = userCheckCodeDao.getLastCheckCode(mobile);
		if(checkCode.equals(lastCheckCode)) {
			response.setDataResult(true);
		} else {
			response.setDataResult(false);
			response.setCode(ResponseCode.ERROR);
			response.setMessage("短信验证码错误！");
		}
		return response;
	}
	
}
