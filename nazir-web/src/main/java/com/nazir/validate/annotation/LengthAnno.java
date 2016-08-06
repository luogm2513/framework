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
 * 长度验证
 *
 */
public @interface LengthAnno {
    /**
     * 最小长度
     * 
     * @return
     */
    int minLength() default 1;

    /**
     * 最大长度
     * 
     * @return
     */
    int maxLength();

    /**
     * 错误信息
     * 
     * @return
     */
    String errMsg() default DEFAULT_ERROR_FLAG.LENGTH_ANNO;
}
