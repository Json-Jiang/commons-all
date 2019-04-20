package com.jjson.common.exception.instance;

/**
 * @author jiangjunshen
 * @date 下午8:02 2018/10/8
 */
public class WrongBooleanException extends CommonBizException {
    
    public WrongBooleanException(String message) {
        super(message);
    }

    public WrongBooleanException(String code, String message) {
        super(code, message);
    }
}
