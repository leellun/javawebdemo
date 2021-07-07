package com.newland.distributedlock.controller;

import com.newland.distributedlock.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/paymentController")
public class PaymentController {
    @Autowired
    @Qualifier("paymentService")
    private IPaymentService paymentService;

    @RequestMapping("/payment")
    public String payment(@RequestParam("account") String account, @RequestParam("money") BigDecimal money, @RequestParam("orderId") String orderId) {
        return paymentService.payment(account, money, orderId);
    }

    @RequestMapping("/payment2")
    public String payment2(@RequestParam("account") String account, @RequestParam("money") BigDecimal money, @RequestParam("orderId") String orderId) {
        return paymentService.payment2(account, money, orderId);
    }
}
