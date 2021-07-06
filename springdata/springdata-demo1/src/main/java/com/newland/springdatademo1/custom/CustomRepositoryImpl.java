package com.newland.springdatademo1.custom;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

@NoRepositoryBean
public class CustomRepositoryImpl<T, ID extends Serializable>
	extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {

	public CustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	@Override
	public void method() {
		System.out.println("...METHOD TEST...");
	}

}
