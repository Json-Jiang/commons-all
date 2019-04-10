package com.jjson.common.exception.aspect;

import com.jjson.common.exception.instance.CommonBizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author jiangjunshen
 * @date 上午11:56 2019/3/12
 */
@Slf4j
@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE + 2)
public class CommonExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Pointcut(value = "execution(public Result *..*.*(..))")
    public void returnResult() {
    }
    
    @Around("@within(errorHandler) && returnResult()")
    public Object exceptionHandler(ProceedingJoinPoint joinPoint, ErrorHandler errorHandler) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodPath = AspectUtil.getMethodPath(joinPoint);
        log.info("Invoke method[{}], params: [{}]", methodPath, joinPoint.getArgs());
        String methodLong = joinPoint.getSignature().toLongString();
        if (!AspectUtil.METHODS.containsKey(methodLong)) {
            AspectUtil.METHODS.putIfAbsent(methodLong, methodSignature.getMethod());
        }
        methodSignature.getReturnType();
        try {
            Object result = joinPoint.proceed();
            log.debug("Invoke method[{}], result: {}", methodPath, result);
            return result;
        } catch (CommonBizException e) {
            AspectUtil.judgeErrorSkip(methodLong, e);
            
            String code = e.getCode() == null ? ErrorConstants.DEFAULT_ERROR : e.getCode();
            String message = getMessage(code, e.getParams());
            log.warn("Invoke method[{}] failed, message: ", methodPath, e);
            return Result.fail(code, message);
        } catch (Throwable e) {
            AspectUtil.judgeErrorSkip(methodLong, e);
            log.error("Unknown error occurs when invoke method[{}], error: ", methodPath, e);
            return Result.fail(ErrorConstants.DEFAULT_ERROR, ErrorConstants.DEFAULT_MESSAGE);
        }
    }
    
    private String getMessage(String code, Object[] params) {
        return messageSource.getMessage(code, params, code, Locale.SIMPLIFIED_CHINESE);
    }
}
