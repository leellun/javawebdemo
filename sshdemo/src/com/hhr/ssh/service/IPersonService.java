package com.hhr.ssh.service;

import java.util.List;

import com.hhr.ssh.model.Person;

public interface IPersonService {
	public void save(Person person);
	public void update(Person person);
	public void delete(Person person);
	public Person getPerson(Integer id);
	public List<Person> getPersons();
}
