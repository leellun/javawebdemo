package com.hhr.ssh.service;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hhr.ssh.model.Person;

public class PersonServiceTest {
	private static IPersonService personService;

	@BeforeClass
	public static void setUpBeforeClass() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		personService = (IPersonService) context.getBean("personService");
	}

	@Test
	public void testSave() {
		Person person=personService.getPerson(6);
		person.setName("jdbc 无效的cardid无效2");
		person.setCard_id(1002);
		personService.save(person);
	}

	@Test
	public void testUpdate() {

	}

	@Test
	public void testDelete() {
		Person person=new Person();
		person.setId(6);
		personService.delete(person);
	}

	@Test
	public void testGetPerson() {
		try {
			Person person=personService.getPerson(6);
			System.out.println(""+person.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetPersons() {

	}
}
