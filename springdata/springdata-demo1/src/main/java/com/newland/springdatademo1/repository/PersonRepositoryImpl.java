package com.newland.springdatademo1.repository;

import com.newland.springdatademo1.pojo.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 这里PersonRepository和PersonRepositoryImpl必须保持名字的前缀一样 既 前缀Person 后面分别是Repository和RepositoryImpl
 *  @Repository 注解，如果PersonRepository和PersonRepositoryImpl不在同一个包下面就需要
 */
public class PersonRepositoryImpl implements PersonDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void test() {
    Person person = entityManager.find(Person.class, 11);
    System.out.println("-->" + person);
  }

}
