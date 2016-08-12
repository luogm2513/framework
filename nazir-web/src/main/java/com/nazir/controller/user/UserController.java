package com.nazir.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nazir.controller.user.model.RegisterModel;
import com.nazir.controller.user.model.UserLoginModel;
import com.nazir.controller.user.model.UserModel;
import com.nazir.controller.user.param.LoginParam;
import com.nazir.controller.user.param.RegisterParam;

/**
 * @Type TestController
 * @Desc 测试
 * @author luogm
 * @date 2016年8月03日
 * @Version V1.0
 */
@Controller
@RequestMapping("/account/user")
public class UserController {
	
    @Autowired
    private UserProcess userProcess;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public UserLoginModel login(@RequestBody LoginParam loginParam) {
    	return userProcess.doLogin(loginParam);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public UserModel getUser(LoginParam loginParam) {
    	return userProcess.getUserAccount(loginParam.getMobile());
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public RegisterModel register(@RequestBody RegisterParam registerParam) {
    	return userProcess.doRegister(registerParam);
    }
}
