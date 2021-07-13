package com.newland.manager.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "manager")
public class PManagerProperties {
    private ShiroProperties shiro;
}
