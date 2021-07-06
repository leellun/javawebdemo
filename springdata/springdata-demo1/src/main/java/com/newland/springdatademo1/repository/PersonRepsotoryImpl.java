package com.newland.springdatademo1.repository;

import com.newland.springdatademo1.pojo.Person;
import com.newland.springdatademo1.repository.PersonExtraRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonRepsotoryImpl implements PersonExtraRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void test() {
        Person person = entityManager.find(Person.class, 11);
        System.out.println("-->" + person);
    }

}
