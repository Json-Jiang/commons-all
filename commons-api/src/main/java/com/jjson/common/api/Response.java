package com.jjson.common.api;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author jiangjunshen
 * @date 9:47 AM 2019/10/31
 */
@Data
public class Response<T> implements Serializable {

    /**
     * 必填 是否成功标识
     */
    private boolean success;

    /**
     * 非必填 返回结果集
     */
    private T result;

    /**
     * 失败时必填 业务码/失败码 全局需唯一
     */
    private String code;

    /**
     * 失败时必填 业务文案/失败文案提示
     */
    private String message;

    /**
     * 元数据，额外信息
     */
    private Map<String, Object> metaData;

    public boolean isSuccess() {
        return this.success;
    }

    public static <T> Response<T> ok(T data) {
        Response<T> resp = new Response<>();
        resp.setResult(data);
        resp.setSuccess(true);
        return resp;
    }

    public static <T> Response<T> ok() {
        return ok((T) null);
    }

    public static <T> Response<T> fail(String code, String message) {
        Response<T> resp = new Response<>();
        resp.setCode(code);
        resp.setMessage(message);
        resp.setSuccess(false);
        return resp;
    }

    public static <T> Response<T> failOfMessage(String message) {
        Response<T> resp = new Response<>();
        resp.setMessage(message);
        resp.setSuccess(false);
        return resp;
    }

    public static <T> Response<T> fail(String code) {
        Response<T> resp = new Response<>();
        resp.setCode(code);
        resp.setSuccess(false);
        return resp;
    }

    public static <T> Response<T> fail(String code, String message, Map<String, Object> metaData) {
        Response<T> resp = new Response<>();
        resp.setCode(code);
        resp.setMessage(message);
        resp.setSuccess(false);
        resp.setMetaData(metaData);
        return resp;
    }
}
