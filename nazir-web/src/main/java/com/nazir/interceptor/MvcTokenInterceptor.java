package com.nazir.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nazir.dao.user.dataobject.UserLoginDO;
import com.nazir.exception.CertificateException;
import com.nazir.reqinfo.ReqHead;
import com.nazir.reqinfo.ReqParam;
import com.nazir.service.base.ResponseDO;
import com.nazir.service.user.UserService;
import com.nazir.utils.IPUtils;
import com.nazir.utils.UrlRightConstant;

/**
 * @Type MvcTokenInterceptor
 * @Desc MVCToken拦截器
 * @author luogm
 * @date 2016-8-3
 * @Version V1.0
 */
public class MvcTokenInterceptor implements HandlerInterceptor {

    private final String ACCESS_TOKEN = "access_token";
    private final String USER_LOGIN_ID = "user_login_id";
    
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg) throws Exception {
        request.setAttribute("req_time", new Date());
        // 设置request属性,先设置属性，以免日志没打印
        String url = request.getRequestURI();

        ReqHead reqHead = new ReqHead();
        reqHead.setAccessToken(request.getHeader(ACCESS_TOKEN));
        reqHead.setUserLoginId(request.getHeader(USER_LOGIN_ID));

        ReqParam reqParam = new ReqParam();
        reqParam.setReqHead(reqHead);
        reqParam.setIp(IPUtils.getIp(request));
        reqParam.setUrl(url);

        request.setAttribute("ReqParam", reqParam);

        // 忽略登陆
        for (String ignoreLoginUrl : UrlRightConstant.notVerifyUrlList) {
            if (url.indexOf(ignoreLoginUrl) != -1) {
                return true;
            }
        }

        // 安全认证
        checkAccessToken(reqParam);
        return true;
    }

    /**
     * 安全认证
     * 
     * @param request
     * @throws CertificateException
     */
    private void checkAccessToken(ReqParam reqParam) throws Exception {
        String url = reqParam.getUrl();
        String loginId = reqParam.getReqHead().getUserLoginId();
        String accessToken = reqParam.getReqHead().getAccessToken();
        if (StringUtils.isBlank(accessToken)) {
            for (String openUrl : UrlRightConstant.openUrlList) {
                if (url.indexOf(openUrl) != -1) {
                    return;
                }
            }

            // 安全认证，需要登录的接口，必须有access_token
            for (String secureUrl : UrlRightConstant.loginVerifyUrlList) {
                if (url.indexOf(secureUrl) != -1) {
                    throw new CertificateException("access_token is blank");
                }
            }
        } else {
        	//FIXME token验证
        	// Step1 解析token————无法解析token，token被篡改，请重新登录
        	// Step2 验证token是否失效————token已失效，请重新登录
        	// Step3 验证token是否正确————token不正确，请重新登录
        	// Step3.2 验证ip和浏览器，判断是否是在其他地方登录过导致的token失效，还可以根据IP获取地区，判断是否是异地登录（需要设计常用地址）
        	// 当token更新时间在本次操作前X时间内，提示当前账号已在其他终端登录，如果ip为异地，警告异地登录
        	// 否则超过X时间，只提示token失效。如果ip为异地，告知，该账号曾在异地登录过。
        	ResponseDO<UserLoginDO> responeDO = userService.getUserLoginByLoginId(loginId);
        	if(responeDO.isSuccess()) {
        		String lastAccessToken = responeDO.getDataResult().getAccessToken();
        		if(!lastAccessToken.equals(accessToken)) {
        			throw new CertificateException("access_token is not right");
        		}
        	}
        	
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView model)
            throws Exception {
    }
}
