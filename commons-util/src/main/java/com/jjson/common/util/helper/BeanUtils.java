package com.jjson.common.util.helper;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jjson.common.exception.instance.CommonBizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author jiangjunshen
 * @date 上午11:21 2018/8/16
 */
@Slf4j
public class BeanUtils {

    private static final ConcurrentMap<String, BeanCopier> BEAN_COPY_MAP = new ConcurrentHashMap<>();

    /**
     * 两个类对象之间转换
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void convert(Object source, Object target) {
        if (null == source) {
            return;
        }
        BeanCopier beanCopier = getBeanCopier(source.getClass(), target.getClass());
        beanCopier.copy(source, target, null);
    }

    /**
     * 属性名称相同的类之间转换
     *
     * @param s     源对象
     * @param clazz 目标Class, 需要有无参构造方法
     * @param <S>   源类
     * @param <T>   目标类
     * @return 目标对象
     */
    public static <S, T> T convert(S s, Class<T> clazz) {
        try {
            if (null == s) {
                return null;
            }
            T t = clazz.newInstance();
            BeanUtils.convert(s, t);
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("instantiate {} failed, error: {}", clazz.getName(), e.getMessage());
            throw new CommonBizException("实例化出现异常");
        }
    }

    public static <S, T> List<T> convert(List<S> list, Class<T> clazz) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().map(t -> BeanUtils.convert(t, clazz)).collect(Collectors.toList());
    }

    /**
     * 需要对象及对象所有的对象属性都实现序列化
     * 异常要外部处理
     *
     * @param obj   源对象
     * @param clazz 目标类
     * @param <T>   目标对象泛型
     * @return 目标对象
     */
    public static <T extends Serializable> T convertByJson(Object obj, Class<T> clazz) {
        //使用JSONObject
        String jsonStr = JSONObject.toJSONString(obj);
        return JSONObject.parseObject(jsonStr, clazz);
    }

    /**
     * 将集合通过json的方式转换
     *
     * @param sourceList 源集合
     * @param clazz      目标集合存储的类型
     * @param <T>        目标泛型
     * @param <S>        源泛型
     * @return 目标集合
     */
    public static <T extends Serializable, S extends Serializable> List<T> convertListByJson(List<S> sourceList, Class<T> clazz) {
        if (null == sourceList) {
            return null;
        }
        if (sourceList.size() == 0) {
            return Collections.emptyList();
        }
        List<T> result = Lists.newArrayList();
        sourceList.forEach(t -> result.add(convertByJson(t, clazz)));
        return result;
    }

    /**
     * @param source 源类
     * @param target 目标类
     * @return BeanCopier 获取BeanCopier
     */
    private static BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
        String beanCopierKey = generateBeanKey(source, target);
        if (BEAN_COPY_MAP.containsKey(beanCopierKey)) {
            return BEAN_COPY_MAP.get(beanCopierKey);
        }
        BeanCopier beanCopier = BeanCopier.create(source, target, Boolean.FALSE);
        BEAN_COPY_MAP.putIfAbsent(beanCopierKey, beanCopier);
        return BEAN_COPY_MAP.get(beanCopierKey);
    }

    /**
     * @param source 源类
     * @param target 目标类
     * @return String 生成两个类的key
     */
    private static String generateBeanKey(Class<?> source, Class<?> target) {
        return source.getName() + "@" + target.getName();
    }
}
