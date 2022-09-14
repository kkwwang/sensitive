package com.kiilin.sensitive.core.annotation;


import com.kiilin.sensitive.core.aspect.SensitiveAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SensitiveAspect.class})
public @interface EnableSensitive {
}
