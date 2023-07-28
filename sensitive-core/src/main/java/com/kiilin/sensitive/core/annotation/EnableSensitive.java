package com.kiilin.sensitive.core.annotation;


import com.kiilin.sensitive.core.aspect.SensitiveAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用脱敏注解
 * @author kiilin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SensitiveAspect.class})
public @interface EnableSensitive {
}
