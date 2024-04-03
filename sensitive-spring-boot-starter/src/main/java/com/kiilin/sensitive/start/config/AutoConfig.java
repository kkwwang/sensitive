package com.kiilin.sensitive.start.config;

import com.kiilin.sensitive.start.factory.PropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * 自动配置类，用于加载敏感信息配置。
 * 使用了Spring Boot的自动配置功能，并通过PropertySource加载配置文件`sensitive.yml`。
 *
 * @author kiilin
 */
@AutoConfiguration
@PropertySource(value = "classpath:sensitive.yml", factory = PropertySourceFactory.class)
public class AutoConfig {


}
