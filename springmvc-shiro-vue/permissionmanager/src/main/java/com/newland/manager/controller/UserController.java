package com.newland.manager.controller;


import com.newland.manager.common.Response;
import com.newland.manager.domain.LoginDTO;
import com.newland.manager.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
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

    @RequestMapping("/login")
    @ResponseBody
    public Response login(@RequestBody LoginDTO loginDTO) {
        return service.login(loginDTO.getUsername(), loginDTO.getPassword());
    }

    @RequiresUser
    @RequestMapping("/info")
    @ResponseBody
    public Response info() {
        return service.info(SecurityUtils.getSubject().getPrincipal().toString());
    }
    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions("user-manage")
    public Response list(){
        return service.list(SecurityUtils.getSubject().getPrincipal().toString());
    }
}

