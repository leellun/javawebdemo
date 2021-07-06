package com.newland.springdatademo1.service;

import com.newland.springdatademo1.pojo.Person;
import com.newland.springdatademo1.repository.PersonRepsotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepsotory personRepsotory;

    @Transactional
    public void savePersons(List<Person> persons) {
        personRepsotory.saveAll(persons);
    }

    @Transactional
    public void updatePersonEmail(String email, Integer id) {
        personRepsotory.updatePersonEmail(id, email);
    }
}
