package com.newland.springdatademo.methodtest;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

//@NoRepositoryBean
public class CommonMethodTestImpl<T, ID extends Serializable> 
	extends SimpleJpaRepository<T, ID> implements CommonMethodTest<T, ID> {

	public CommonMethodTestImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	@Override
	public void method() {
		System.out.println("...METHOD TEST...");
	}

}
