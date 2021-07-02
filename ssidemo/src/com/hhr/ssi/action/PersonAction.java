package com.hhr.ssi.action;

import com.hhr.ssi.model.Person;
import com.hhr.ssi.service.IPersonService;

public class PersonAction {
	private IPersonService personService;
	public void setPersonService(IPersonService personService){
		this.personService=personService;
	}
	public void  savePerson(Person person){
		personService.savePerson(person);
	}
	public void updatePerson(Person person){
		personService.updatePerson(person);
	}
	public Person getPerson(Integer id){
		return personService.getPerson(id);
	}
}
