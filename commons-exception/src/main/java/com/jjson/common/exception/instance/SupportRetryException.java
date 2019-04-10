package com.jjson.common.exception.instance;

/**
 * @author jiangjunshen
 * @date 10:01 PM 2019/4/10
 */
public class SupportRetryException extends CommonBizException {
    
    public SupportRetryException(String message) {
        super(message);
    }

    public SupportRetryException(String message, String code, Object... params) {
        super(message);
        setCode(code);
        setParams(params);
    }
}
