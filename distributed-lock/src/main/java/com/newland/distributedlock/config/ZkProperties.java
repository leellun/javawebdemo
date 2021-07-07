package com.newland.distributedlock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "zk")
public class ZkProperties {
    private String connectString;
    private CuratorProperties curator;

    @Data
    public static class CuratorProperties {
        private int retryCount;
        private int elapsedTimeMs;
        private int sessionTimeoutMs;
        private int connectionTimeoutMs;
    }
}
