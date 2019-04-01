/**
 * (#)com.kiilin.core.sensitive.json.SensitiveJsonValueFilter.java
 * Create time 2019-03-29 17:24
 * Create by kiilin kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.core.sensitive.json;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.kiilin.core.sensitive.annotation.SensitiveInfo;

import java.lang.reflect.Field;

/**
 * @author kiilin
 * @version V 1.0
 * @description json敏感词过滤器
 * @see {@link SensitiveJsonValueFilter}
 * @since JDK1.8
 */

public class SensitiveJsonValueFilter implements ValueFilter {


    @Override
    public Object process(Object object, String name, Object value) {
        if (value instanceof String) {
            Field field;
            try {
                field = object.getClass().getDeclaredField(name);
                SensitiveInfo annotation = field.getAnnotation(SensitiveInfo.class);
                if (null != annotation) {
                    return ((String) value).replaceAll(annotation.value().getPattern(), annotation.value().getTargetChar());
                }
            } catch (NoSuchFieldException e) {
            }
        }
        return value;
    }
}
