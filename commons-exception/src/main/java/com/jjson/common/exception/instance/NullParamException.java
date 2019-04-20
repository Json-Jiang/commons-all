package com.jjson.common.exception.instance;

/**
 * @author jiangjunshen
 * @date 下午7:18 2018/10/8
 */
public class NullParamException extends CommonBizException {
    
    public NullParamException(String message) {
        super(message);
    }

    public NullParamException(String code, String message) {
        super(code, message);
    }
}
