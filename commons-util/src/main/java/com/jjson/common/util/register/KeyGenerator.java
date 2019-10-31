package com.jjson.common.util.register;

/**
 * @author jiangjunshen
 * @date 11:28 AM 2019/7/20
 */
public interface KeyGenerator<T> {

    /**
     * 根据传入的参数获取key
     *
     * @param t 入参
     * @return key
     */
    String getKey(T t);
}
