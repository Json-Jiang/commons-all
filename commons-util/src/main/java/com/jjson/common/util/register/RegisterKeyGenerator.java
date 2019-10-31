package com.jjson.common.util.register;

import com.jjson.common.util.constant.RegisterConstants;
import com.jjson.common.util.constant.SymbolConstants;

/**
 * @author jiangjunshen
 * @date 11:29 AM 2019/7/20
 */
public class RegisterKeyGenerator implements KeyGenerator<Registrable> {

    private static volatile RegisterKeyGenerator instance;

    private RegisterKeyGenerator() {
    }

    public static RegisterKeyGenerator getInstance() {
        if (null == instance) {
            synchronized (RegisterKeyGenerator.class) {
                if (null == instance) {
                    instance = new RegisterKeyGenerator();
                }
            }
        }
        return instance;
    }

    /**
     * <pre>
     *     key生成策略：
     *     1. 取需要注册到容器中的bean返回的key前缀
     *     2. 如果key前缀是"default"，那么取该类的全路径，否则取父类的全路径
     *     3. 将前缀与路径以"-"进行拼接
     *     例如：
     *     1. 某个bean返回的namePrefix为"key1"，父类为cn.gov.zcy.example.Parent, 则最终的key为"key1-cn.gov.zcy.example.Parent"
     *     2. 某个bean返回的namePrefix为"default"，其本身的类路径为cn.gov.zcy.example.Son, 则最终的key为"default-cn.gov.zcy.example.Son"
     * </pre>
     *
     * @param value 需要注册到容器中的bean
     */
    @Override
    public String getKey(Registrable value) {
        String keyPrefix = String.valueOf(value.getKeyPrefix());
        Class<?> clazz = RegisterConstants.DEFAULT.equals(keyPrefix) ? value.getClass() : value.getClass().getSuperclass();
        return keyPrefix + SymbolConstants.BAR + clazz.getName();
    }
}
