package com.hhr.ssi.service;

import com.hhr.ssi.model.Person;

public interface IPersonService {
	public Person savePerson(Person person);
	public Person updatePerson(Person person);
	public Person getPerson(Integer id);
}
