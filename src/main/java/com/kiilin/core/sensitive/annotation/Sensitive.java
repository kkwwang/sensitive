/**
 * (#)com.kiilin.core.sensitive.annotation.Sensitive.java
 * Create time 2019-03-29 9:48
 * Create by kiilin kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.core.sensitive.annotation;

import java.lang.annotation.*;

/**
 * @author kiilin
 * @version V 1.0
 * @description 敏感字段标记注解(使用在接口上)
 * @see {@link Sensitive}
 * @since JDK1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sensitive {

}
