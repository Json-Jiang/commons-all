package com.jjson.common.exception.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记注解，代表某个方法不用被Aop捕获异常
 * 
 * @author jiangjunshen
 * @date 下午2:53 2019/2/21
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ErrorSkip {
}
