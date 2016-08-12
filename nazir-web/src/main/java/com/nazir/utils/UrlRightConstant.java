package com.nazir.utils;

import java.util.ArrayList;
import java.util.List;

public class UrlRightConstant {
    // 需要登录
    public static List<String> loginVerifyUrlList = new ArrayList<String>();
    // 忽略所有验证
    public static List<String> notVerifyUrlList = new ArrayList<String>();
    // 是否登录不能请求,如果登录能获取登录信息,暂时没用
    public static List<String> openUrlList = new ArrayList<String>();
    // 返回结果集数据需要加密的url,暂时没用
    public static List<String> dataSignUrlList = new ArrayList<String>();
    // 线上需要打印返回结果体的日志的url,暂时没用
    public static List<String> logUrlList = new ArrayList<String>();

    static {
        notVerifyUrlList.add("/account/user/login");
        notVerifyUrlList.add("/account/user/register");

        openUrlList.add("/");

        loginVerifyUrlList.add("/account/user");

        dataSignUrlList.add("/");

        logUrlList.add("/");
    }

}
