package com.jjson.common.business.context.business;

import com.jjson.common.business.actuator.Actuator;
import com.jjson.common.business.context.Context;

/**
 * @author jiangjunshen
 * @date 1:51 PM 2019/12/11
 */
public interface BusinessContext extends Context {
    
    Class<? extends Actuator<? extends BusinessContext, ?>> getActuator();
}
