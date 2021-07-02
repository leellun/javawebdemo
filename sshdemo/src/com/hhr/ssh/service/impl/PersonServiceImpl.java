package com.hhr.ssh.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hhr.ssh.model.Person;
import com.hhr.ssh.service.IPersonService;

public class PersonServiceImpl implements IPersonService{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	public Session getCurrentSession(){
		try {
			return sessionFactory.getCurrentSession();
		} catch (Exception e) {
			return sessionFactory.openSession();
		}
	}
	@Override
	public void save(Person person) {
		getCurrentSession().persist(person);
	}

	@Override
	public void update(Person person) {
		getCurrentSession().update(person);
	}

	@Override
	public void delete(Person person) {
		getCurrentSession().delete(person);
	}

	@Override
	public Person getPerson(Integer id) {
		return (Person) getCurrentSession().get(Person.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public java.util.List<Person> getPersons() {
		return getCurrentSession().createSQLQuery("FROM person").list();
	}

}
