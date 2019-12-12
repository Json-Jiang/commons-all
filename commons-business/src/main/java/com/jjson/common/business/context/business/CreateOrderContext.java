package com.jjson.common.business.context.business;

import com.jjson.common.business.actuator.CreateOrderActuator;

/**
 * @author jiangjunshen
 * @date 2:18 PM 2019/12/11
 */
public class CreateOrderContext implements BusinessContext {
    
    @Override
    public Class<CreateOrderActuator> getActuator() {
        return CreateOrderActuator.class;
    }
}
