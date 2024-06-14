package com.learn.dream.project.service;

import java.util.Optional;


import com.learn.dream.project.model.Person;

public interface PersonService {

    Person save(Person person);

    Optional<Person> findById(Long id);

    void delete(Person person);

}
