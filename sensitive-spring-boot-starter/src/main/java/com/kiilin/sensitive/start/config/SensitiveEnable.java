package com.kiilin.sensitive.start.config;


import com.kiilin.sensitive.core.annotation.EnableSensitive;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * 读取环境，自动装配
 * @author kiilin
 */
@AutoConfiguration(after = AutoConfig.class)
@ConditionalOnProperty(value = "sensitive.enable", havingValue = "true")
@EnableSensitive
public class SensitiveEnable {
}
