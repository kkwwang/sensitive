/**
 * (#)com.kiilin.core.sensitive.annotation.SensitiveInfo.java
 * Create time 2019-03-29 9:48
 * Create by kiilin kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.core.sensitive.annotation;

import com.kiilin.core.sensitive.enums.SensitiveType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kiilin
 * @version V 1.0
 * @description 敏感字段标记注解（使用在字段上）
 * @see {@link SensitiveInfo}
 * @since JDK1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SensitiveInfo {

    SensitiveType value();

}
