package com.nazir.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nazir.controller.base.RestModelView;
import com.nazir.exception.ParamException;
import com.nazir.utils.PropertiesUtils;
import com.nazir.utils.RestLogUtils;
import com.nazir.validate.ValidateProcess;
import com.nazir.validate.common.ValidateResult;

/**
 * @Type ParamValidateInterceptor
 * @Desc 参数验证拦截器
 */
public class ParamValidateInterceptor implements MethodInterceptor {
    @Autowired
    private HttpServletRequest request;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Class<?> clazz = method.getDeclaringClass();
        if (AnnotationUtils.findAnnotation(clazz, Controller.class) != null
                && AnnotationUtils.findAnnotation(method, ResponseBody.class) != null) {
            // 接口参数验证
            Object[] objectArray = invocation.getArguments();
            Object param = null;
            if (objectArray.length == 1 || objectArray.length == 2) {
                param = objectArray[0];
                // 支付回调使用objectArray[1]
                if (objectArray.length == 2) {
                    param = objectArray[1];
                }
                // 获得Controller对象的传入参数
                Class<?> paramType = param.getClass();
                // 判断是否有验证注解信息
                if (ValidateProcess.hasAnnotation(paramType)) {
                    ValidateResult verifyResult = ValidateProcess.validateObject(param);
                    if (!verifyResult.isSuccess()) {
                        throw new ParamException(verifyResult.getErrorMsg());
                    }
                }
            }

            // 执行操作
            Object result = invocation.proceed();

            // 返回结果
            Object resultObject = null;
            if (result instanceof RestModelView) {
                resultObject = ((RestModelView) result).getModelObject();
            } else {
                resultObject = result;
            }

            // 数据格式化处理
            PropertiesUtils.setNullProperties(resultObject);

            // 日志输出:仅配置需要输出内容列表的才会输出
            RestLogUtils.writeRestLogByInfo(request, resultObject);

            return resultObject;
        }
        return invocation.proceed();
    }
}
