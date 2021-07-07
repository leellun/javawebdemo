package com.newland.distributedlock.config;

import lombok.extern.java.Log;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Log
@Component
public class ZooKeeperSession {
    @Autowired
    private ZkProperties zkProperties;
    private boolean isConnecting;

    private ZooKeeper zookeeper;

    @PostConstruct
    public void init() {
        try {
            this.zookeeper = new ZooKeeper(
                    zkProperties.getConnectString(),
                    zkProperties.getCurator().getSessionTimeoutMs(),
                    new ZooKeeperWatcher());
            log.info("ZooKeeper session established......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立zk session的watcher：
     */
    private class ZooKeeperWatcher implements Watcher {

        public void process(WatchedEvent event) {
            if (KeeperState.SyncConnected == event.getState()) {
                isConnecting = true;
            }
        }

    }


    /**
     * 重试获取分布式锁：
     *
     * @param adId
     */
    public void acquireDistributedLock(Long adId) {
        String path = "/ad-lock-" + adId;
        try {
            zookeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            log.info("success to acquire lock for adId = " + adId);
        } catch (Exception e) {
            int count = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                    zookeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (Exception e2) {
                    count++;
                    log.info("the " + count + " times try to acquire lock for adId = " + adId);
                    continue;
                }
                log.info("success to acquire lock for adId = " + adId + " after " + count + " times try......");
                break;
            }
        }
    }

    /**
     * 释放掉分布式锁：
     *
     * @param adId
     */
    public void releaseDistributedLock(Long adId) {
        String path = "/ad-lock-" + adId;
        try {
            zookeeper.delete(path, -1);
            log.info("release the lock for adId = " + adId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
