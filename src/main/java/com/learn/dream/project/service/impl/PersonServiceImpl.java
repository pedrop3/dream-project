package com.learn.dream.project.service.impl;

import com.learn.dream.project.model.Person;
import com.learn.dream.project.respository.PersonRepository;
import com.learn.dream.project.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person save(Person person) {

        // TODO deixar mais elegante.
        if (person.getName() == null || person.getName().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        return personRepository.save(person);
    }

    @Override
    public Optional<Person> findById(Long personId) {
        return personRepository.findById(personId);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

}
