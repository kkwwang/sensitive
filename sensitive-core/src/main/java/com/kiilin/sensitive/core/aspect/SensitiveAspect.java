/**
 * Create time 2019-06-05 10:34
 * Create by wangkai kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.sensitive.core.aspect;


import com.kiilin.sensitive.core.annotation.Sensitive;
import com.kiilin.sensitive.core.constant.SensitiveConstant;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * 注解切面，处理当前请求是否需要脱敏
 *
 * @author wangkai
 * @version V 1.0
 * @since JDK1.8
 */
@Aspect
public class SensitiveAspect {

    final
    HandlerMapping requestMappingHandlerMapping;

    public SensitiveAspect(HandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @Pointcut("@within(com.kiilin.sensitive.core.annotation.Sensitive) || @annotation(com.kiilin.sensitive.core.annotation.Sensitive)")
    public void pointcutByBean() {
    }


    @Before("pointcutByBean()")
    public void before() throws Throwable {

        // 获取请求
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HandlerExecutionChain handler = requestMappingHandlerMapping.getHandler(request);
        HandlerMethod method = (HandlerMethod) Objects.requireNonNull(handler).getHandler();


        // 获取controller方法上的注解
        Sensitive sensitive = method.getMethodAnnotation(Sensitive.class);
        if (sensitive == null) {
            sensitive = method.getBeanType().getAnnotation(Sensitive.class);
        }
        // 放入标识
        request.setAttribute(SensitiveConstant.IS_SENSITIVE, sensitive.value());
    }

}
