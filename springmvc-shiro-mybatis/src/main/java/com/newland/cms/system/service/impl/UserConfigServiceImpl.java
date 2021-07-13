package com.newland.cms.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.cms.domain.UserConfig;
import com.newland.cms.system.mapper.UserConfigMapper;
import com.newland.cms.system.service.UserConfigService;
@Service("userConfigService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserConfigServiceImpl extends ServiceImpl<UserConfigMapper, UserConfig> implements UserConfigService {

    @Override
    public UserConfig findByUserId(String userId) {
        return baseMapper.selectById(userId);
    }
}
