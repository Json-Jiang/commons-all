package com.jjson.common.util.consistenthash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author jiangjunshen
 * @date 2:07 PM 2019/4/16
 */
public class ConsistentHash {
    
    private final TreeMap<Long, String> servers = new TreeMap<>();

    /**
     * 默认节点分裂的数量，需要为4的倍数，因为MD5生成的128位(16字节)会hash成4个long值，默认为16
     */
    private Integer virtualScale;
    
    public ConsistentHash(List<String> hosts) {
        this(hosts, 16);
    }
    
    public String select(Object key) {
        if (servers.isEmpty()) {
            throw new IllegalStateException("Servers is empty.");
        }
        byte[] digest = md5(key.toString());
        long hash = hash(digest, 0);
        return selectForKey(hash);
    }
    
    private String selectForKey(Long key) {
        Map.Entry<Long, String> ceilingEntry = servers.ceilingEntry(key);
        if (null == ceilingEntry) {
            ceilingEntry = servers.firstEntry();
        }
        System.out.println("Key[hash=" + key + "] is routed to server[host=" + ceilingEntry.getValue() + ", hash=" + ceilingEntry.getKey() + "]");
        return ceilingEntry.getValue();
    }
    
    public ConsistentHash(List<String> hosts, Integer virtualScale) {
        this.virtualScale = virtualScale;
        addHost(hosts);
    }

    public void addHost(List<String> hosts) {
        synchronized (servers) {
            for (String host : hosts) {
                for (int i = 0; i < virtualScale / 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        byte[] digest = md5(host);
                        long hash = hash(digest, j);
                        servers.put(hash, host);
                        System.out.println("Add host[" + host + "&&" + j + "], hash[" + hash + "]");
                    }
                }
            }
        }
    }
    
    public void removeHost(List<String> hosts) {
        synchronized (servers) {
            for (String host : hosts) {
                for (int i = 0; i < virtualScale / 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        byte[] digest = md5(host);
                        long hash = hash(digest, j);
                        // 考虑极小可能下的hash冲突
                        servers.remove(hash, host);
                    }
                }
            }
        }
    }
    
    /**
     * 参考dubbo实现的hash
     * @param digest MD5计算后得到的字节数组
     * @param number 分段数字，最高为3
     * @return hash值
     */
    private long hash(byte[] digest, int number) {
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;
    }
    
    private byte[] md5(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.reset();
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        md5.update(bytes);
        return md5.digest();
    }
}
