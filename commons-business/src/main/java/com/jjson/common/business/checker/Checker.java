package com.jjson.common.business.checker;

/**
 * @author jiangjunshen
 * @date 3:07 PM 2019/12/11
 */
public interface Checker<S, T> {

    T check(S context);
}
