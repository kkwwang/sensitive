package com.kiilin.sensitive.start.config;


import com.kiilin.sensitive.start.factory.PropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author kiilin
 */
@AutoConfiguration
@PropertySource(value = "classpath:sensitive.yml", factory = PropertySourceFactory.class)
public class AutoConfig {


}
