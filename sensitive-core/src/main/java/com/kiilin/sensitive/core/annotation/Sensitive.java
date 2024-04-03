/**
 * Create time 2019-05-29 9:48
 * Create by kiilin kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.sensitive.core.annotation;

import java.lang.annotation.*;

/**
 * 敏感字段标记注解，用于标记需要进行敏感信息处理的字段。
 * 可以应用于方法或类型上。
 *
 * @author kiilin
 * @version V 1.0
 * @since JDK1.8
 */
@Target({ElementType.METHOD, ElementType.TYPE}) // 注解可以应用于方法或类上
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时可以被读取
@Documented // 文档中会包含此注解
public @interface Sensitive {

    /**
     * 是否对字段进行脱敏处理。
     *
     * @return 返回true表示需要进行脱敏处理，false表示不需要。默认为true。
     */
    boolean value() default true;

}
