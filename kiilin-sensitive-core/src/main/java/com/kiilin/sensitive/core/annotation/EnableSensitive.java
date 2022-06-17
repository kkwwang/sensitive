package com.kiilin.sensitive.core.annotation;


import com.kiilin.sensitive.core.aspect.SensitiveAspect;
import com.kiilin.sensitive.core.util.SensitiveSpringContextUtils;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SensitiveAspect.class, SensitiveSpringContextUtils.class})
public @interface EnableSensitive {
}
