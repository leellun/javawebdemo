package com.newland.manager.controller;


import com.newland.manager.common.Response;
import com.newland.manager.domain.LoginDTO;
import com.newland.manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService service;

    @RequestMapping("/login")
    @ResponseBody
    public Response login(@RequestBody LoginDTO loginDTO) {
        return service.login(loginDTO.getUsername(), loginDTO.getPassword());
    }

    @RequestMapping("/info")
    @ResponseBody
    @PreAuthorize("home")
    public Response info() {
        return service.info(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    @RequestMapping("/list")
    @ResponseBody
    @PreAuthorize("user-manage")
    public Response list(){
        return service.list(SecurityContextHolder.getContext().getAuthentication().toString());
    }
}

