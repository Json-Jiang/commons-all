package com.jjson.common.util.helper;

import java.net.MalformedURLException;

/**
 * @author jiangjunshen
 * @date 4:08 PM 2019/10/21
 */
public class UrlUtils {

    /**
     * 是否URL地址
     */
    public static Boolean isValidUrl(String url) {
        if (url == null || url.length() == 0) {
            return true;
        }
        try {
            new java.net.URL(url);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }
}
