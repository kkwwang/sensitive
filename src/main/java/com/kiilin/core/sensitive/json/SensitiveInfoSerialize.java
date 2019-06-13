/**
 * Create time 2019-06-03 16:04
 * Create by wangkai kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.core.sensitive.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.kiilin.core.sensitive.annotation.SensitiveInfo;
import com.kiilin.core.sensitive.constant.SensitiveConstant;
import com.kiilin.core.sensitive.enums.SensitiveType;
import com.kiilin.core.sensitive.util.SpringContextUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * 脱敏序列化方式
 *
 * @author wangkai
 * @version V 1.0
 * @since JDK1.8
 */
public class SensitiveInfoSerialize extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveType type;

    public SensitiveInfoSerialize() {
    }

    public SensitiveInfoSerialize(final SensitiveType type) {
        this.type = type;
    }

    @Override
    public void serialize(String value, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {

        // 读取全局执行接口
        SensitiveExecute bean = SpringContextUtils.getBean(SensitiveExecute.class);
        // 未实现接口 默认开启脱敏
        boolean execute = bean == null || (null != bean && bean.execute());

        // 读取当前请求是否需要脱敏
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object isSensitiveValue = request.getAttribute(SensitiveConstant.IS_SENSITIVE);
        boolean isSensitive = isSensitiveValue != null && (boolean) isSensitiveValue;

        if (execute && isSensitive) {
            // 替换
            value = value.replaceAll(this.type.getPattern(), this.type.getTargetChar());
        }

        jsonGenerator.writeString(value);
    }

    @Override
    public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider, final BeanProperty beanProperty) throws JsonMappingException {
        // 为空直接跳过
        if (beanProperty != null) {
            // 非 String 类直接跳过
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {

                SensitiveInfo sensitiveInfo = beanProperty.getAnnotation(SensitiveInfo.class);
                if (sensitiveInfo == null) {
                    sensitiveInfo = beanProperty.getContextAnnotation(SensitiveInfo.class);
                }

                // 如果能得到注解，就将注解的 value 传入 SensitiveInfoSerialize
                if (sensitiveInfo != null) {
                    return new SensitiveInfoSerialize(sensitiveInfo.value());
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(beanProperty);
    }
}