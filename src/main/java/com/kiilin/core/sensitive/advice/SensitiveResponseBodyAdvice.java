/**
 * (#)com.kiilin.core.sensitive.advice.SensitiveResponseBodyAdvice.java
 * Create time 2019-03-29 14:38
 * Create by kiilin kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.core.sensitive.advice;

import com.alibaba.fastjson.JSON;
import com.kiilin.core.sensitive.annotation.Sensitive;
import com.kiilin.core.sensitive.json.SensitiveJsonValueFilter;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author kiilin
 * @version V 1.0
 * @description 数据脱敏的ResponseBodyAdvice
 * @see {@link SensitiveResponseBodyAdvice}
 * @since JDK1.8
 */
@ControllerAdvice
public class SensitiveResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 有Sensitive的接口 执行
        return null != returnType.getMethodAnnotation(Sensitive.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String string = JSON.toJSONString(body, new SensitiveJsonValueFilter());
        return JSON.parseObject(string, Object.class);
    }
}
