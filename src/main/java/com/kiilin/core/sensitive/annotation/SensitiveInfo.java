/**
 * Create time 2019-05-29 9:48
 * Create by wangkai kiilin@kiilin.com
 * Copyright 2018 kiilin http://www.kiilin.com
 */

package com.kiilin.core.sensitive.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kiilin.core.sensitive.enums.SensitiveType;
import com.kiilin.core.sensitive.json.SensitiveInfoSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 敏感字段标记注解（使用在字段上）
 *
 * @author wangkai
 * @version V 1.0
 * @since JDK1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveInfoSerialize.class)
public @interface SensitiveInfo {

    /**
     * 脱敏类型
     *
     * @return 脱敏类型
     */
    SensitiveType value();

}
