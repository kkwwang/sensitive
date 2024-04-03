package com.kiilin.sensitive.start.factory;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.List;

/**
 * 自定义属性源工厂类，用于SpringBoot应用中加载yml配置文件。
 * 继承自DefaultPropertySourceFactory，重写createPropertySource方法以支持yml配置。
 *
 * @author yangyi
 */
public class PropertySourceFactory extends DefaultPropertySourceFactory {

    /**
     * 创建并返回一个属性源，该属性源加载自指定的资源。
     * 本方法专门用于加载yml配置文件为属性源。
     *
     * @param name 属性源的名称。在本场景中，名称并不直接使用，但必须提供。
     * @param resource 指定的资源对象，包含了要加载的yml文件的信息。
     * @return 加载后的属性源对象。该对象是Spring环境中的PropertySource类型，包含从yml文件中加载的属性。
     * @throws IOException 如果在读取资源时发生IO异常，则抛出。
     */
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        // 使用YamlPropertySourceLoader加载yml文件为属性源列表
        List<PropertySource<?>> sources = new YamlPropertySourceLoader().load(resource.getResource().getFilename(), resource.getResource());
        // 返回加载的第一个属性源。在通常情况下，一个yml文件会对应一个属性源。
        return sources.get(0);
    }
}
