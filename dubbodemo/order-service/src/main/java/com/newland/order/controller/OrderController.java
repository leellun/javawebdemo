package com.newland.order.controller;

import com.newland.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orderController")
public class OrderController {
    @Autowired
    private UserService userService;
    @RequestMapping("/getOrder")
    @ResponseBody
    public String getOrderDetail(@RequestParam("username") String username){
        return userService.getUser(username)+"==>order:2234234234";
    }
}
