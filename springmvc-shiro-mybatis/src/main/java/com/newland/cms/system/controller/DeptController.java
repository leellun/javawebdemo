package com.newland.cms.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newland.cms.domain.Dept;
import com.newland.cms.domain.QueryRequest;
import com.newland.cms.system.service.DeptService;

@RestController
@RequestMapping("/dept")
public class DeptController{
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Map<String, Object> deptList(QueryRequest request, Dept dept) {
        return this.deptService.findDepts(request, dept);
    }
}
