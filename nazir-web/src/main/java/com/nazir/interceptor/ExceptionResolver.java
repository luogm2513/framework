package com.nazir.interceptor;

import java.io.EOFException;
import java.lang.reflect.UndeclaredThrowableException;
import java.nio.channels.ClosedChannelException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.nazir.controller.base.ErrorModel;
import com.nazir.controller.base.RestModelView;
import com.nazir.exception.CertificateException;
import com.nazir.exception.ForceExitException;
import com.nazir.exception.ParamException;
import com.nazir.model.enums.BizStatusEnum;
import com.nazir.utils.RestLogUtils;

public class ExceptionResolver implements HandlerExceptionResolver {
	
    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        ErrorModel error = new ErrorModel();
        // 安全问题
        if (ex instanceof CertificateException) {
            error.setCode(BizStatusEnum.SYS_NOT_PERMISSION.getCodeEnum().getCode());
            error.setMessage(BizStatusEnum.SYS_NOT_PERMISSION.getMessage());
            RestLogUtils.writeExceptionResolverByInfo(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // 其他用户登陆,强制退出
        else if (ex instanceof ForceExitException) {
            error.setCode(BizStatusEnum.SYS_OTHER_LOGIN_ERROR.getCodeEnum().getCode());
            error.setMessage(BizStatusEnum.SYS_OTHER_LOGIN_ERROR.getMessage());
            RestLogUtils.writeExceptionResolverByInfo(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // 参数异常(参数为空)捕捉
        else if (ex instanceof EOFException) {
            error.setCode(BizStatusEnum.SYS_PARAM_ERROR.getCodeEnum().getCode());
            error.setMessage("post参数不能为空");
            RestLogUtils.writeExceptionResolverByError(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // 参数异常(类型错误)捕捉
        else if (ex instanceof ParamException) {
            ParamException paramException = (ParamException) ex;
            error.setCode(BizStatusEnum.SYS_PARAM_ERROR.getCodeEnum().getCode());
            error.setMessage(paramException.getMessage());
            RestLogUtils.writeExceptionResolverByError(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }

        else if (ex instanceof ClosedChannelException) {
            error.setCode(BizStatusEnum.SYS_STEAM_ERROR.getCodeEnum().getCode());
            error.setMessage(ex.getMessage());
            RestLogUtils.writeExceptionResolverByError(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // 上传超过最大尺寸
        else if (ex instanceof MaxUploadSizeExceededException) {
            error.setCode(BizStatusEnum.SYS_MAXUP_lOAD_SIZE.getCodeEnum().getCode());
            error.setMessage(BizStatusEnum.SYS_MAXUP_lOAD_SIZE.getMessage());
            RestLogUtils.writeExceptionResolverByInfo(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // Throwable异常
        else if (ex instanceof UndeclaredThrowableException) {
            UndeclaredThrowableException throwable = (UndeclaredThrowableException) ex;
            if (throwable.getUndeclaredThrowable().getClass().equals(ParamException.class)) {
                ParamException paramException = (ParamException) throwable.getUndeclaredThrowable();
                error.setCode(BizStatusEnum.SYS_PARAM_ERROR.getCodeEnum().getCode());
                error.setMessage(paramException.getMessage());
                RestLogUtils.writeExceptionResolverByError(request, ex, logger);
                RestLogUtils.writeRestLogByInfo(request, error);
            }
            // 错误流问题
            else if (throwable.getUndeclaredThrowable().getClass().equals(ClosedChannelException.class)) {
                ParamException paramException = (ParamException) throwable.getUndeclaredThrowable();
                error.setCode(BizStatusEnum.SYS_PARAM_ERROR.getCodeEnum().getCode());
                error.setMessage(paramException.getMessage());
                RestLogUtils.writeExceptionResolverByError(request, ex, logger);
                RestLogUtils.writeRestLogByInfo(request, error);
            } else {
                error.setCode(BizStatusEnum.SYS_EXCEPTION.getCodeEnum().getCode());
                error.setMessage(BizStatusEnum.SYS_EXCEPTION.getMessage());
                RestLogUtils.writeExceptionResolverByError(request, ex, logger);
                RestLogUtils.writeRestLogByInfo(request, error);
            }
        }
        // 其他异常
        else {
            error.setCode(BizStatusEnum.SYS_EXCEPTION.getCodeEnum().getCode());
            error.setMessage(BizStatusEnum.SYS_EXCEPTION.getMessage());
            RestLogUtils.writeExceptionResolverByError(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        ModelAndView mv = new RestModelView(error);
        return mv;
    }

}
