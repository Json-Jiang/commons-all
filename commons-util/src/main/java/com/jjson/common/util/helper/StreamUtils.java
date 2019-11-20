package com.jjson.common.util.helper;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jiangjunshen
 * @date 11:40 AM 2019/11/20
 */
public class StreamUtils {

    /**
     * 将两个集合通过对应的转换函数合并为一个新集合，兼容传进来的集合为空的情况
     * 
     * @param list1 集合1
     * @param list2 集合2
     * @param function1 转换函数1
     * @param function2 转换函数2
     * @param <S> 集合1的泛型
     * @param <R> 集合2的泛型
     * @param <T> 目标集合的泛型
     * @return 目标集合
     */
    public static <S, R, T> List<T> mergeStream(Collection<S> list1, Collection<R> list2, Function<S, T> function1,
            Function<R, T> function2) {
        if (CollectionUtils.isEmpty(list1)) {
            if (CollectionUtils.isEmpty(list2)) {
                return Collections.emptyList();
            } else {
                return list2.stream().map(function2).collect(Collectors.toList());
            }
        } else if (CollectionUtils.isEmpty(list2)) {
            return list1.stream().map(function1).collect(Collectors.toList());
        }
        Stream<T> stream1 = list1.stream().map(function1);
        Stream<T> stream2 = list2.stream().map(function2);
        return Stream.concat(stream1, stream2).collect(Collectors.toList());
    }

    /**
     * 简化版的集合流合并，集合1的类型与目标类型相同，兼容传进来的集合为空的情况
     * 
     * @param list1 集合1
     * @param list2 集合2
     * @param function 集合2的转换函数
     * @param <S> 集合2的泛型
     * @param <T> 集合1和目标集合的泛型
     * @return 目标集合
     */
    public static <S, T> List<T> mergeStream(Collection<T> list1, Collection<S> list2, Function<S, T> function) {
        return mergeStream(list1, list2, Function.identity(), function);
    }
}
