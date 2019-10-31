package com.jjson.common.util.register;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author jiangjunshen
 * @date 10:52 AM 2019/7/20
 */
public class RegisterContainer {

    private static Map<Object, Registrable> innerContainer = Maps.newConcurrentMap();

    public static void register(Object key, Registrable value) {
        innerContainer.put(key, value);
    }

    public static Registrable get(Object key) {
        return innerContainer.get(key);
    }

    public static boolean containsKey(Object key) {
        return innerContainer.containsKey(key);
    }
}
