/**
 * Create time 2019-06-03 16:04
 * Create by kiilin kiilin@kiilin.com
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
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Objects;

import static com.kiilin.sensitive.core.constant.SensitiveConstant.SENSITIVE_PLACEHOLDER;

/**
 * 脱敏序列化器，用于在将敏感信息字段序列化为JSON时，自动对敏感信息进行脱敏处理。
 *
 * @author kiilin
 * @version V 1.0
 * @since JDK1.8
 */
public class SensitiveInfoSerialize extends JsonSerializer<String> implements ContextualSerializer {

    /**
     * 正则表达式模式，用于匹配敏感信息。
     */
    private String pattern;

    /**
     * 替换目标字符，用于替换敏感信息。
     */
    private String targetChar;

    /**
     * 默认构造函数
     */
    public SensitiveInfoSerialize() {
    }

    /**
     * 构造函数，根据敏感类型初始化敏感信息处理参数。
     *
     * @param type 敏感类型，包含正则表达式模式和目标替换字符。
     */
    public SensitiveInfoSerialize(final SensitiveType type) {
        this.pattern = type.getPattern();
        this.targetChar = type.getTargetChar();
    }

    /**
     * 构造函数，直接指定正则表达式模式和目标替换字符。
     *
     * @param pattern 正则表达式模式。
     * @param targetChar 目标替换字符。
     */
    public SensitiveInfoSerialize(String pattern, String targetChar) {
        this.pattern = pattern;
        this.targetChar = targetChar;
    }

    /**
     * 序列化处理函数，实际处理敏感信息的脱敏逻辑。
     *
     * @param value 需要序列化的敏感信息字符串。
     * @param jsonGenerator JSON生成器，用于输出处理后的字符串。
     * @param serializerProvider 序列化提供器，提供序列化过程所需的上下文信息。
     * @throws IOException 如果序列化过程中发生IO异常。
     */
    @Override
    public void serialize(String value, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        // 获取当前HTTP请求，用于判断是否需要脱敏
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if (request != null) {
            // 判断当前请求是否标记为需要脱敏
            Object isSensitiveValue = request.getAttribute(SensitiveConstant.IS_SENSITIVE);
            if (isSensitiveValue instanceof Boolean && (Boolean) isSensitiveValue) {
                // 获取请求头中定义的占位符，用于替换敏感信息
                String placeholder = request.getHeader(SENSITIVE_PLACEHOLDER);
                // 对敏感信息进行脱敏替换
                value = value.replaceAll(this.pattern, StringUtils.hasText(placeholder) ? this.targetChar.replace("*", placeholder) : this.targetChar);
            }
        }
        // 输出处理后的敏感信息
        jsonGenerator.writeString(value);
    }

    /**
     * 创建上下文敏感信息序列化器，用于根据注解配置实例化具体的敏感信息序列化器。
     *
     * @param serializerProvider 序列化提供器，提供序列化过程所需的上下文信息。
     * @param beanProperty 当前字段属性，用于获取注解配置。
     * @return 根据注解配置的敏感信息序列化器实例。
     * @throws JsonMappingException 如果处理过程中发生JSON映射异常。
     */
    @Override
    public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider, final BeanProperty beanProperty) throws JsonMappingException {
        // 忽略非String类型的字段
        if (beanProperty != null) {
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                // 尝试从字段或其容器类上获取SensitiveInfo注解
                SensitiveInfo sensitiveInfo = beanProperty.getAnnotation(SensitiveInfo.class);
                if (sensitiveInfo == null) {
                    sensitiveInfo = beanProperty.getContextAnnotation(SensitiveInfo.class);
                }
                // 如果存在注解，则根据注解配置创建敏感信息序列化器实例
                if (sensitiveInfo != null) {
                    SensitiveInfoSerialize sensitiveInfoSerialize;
                    if (sensitiveInfo.value() != null && !StringUtils.hasText(sensitiveInfo.pattern())) {
                        // 根据枚举类型配置实例化
                        sensitiveInfoSerialize = new SensitiveInfoSerialize(sensitiveInfo.value());
                    } else {
                        // 根据模式和目标字符配置实例化
                        sensitiveInfoSerialize = new SensitiveInfoSerialize(sensitiveInfo.pattern(), sensitiveInfo.targetChar());
                    }
                    return sensitiveInfoSerialize;
                }
            }
            // 对于非String类型或未配置SensitiveInfo注解的字段，返回默认的值序列化器
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        // 对于null值，返回默认的null值序列化器
        return serializerProvider.findNullValueSerializer(null);
    }

}
