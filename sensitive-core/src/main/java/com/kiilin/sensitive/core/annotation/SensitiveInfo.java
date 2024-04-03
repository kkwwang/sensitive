/**
 * Create time 2019-05-29 9:48
 * Create by kiilin kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.sensitive.core.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kiilin.sensitive.core.enums.SensitiveType;
import com.kiilin.sensitive.core.json.SensitiveInfoSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 敏感字段标记注解，用于标记需要进行敏感信息处理的字段。
 * 该注解需作用于字段上，支持配置脱敏类型、输入格式正则表达式和替换目标字符。
 *
 * @author kiilin
 * @version V 1.0
 * @since JDK1.8
 */
@Retention(RetentionPolicy.RUNTIME) // 注解的生命周期为运行时
@Target({ElementType.FIELD}) // 注解可应用于字段上
@JacksonAnnotationsInside // Jackson注解，表示注解会应用到关联的JSON序列化和反序列化上
@JsonSerialize(using = SensitiveInfoSerialize.class) // 指定使用SensitiveInfoSerialize类进行JSON序列化
public @interface SensitiveInfo {

    /**
     * 脱敏类型。定义了敏感信息的脱敏方式，可选取值于SensitiveType枚举。
     * 默认为DEFAULT_TYPE，表示使用系统默认的脱敏处理方式。
     *
     * @return 脱敏类型
     */
    SensitiveType value() default SensitiveType.DEFAULT_TYPE;

    /**
     * 输入格式，使用正则表达式。用于指定字段值的匹配模式，以确定是否对该字段进行敏感信息处理。
     * 优先级高于value配置，即如果配置了pattern，则会忽略value设置的脱敏类型。
     *
     * @return 输入格式的正则表达式
     */
    String pattern() default "";

    /**
     * 替换目标字符。当字段被识别为敏感信息时，使用该字符替换原字段值中的敏感信息。
     * 例如，设置targetChar为"*"，则"12345"会被替换为"****5"。
     *
     * @return 替换目标字符串
     */
    String targetChar() default "";

}
