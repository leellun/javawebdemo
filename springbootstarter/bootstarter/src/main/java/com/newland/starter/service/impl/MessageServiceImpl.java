package com.newland.starter.service.impl;

import com.newland.starter.properties.SystemProperteis;
import com.newland.starter.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private SystemProperteis systemProperteis;

    @Override
    public void sendEmail(String email, String content) {
        System.out.println(String.format("系統賬號%s===>向%s發送郵件：%s", systemProperteis.getEmail(), email, content));
    }

    @Override
    public void sendMessage(String phoneNumber, String content) {
        System.out.println(String.format("系統賬號%s===>向%s發送短信：%s", systemProperteis.getPhone(), phoneNumber, content));
    }
}
