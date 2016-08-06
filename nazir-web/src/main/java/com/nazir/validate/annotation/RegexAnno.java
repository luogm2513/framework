package com.nazir.validate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nazir.validate.ValidateFactory.DEFAULT_ERROR_FLAG;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
/**
 * 正则表达式验证
 *
 */
public @interface RegexAnno {
    /**
     * 正则表达式
     * 
     * @return
     */
    String pattern();

    /**
     * 错误信息
     * 
     * @return
     */
    String errMsg() default DEFAULT_ERROR_FLAG.REGEX_ANNO;
}
