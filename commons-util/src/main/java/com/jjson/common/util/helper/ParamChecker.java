package com.jjson.common.util.helper;

import com.jjson.common.exception.instance.CommonBizException;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Objects;

/**
 * @author jiangjunshen
 * @date 下午7:19 2018/10/8
 */
@Slf4j
public class ParamChecker {

    public static <T> T notNull(T t, String message) {
        if (Objects.isNull(t)) {
            throw new CommonBizException(message);
        }
        return t;
    }

    public static <T> T notNull(T t, String code, String message) {
        if (Objects.isNull(t)) {
            throw new CommonBizException(code, message);
        }
        return t;
    }

    public static void isTrue(Boolean b, String message) {
        if (null == b || !b) {
            throw new CommonBizException(message);
        }
    }

    public static void isFalse(Boolean b, String message) {
        if (null == b || b) {
            throw new CommonBizException(message);
        }
    }

    public static void notEmpty(Collection collection, String message) {
        if (null == collection || collection.isEmpty()) {
            throw new CommonBizException(message);
        }
    }

    /**
     * 校验如果不通过，打印warn日志
     * 
     * @param t 需要校验的对象
     * @param message 向外传递的message
     * @param logFormat 日志待格式化的串
     * @param arguments 日志的格式化参数
     * @param <T> 泛型
     * @return 校验对象本身
     */
    public static <T> T notNull(T t, String message, String logFormat, Object... arguments) {
        if (Objects.isNull(t)) {
            log.warn(logFormat, arguments);
            throw new CommonBizException(message);
        }
        return t;
    }
}
