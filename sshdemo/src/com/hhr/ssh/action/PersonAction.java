package com.hhr.ssh.action;

import javax.annotation.PostConstruct;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hhr.ssh.model.Person;
import com.hhr.ssh.service.IPersonService;
import com.opensymphony.xwork2.ActionContext;

public class PersonAction {
	private IPersonService personService;
	public void setPersonService(IPersonService personService){
		this.personService=personService;
	}
	public String execute() {
		Person person=personService.getPerson(6);
		person=personService.getPerson(6);
		person=personService.getPerson(6);
		ActionContext actionContext=ActionContext.getContext();
		actionContext.put("person", person);
		return "success";
	}
}
