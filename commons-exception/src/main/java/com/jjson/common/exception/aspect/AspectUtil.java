package com.jjson.common.exception.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangjunshen
 * @date 上午11:54 2019/3/12
 */
public class AspectUtil {

    public static final Map<String, Method> METHODS = new ConcurrentHashMap<>();
    
    public static String getMethodPath(ProceedingJoinPoint pjp){
        return pjp.getTarget().getClass().getPackage().getName() + "." + pjp.getSignature().toShortString();
    }

    public static void judgeErrorSkip(String methodLong, Throwable e) throws Throwable {
        if (Objects.nonNull(METHODS.get(methodLong))) {
            ErrorSkip errorSkip = METHODS.get(methodLong).getAnnotation(ErrorSkip.class);
            if (errorSkip != null) {
                //跳过方法,不需要拦截处理该方法抛出的异常!
                throw e;
            }
        }
    }
}
