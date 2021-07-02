package com.hhr.ssi.service.impl;

import com.hhr.ssi.dao.IPersonDao;
import com.hhr.ssi.model.Person;
import com.hhr.ssi.service.IPersonService;

public class PersonServiceImpl implements IPersonService {
	private IPersonDao personDao;

	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public Person savePerson(Person person) {
		personDao.savePerson(person);
		return person;
	}

	@Override
	public Person updatePerson(Person person) {
		personDao.updatePerson(person);
		return person;
	}

	@Override
	public Person getPerson(Integer id) {
		Person person = personDao.getPerson(id);
		return person;
	}

}
