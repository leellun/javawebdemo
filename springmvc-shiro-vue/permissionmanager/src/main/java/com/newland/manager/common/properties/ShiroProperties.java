package com.newland.manager.common.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ShiroProperties {
    private String anonUrl;

    /**
     * token默认有效时间 1天
     */
    private Long jwtTimeOut = 86400L;
}