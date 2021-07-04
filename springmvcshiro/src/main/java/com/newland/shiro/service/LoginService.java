package com.newland.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

    public void testMethod(){
        System.out.println("testMethod, time: " + new Date());
        Session session = SecurityUtils.getSubject().getSession();
        Object val = session.getAttribute("key");
        System.out.println("Service SessionVal: " + val);
    }

}
