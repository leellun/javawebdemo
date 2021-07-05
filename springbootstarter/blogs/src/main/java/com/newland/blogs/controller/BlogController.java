package com.newland.blogs.controller;

import com.newland.starter.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {
    @Autowired
    private IMessageService messageService;

    @RequestMapping("send")
    @ResponseBody
    public String sendEmailAndSMS(@RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("content") String content) {
        messageService.sendEmail(email, content);
        messageService.sendMessage(phone, content);
        return "邮件短信发送成功";
    }
}
