package com.jjson.common.util.consistenthash;

import java.util.Arrays;

/**
 * @author jiangjunshen
 * @date 5:24 PM 2019/4/20
 */
public class ConsistentHashTest {
    public static void main(String[] args) {
        String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};
        ConsistentHash consistentHash = new ConsistentHash(Arrays.asList(servers));

        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
        for (String node : nodes) {
            System.out.println("[" + node + "]被路由到结点[" + consistentHash.select(node) + "]");
        }
    }
}
