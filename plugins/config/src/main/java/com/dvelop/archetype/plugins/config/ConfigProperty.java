package com.dvelop.archetype.plugins.config;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface ConfigProperty {
    @Nonbinding
    String value();

    @Nonbinding
    String fallback() default "";

    @Nonbinding
    boolean mandatory() default false;
}
