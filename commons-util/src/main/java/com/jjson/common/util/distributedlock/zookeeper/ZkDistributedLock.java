package com.jjson.common.util.distributedlock.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author jiangjunshen
 * @date 11:02 AM 2019/4/22
 */
public class ZkDistributedLock {
    
    private static final String LOCK_ROOT = "/distributed/lock";
    
    static {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(LOCK_ROOT, new ExponentialBackoffRetry(1000, 2));
    }
    
    public ZkDistributedLock(String lockInstance) {
        
    }
    
    public void lock() {
        
    }
    
    public void unlock() {
        
    }
}
