package com.newland.cms.system.service;

import java.util.List;
import java.util.Map;

import com.newland.cms.domain.Dept;
import com.newland.cms.domain.QueryRequest;

public interface DeptService {
    Map<String, Object> findDepts(QueryRequest request, Dept dept);
    List<Dept> findDepts(Dept dept, QueryRequest request);
}
