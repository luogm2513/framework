package com.nazir.interceptor;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author luogm
 * 
 */
public class RestObjectMapper extends ObjectMapper {
    public RestObjectMapper() {
        super();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
