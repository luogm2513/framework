package com.nazir.service.common;

import org.springframework.stereotype.Component;

import com.nazir.service.base.ResponseCode;
import com.nazir.service.base.ResponseDO;
import com.nazir.utils.RandomUtil;

/**
 * @author luogm
 *
 */
@Component("messageService")
public class MessageService {

	public ResponseDO<String> sendCheckCode(String mobile){
		ResponseDO<String> response = new ResponseDO<String>();
		String checkCode = RandomUtil.getRandomNumString(6);
		//TODO send Message to mobile
		boolean success = true;
		if(!success) {
			// TODO write log
			response.setCode(ResponseCode.ERROR);
			response.setMessage("发送短信失败，请联系管理员!");
		}
		response.setDataResult(checkCode);
		return response;
	}
	
}
