package com.newland.cms.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.cms.domain.Dict;
import com.newland.cms.system.mapper.DictMapper;
import com.newland.cms.system.service.DictService;

@Service("dictService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public IPage<Dict> findDicts(Dict dict) {
        try {
            LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();

            if (!StringUtils.isEmpty(dict.getKeyy())) {
                queryWrapper.eq(Dict::getKeyy, dict.getKeyy());
            }
            if (!StringUtils.isEmpty(dict.getValuee())) {
                queryWrapper.eq(Dict::getValuee, dict.getValuee());
            }
            if (!StringUtils.isEmpty(dict.getTableName())) {
                queryWrapper.eq(Dict::getTableName, dict.getTableName());
            }
            if (!StringUtils.isEmpty(dict.getFieldName())) {
                queryWrapper.eq(Dict::getFieldName, dict.getFieldName());
            }

            Page<Dict> page = new Page<>();
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取字典信息失败", e);
            return null;
        }
    }
}
