package com.jjson.common.exception.aspect;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jiangjunshen
 * @date 9:51 PM 2019/4/10
 */
@Data
public class Result<T> implements Serializable {
    
    private String code;
    
    private String message;
    
    private Boolean success;
    
    private T data;

    public boolean isSuccess() {
        return this.success;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setSuccess(true);
        return result;
    }
    
    public static <T> Result<T> fail(String code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }
    
    public static <T> Result<T> fail(String code) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setSuccess(false);
        result.setMessage(ErrorConstants.DEFAULT_MESSAGE);
        return result;
    }
}
