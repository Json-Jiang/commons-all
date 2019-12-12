package com.jjson.common.business.context.check;

import com.jjson.common.business.checker.ItemChecker;

/**
 * @author jiangjunshen
 * @date 3:13 PM 2019/12/11
 */
public class ItemCheckContext implements CheckContext {
    
    @Override
    public Class<ItemChecker> getChecker() {
        return ItemChecker.class;
    }
}
