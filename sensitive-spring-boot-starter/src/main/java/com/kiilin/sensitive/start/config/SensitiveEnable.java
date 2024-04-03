package com.kiilin.sensitive.start.config;

import com.kiilin.sensitive.core.annotation.EnableSensitive;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * 自动配置类，用于敏感信息处理的启用配置。
 * 当应用的配置中"sensitive.enable"属性为true时，该配置类会被Spring Boot自动识别并加载。
 * 它主要负责开启敏感信息的处理功能。
 *
 * @author kiilin
 */
@AutoConfiguration(after = AutoConfig.class) // 指定在AutoConfig之后进行自动配置
@ConditionalOnProperty(value = "sensitive.enable", havingValue = "true") // 条件注解，只有当"sensitive.enable"为true时，该配置类才生效
@EnableSensitive // 开启敏感信息处理功能的注解
public class SensitiveEnable {
}
