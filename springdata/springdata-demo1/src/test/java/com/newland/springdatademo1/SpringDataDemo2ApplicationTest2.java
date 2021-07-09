package com.newland.springdatademo1;

import com.newland.springdatademo1.custom.AddressRepository;
import com.newland.springdatademo1.repository.PersonRepository;
import com.newland.springdatademo1.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDataDemo2ApplicationTest2 {
    private ApplicationContext ctx = null;
    private AddressRepository addressRepository;

    {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext2.xml");
        addressRepository = ctx.getBean(AddressRepository.class);
    }

    /**
     * 自定义JpaRepositoryFactoryBean
     */
    @Test
    public void testCommonCustomRepositoryMethod() {
        addressRepository.method();
    }
}
