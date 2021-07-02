package com.hhr.ssi;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hhr.ssi.action.PersonAction;
import com.hhr.ssi.model.Person;

public class PersonTest {
	private static PersonAction personAction;
	@BeforeClass
	public static void setUpBeforeClass(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		personAction=(PersonAction) context.getBean("personAction");
		System.out.println("234234");
	}
	@Test
	public void savePerson(){
		Person person=new Person();
		person.setCard_id(100);
		person.setName("ssi");
		personAction.savePerson(person);
	}
}
