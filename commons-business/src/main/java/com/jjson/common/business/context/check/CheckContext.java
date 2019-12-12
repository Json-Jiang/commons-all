package com.jjson.common.business.context.check;

import com.jjson.common.business.checker.Checker;
import com.jjson.common.business.context.Context;

/**
 * @author jiangjunshen
 * @date 3:11 PM 2019/12/11
 */
public interface CheckContext extends Context {

    Class<? extends Checker<? extends CheckContext, ?>> getChecker();
}
