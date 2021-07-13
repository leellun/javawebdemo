package com.newland.cms.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.cms.domain.FebsConstant;
import com.newland.cms.domain.QueryRequest;
import com.newland.cms.domain.User;
import com.newland.cms.system.mapper.UserMapper;
import com.newland.cms.system.service.UserService;
import com.newland.cms.utils.SortUtil;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Override
	public User findByName(String username) {
		return baseMapper.findDetail(username);
	}

    @Override
    public IPage<User> findUserDetail(User user, QueryRequest request) {
        try {
            Page<User> page = new Page<>();
            SortUtil.handlePageSort(request, page, "userId", FebsConstant.ORDER_ASC, false);
            return this.baseMapper.findUserDetail(page, user);
        } catch (Exception e) {
            log.error("查询用户异常", e);
            return null;
        }
    }
    
}
