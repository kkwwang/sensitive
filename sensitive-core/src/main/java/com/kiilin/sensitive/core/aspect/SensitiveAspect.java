/**
 * Create time 2019-06-05 10:34
 * Create by kiilin kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.sensitive.core.aspect;


import com.kiilin.sensitive.core.annotation.Sensitive;
import com.kiilin.sensitive.core.constant.SensitiveConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Objects;

/**
 * 注解切面，处理当前请求是否需要脱敏
 * <p>
 * 该类负责在Spring MVC的处理流程中，识别并处理需要进行敏感信息脱敏的请求。
 * 通过AOP（面向切面编程）的方式，对注解了{@link Sensitive}的控制器方法进行拦截，
 * 在执行方法前设置请求是否需要进行敏感信息处理的标识。
 *
 * @author kiilin
 * @version V 1.0
 * @since JDK1.8
 */
@Aspect
public class SensitiveAspect {

    // 处理请求映射的HandlerMapping
    final HandlerMapping requestMappingHandlerMapping;

    /**
     * 构造函数
     *
     * @param requestMappingHandlerMapping 处理请求映射的HandlerMapping，用于获取当前请求对应的处理器方法。
     */
    public SensitiveAspect(@Qualifier("requestMappingHandlerMapping") HandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    /**
     * 定义切面的切入点
     * <p>
     * 该方法定义了切面的切入点，即被拦截的方法。此处拦截的是被{@link Sensitive}注解标记的控制器方法。
     */
    @Pointcut("@within(com.kiilin.sensitive.core.annotation.Sensitive) || @annotation(com.kiilin.sensitive.core.annotation.Sensitive)")
    public void pointcutByBean() {
    }

    /**
     * 方法执行前的拦截处理
     * <p>
     * 在目标方法执行前，判断当前请求的方法或类是否被{@link Sensitive}注解标记，
     * 如果是，则将是否需要脱敏的标识放入请求属性中，以供后续处理使用。
     */
    @Before("pointcutByBean()")
    public void before() throws Throwable {
        // 获取当前请求和对应的处理器方法
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HandlerExecutionChain handler = requestMappingHandlerMapping.getHandler(request);
        HandlerMethod method = (HandlerMethod) Objects.requireNonNull(handler).getHandler();

        // 获取敏感信息处理注解，并放入请求标识
        Sensitive sensitive = method.getMethodAnnotation(Sensitive.class);
        if (sensitive == null) {
            sensitive = method.getBeanType().getAnnotation(Sensitive.class);
        }
        // 设置请求是否需要敏感信息处理的标识
        request.setAttribute(SensitiveConstant.IS_SENSITIVE, sensitive.value());
    }

}
