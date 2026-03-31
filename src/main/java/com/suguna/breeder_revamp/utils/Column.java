package com.suguna.breeder_revamp.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String name();
    Class<?> type() default String.class; // Default to String type
}