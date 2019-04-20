package com.jjson.common.exception.helper;

import com.jjson.common.exception.instance.NullParamException;
import com.jjson.common.exception.instance.WrongBooleanException;

import java.util.Collection;
import java.util.Objects;

/**
 * @author jiangjunshen
 * @date 下午7:19 2018/10/8
 */
public class ParamHelper {

    public static <T> T notNull(T t, String message) {
        if (Objects.isNull(t)) {
            throw new NullParamException(message, message);
        }
        return t;
    }

    public static void isTrue(Boolean b, String message) {
        if (null == b || !b) {
            throw new WrongBooleanException(message, message);
        }
    }

    public static void isFalse(Boolean b, String message) {
        if (null == b || b) {
            throw new WrongBooleanException(message, message);
        }
    }

    public static void notEmpty(Collection collection, String message) {
        if (null == collection || collection.isEmpty()) {
            throw new NullParamException(message, message);
        }
    }
}
