package com.jjson.common.util.register;

import javax.annotation.PostConstruct;

/**
 * @author jiangjunshen
 * @date 11:02 AM 2019/7/20
 */
public interface Registrable {

    /**
     * 获取注册到容器时的key的前缀，用于区分继承自同一个父类的不同子类。 <br>
     * <span style="color: #FF0000;"> 注意：使用默认的key生成策略时，同一父类的不同子类之间返回的值不能相同，否则可能会出现类型覆盖等异常情况 </span>
     *
     * @return key的前缀对象
     * @see RegisterContainer
     */
    Object getKeyPrefix();

    /**
     * 默认的注册逻辑，使用默认的key生成策略，如果子类需要使用非默认的key，可以覆盖本方法。
     */
    default void register() {
        String key = RegisterKeyGenerator.getInstance().getKey(this);
        RegisterContainer.register(key, this);
    }

    /**
     * 初始化时自动注册
     */
    @PostConstruct
    default void init() {
        register();
    }
}
