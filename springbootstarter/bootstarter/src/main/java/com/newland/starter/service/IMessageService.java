package com.newland.starter.service;

public interface IMessageService {
    void sendEmail(String email,String content);
    void sendMessage(String phoneNumber,String content);
}
