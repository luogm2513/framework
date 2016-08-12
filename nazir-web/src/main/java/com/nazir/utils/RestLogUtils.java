package com.nazir.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nazir.controller.base.AbstractFlagModel;
import com.nazir.controller.base.SimpleFlagModel;
import com.nazir.reqinfo.ReqInfo;
import com.nazir.reqinfo.ReqParam;

public class RestLogUtils {
    private static final Logger log = LoggerFactory.getLogger("rest");

    public static void writeExceptionResolverByInfo(HttpServletRequest request, Exception ex, Logger logger) {
        ReqParam reqParam = (ReqParam) request.getAttribute("ReqParam");
        logger.info("reqParam={},errmsg={}", JsonUtils.toJSON(reqParam), ex.getMessage(), ex);
    }

    public static void writeExceptionResolverByError(HttpServletRequest request, Exception ex, Logger logger) {
        ReqParam reqParam = (ReqParam) request.getAttribute("ReqParam");
        logger.error("reqParam={},errmsg={}", JsonUtils.toJSON(reqParam), ex.getMessage(), ex);
    }

    public static void writeRestLogByInfo(HttpServletRequest request, Object resultObject) {
        String url = "";
        ReqParam reqParam = (ReqParam) request.getAttribute("ReqParam");
        if (reqParam != null) {
            url = reqParam.getUrl();
        }
        if (StringUtils.isNotBlank(url) && UrlRightConstant.logUrlList.contains(url)) {
            RestLogUtils.writeRestLogByInfo(reqParam, resultObject,
                    (Date) request.getAttribute("req_time"), false);
        } else {
            // 暂时日志先不打印
            if (resultObject instanceof AbstractFlagModel) {
            	SimpleFlagModel simpleFlagModel = new SimpleFlagModel();
                simpleFlagModel.setCode(((AbstractFlagModel) resultObject).getCode());
                simpleFlagModel.setMessage(((AbstractFlagModel) resultObject).getMessage());
                if ("0".equals(simpleFlagModel.getCode())) {
                    RestLogUtils.writeRestLogByInfo(reqParam, simpleFlagModel,
                            (Date) request.getAttribute("req_time"), false);
                } else {
                    RestLogUtils.writeRestLogByInfo(reqParam, resultObject,
                            (Date) request.getAttribute("req_time"), false);
                }
            } else {
                RestLogUtils.writeRestLogByInfo(reqParam, resultObject,
                        (Date) request.getAttribute("req_time"), false);
            }
        }
    }

    private static void writeRestLogByInfo(ReqParam reqParam, Object resultObject, Date reqTime,
            Boolean jsonFormatter) {
        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setReqParam(reqParam);
        reqInfo.setRespBody(resultObject);
        reqInfo.setRespTime(DateUtil.getMillisecond(reqTime, new Date()));
        if (jsonFormatter) {
            log.info(JsonUtils.toJSONFormatter(reqInfo));
        } else {
            log.info(JsonUtils.toJSON(reqInfo));
        }
    }
}
