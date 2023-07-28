/**
 * Create time 2019-06-03 16:04
 * Create by wangkai kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.sensitive.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.kiilin.sensitive.core.annotation.SensitiveInfo;
import com.kiilin.sensitive.core.constant.SensitiveConstant;
import com.kiilin.sensitive.core.enums.SensitiveType;
import org.springframework.util.StringUtils;
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


    /**
     * 匹配正则
     */
    private String pattern;

    /**
     * 目标字符
     */
    private String targetChar;

    public SensitiveInfoSerialize() {
    }

    public SensitiveInfoSerialize(final SensitiveType type) {
        this.pattern = type.getPattern();
        this.targetChar = type.getTargetChar();
    }

    public SensitiveInfoSerialize(String pattern, String targetChar) {
        this.pattern = pattern;
        this.targetChar = targetChar;
    }

    @Override
    public void serialize(String value, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        // 读取当前请求是否需要脱敏
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Object isSensitiveValue = request.getAttribute(SensitiveConstant.IS_SENSITIVE);
        String placeholder = request.getHeader("sensitive-placeholder");

        if (isSensitiveValue instanceof Boolean && (Boolean) isSensitiveValue) {
            // 替换
            value = value.replaceAll(this.pattern, StringUtils.hasText(placeholder) ? this.targetChar.replace("*", placeholder) : this.targetChar);
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

                // 如果能得到注解
                if (sensitiveInfo != null) {
                    SensitiveInfoSerialize sensitiveInfoSerialize;
                    if (sensitiveInfo.value() != null && !StringUtils.hasText(sensitiveInfo.pattern())) {
                        sensitiveInfoSerialize = new SensitiveInfoSerialize(sensitiveInfo.value());
                    } else {
                        sensitiveInfoSerialize = new SensitiveInfoSerialize(sensitiveInfo.pattern(), sensitiveInfo.targetChar());
                    }
                    return sensitiveInfoSerialize;
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(beanProperty);
    }

}
