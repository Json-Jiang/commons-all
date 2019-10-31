package com.jjson.common.util.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by linrizeng on 2015/12/5.
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext arg) throws BeansException {
        context = arg;
    }

    public static ApplicationContext getContext(){
        return context;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        return context.getBean(beanName, requiredType);
    }

    public static <T> T getBeanByType(Class<T> tClass){
        return context.getBean(tClass);
    }

    public static boolean containsBean(String name) {
        return context.containsBean(name);
    }
}
