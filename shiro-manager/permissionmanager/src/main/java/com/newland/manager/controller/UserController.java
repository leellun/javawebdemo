package com.newland.manager.controller;


import com.newland.manager.common.Response;
import com.newland.manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService service;
    @PostMapping("/login")
    @ResponseBody
    public Response login(String username, String password){
        return service.login(username,password);
    }

}

