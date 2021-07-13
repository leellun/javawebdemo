package com.newland.cms.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.cms.domain.Dept;
import com.newland.cms.domain.FebsConstant;
import com.newland.cms.domain.QueryRequest;
import com.newland.cms.domain.Tree;
import com.newland.cms.system.mapper.DeptMapper;
import com.newland.cms.system.service.DeptService;
import com.newland.cms.utils.SortUtil;
import com.newland.cms.utils.TreeUtil;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService{
    @Override
    public Map<String, Object> findDepts(QueryRequest request, Dept dept) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Dept> depts = findDepts(dept, request);
            List<Tree<Dept>> trees = new ArrayList<>();
            buildTrees(trees, depts);
            Tree<Dept> deptTree = TreeUtil.build(trees);

            result.put("rows", deptTree);
            result.put("total", depts.size());
        } catch (Exception e) {
            log.error("获取部门列表失败", e);
            result.put("rows", null);
            result.put("total", 0);
        }
        return result;
    }
    @Override
    public List<Dept> findDepts(Dept dept, QueryRequest request) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(dept.getDeptName()))
            queryWrapper.lambda().eq(Dept::getDeptName, dept.getDeptName());
        if (!StringUtils.isEmpty(dept.getCreateTimeFrom()) && !StringUtils.isEmpty(dept.getCreateTimeTo()))
            queryWrapper.lambda()
                    .ge(Dept::getCreateTime, dept.getCreateTimeFrom())
                    .le(Dept::getCreateTime, dept.getCreateTimeTo());
        SortUtil.handleWrapperSort(request, queryWrapper, "orderNum", FebsConstant.ORDER_ASC, true);
        return this.baseMapper.selectList(queryWrapper);
    }
    private void buildTrees(List<Tree<Dept>> trees, List<Dept> depts) {
        depts.forEach(dept -> {
            Tree<Dept> tree = new Tree<>();
            tree.setId(dept.getDeptId().toString());
            tree.setKey(tree.getId());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getDeptName());
            tree.setCreateTime(dept.getCreateTime());
            tree.setModifyTime(dept.getModifyTime());
            tree.setOrder(dept.getOrderNum());
            tree.setTitle(tree.getText());
            tree.setValue(tree.getId());
            trees.add(tree);
        });
    }
}
