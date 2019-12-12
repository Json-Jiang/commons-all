package com.jjson.common.business.actuator;

/**
 * @author jiangjunshen
 * @date 1:52 PM 2019/12/11
 */
public interface Actuator<S, T> {
    
    T execute(S context);
}
