package com.jjson.common.exception.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiangjunshen
 * @date 10:24 AM 2019/4/4
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ErrorHandler {

    /**
     * @return whether print parameters 
     */
    boolean logStart() default true;

    /**
     * @return whether print result
     */
    boolean logEnd() default true;
}
