
package com.jjson.common.util.aspect;

import com.jjson.common.api.Response;
import com.jjson.common.exception.instance.CommonBizException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

/**
 * 异常切面抽象类
 *
 * @author 幽明
 * @serial 2019-07-31
 */
public abstract class AbstractExceptionAspect {

    /**
     * 切面需要抛出的异常信息
     *
     * @return error msg
     */
    protected abstract String getErrorMsg();

    /**
     * 返回logger
     *
     * @return logger
     */
    protected abstract Logger getLogger();

    /**
     * 异常处理切面
     *
     * @param pjp 处理点
     * @return Object
     */
    public Object doAround(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        try {
            return pjp.proceed();
        } catch (CommonBizException e) {
            // 业务异常单独处理
            processException(pjp, args, e);
            return Response.failOfMessage(e.getMessage());
        } catch (Throwable throwable) {
            // 异常兜底
            processException(pjp, args, throwable);
            return Response.failOfMessage(getErrorMsg());
        }
    }

    /**
     * 对异常进行处理
     *
     * @param joinPoint 切点
     * @param args      参数
     * @param throwable 异常
     */
    private void processException(final ProceedingJoinPoint joinPoint, final Object[] args, Throwable throwable) {
        String param = "";
        if (args != null && args.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Object arg : args) {
                sb.append(",");
                sb.append(arg);
            }
            param = sb.toString().substring(1);
        }
        this.getLogger().warn("\n method: {}\n args: {} ", joinPoint.toLongString(), param, throwable);
    }
}
