package com.jjson.common.exception.instance;

/**
 * @author jiangjunshen
 * @date 9:25 PM 2019/4/10
 */
public class CommonBizException extends RuntimeException {
    
    private String code;
    
    private Object[] params;
    
    public CommonBizException(String message) {
        super(message);
    }
    
    public CommonBizException(String message, String code, Object... params) {
        super(message);
        this.code = code;
        this.params = params;
    }
    
    public Object[] getParams() {
        return this.params;
    }
    
    public String getCode() {
        return this.code;
    }
    
    protected void setParams(Object[] params) {
        this.params = params;
    }

    protected void setCode(String code) {
        this.code = code;
    }
}
