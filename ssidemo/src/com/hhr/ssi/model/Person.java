package com.hhr.ssi.model;

public class Person {
	// Fields

	private Integer id;
	private String name;
	private Integer card_id;

	// Constructors

	/** default constructor */
	public Person() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCard_id() {
		return card_id;
	}

	public void setCard_id(Integer card_id) {
		this.card_id = card_id;
	}
	

}
