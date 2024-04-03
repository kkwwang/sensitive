package com.kiilin.sensitive.core.annotation;

import com.kiilin.sensitive.core.aspect.SensitiveAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用敏感信息处理的注解
 * 当在Spring Boot应用中使用此注解时，它会自动导入{@link SensitiveAspect}切面，
 * 从而启用对敏感信息的处理。该注解适用于类型级别（类上）。
 *
 * @author kiilin
 * @since 1.0.0
 */
@Target(ElementType.TYPE) // 注解适用于类
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时可读取
@Documented // 将此注解包含在Javadoc中
@Inherited // 子类可以继承此注解
@Import({SensitiveAspect.class}) // 导入SensitiveAspect切面
public @interface EnableSensitive {
}
