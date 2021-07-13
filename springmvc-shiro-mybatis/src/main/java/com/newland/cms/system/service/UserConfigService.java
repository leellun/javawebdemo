package com.newland.cms.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.cms.domain.UserConfig;

public interface UserConfigService extends IService<UserConfig>{
    /**
     * 通过用户 ID 获取前端系统个性化配置
     *
     * @param userId 用户 ID
     * @return 前端系统个性化配置
     */
    UserConfig findByUserId(String userId);
}
