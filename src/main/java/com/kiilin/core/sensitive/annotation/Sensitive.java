/**
 * Create time 2019-05-29 9:48
 * Create by wangkai kiilin@kiilin.com
 * Copyright 2018 kiilin http://www.kiilin.com
 */

package com.kiilin.core.sensitive.annotation;

import java.lang.annotation.*;

/**
 * @author wangkai
 * @version V 1.0
 * @description 敏感字段标记注解(使用在接口上)
 * @see {@link Sensitive}
 * @since JDK1.8
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sensitive {

    /**
     * 是否脱敏
     *
     * @return 是否需要脱敏
     */
    boolean value() default true;

}
