package com.newland.distributedlock.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DbLock {
    void lock(@Param("methodName") String methodName);

    void unLock(@Param("methodName") String methodName);
}
