package com.newland.distributedlock.service.impl;

import com.alibaba.druid.sql.parser.Token;
import com.newland.distributedlock.mapper.DbLock;
import com.newland.distributedlock.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("paymentService")
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private DbLock dbLock;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String payment(String account, BigDecimal money, String orderId) {
        boolean isLock = false;
        try {
            dbLock.lock(orderId);
            isLock = true;
            Thread.sleep(5000);
            log.info("支付流程正常执行成功了" + Thread.currentThread());
            return "执行完成了";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("当前上锁了");
            return "锁具被上锁";
        } finally {
            if (isLock) {
                dbLock.unLock(orderId);
            }
        }
    }

    @Override
    public String payment2(String account, BigDecimal money, String orderId) {
        boolean lock = false;
        try {
            lock = redisTemplate.opsForValue().setIfAbsent(orderId, Token.LOCK);
            log.info("cancelCouponCode是否获取到锁：" + lock);
            if (lock) {
                try{
                    Thread.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisTemplate.expire(orderId, 1, TimeUnit.MINUTES); //成功设置过期时间
                return "执行完成了";
            } else {
                log.info("cancelCouponCode没有获取到锁，不执行任务!");
                return "锁具被上锁";
            }
        } finally {
            if (lock) {
                redisTemplate.delete(orderId);
                log.info("cancelCouponCode任务结束，释放锁!");
            } else {
                log.info("cancelCouponCode没有获取到锁，无需释放锁!");
            }
        }
    }
}
