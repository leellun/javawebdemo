package com.newland.starter.config;

import com.newland.starter.properties.SystemProperteis;
import com.newland.starter.service.IMessageService;
import com.newland.starter.service.impl.MessageServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SystemProperteis.class})
@ConditionalOnProperty(prefix = "newland",name = "enable",havingValue = "true")
public class SystemConfig {
    @Bean
    public IMessageService messageService(){
        return new MessageServiceImpl();
    }
}
