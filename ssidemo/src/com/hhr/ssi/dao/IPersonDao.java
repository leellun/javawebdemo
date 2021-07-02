package com.hhr.ssi.dao;

import com.hhr.ssi.model.Person;

public interface IPersonDao {
	public void savePerson(Person person);
	public void updatePerson(Person person);
	public Person getPerson(Integer id);
}
