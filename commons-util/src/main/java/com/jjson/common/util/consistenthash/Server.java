package com.jjson.common.util.consistenthash;

/**
 * @author jiangjunshen
 * @date 2:42 PM 2019/4/16
 */
public class Server {
    
    private String host;
    
    private Integer port;
    
    public Server(String host) {
        this.host = host;
    }
    
    public Server(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return host + ":" + port;
    }
}
