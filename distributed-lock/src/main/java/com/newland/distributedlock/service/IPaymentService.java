package com.newland.distributedlock.service;

import java.math.BigDecimal;

public interface IPaymentService {
    String payment(String account, BigDecimal money,String orderId);
    String payment2(String account, BigDecimal money,String orderId);
}
