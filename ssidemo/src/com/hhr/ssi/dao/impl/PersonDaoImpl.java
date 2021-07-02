package com.hhr.ssi.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;

import com.hhr.ssi.dao.IPersonDao;
import com.hhr.ssi.model.Person;

public class PersonDaoImpl implements IPersonDao{
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession){
		this.sqlSession=sqlSession;
	}
	@Override
	public void savePerson(Person person) {
		sqlSession.update("com.hhr.ssi.model.Person.savePerson", person);
	}

	@Override
	public void updatePerson(Person person) {
		sqlSession.update("com.hhr.ssi.model.Person.updatePerson", person);
	}

	@Override
	public Person getPerson(Integer id) {
		return sqlSession.selectOne("com.hhr.ssi.model.Person.getPerson", id);
	}

}
