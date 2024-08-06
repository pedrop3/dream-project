package com.learn.dream.project.service;

import com.learn.dream.project.model.Person;
import com.learn.dream.project.respository.PersonRepository;
import com.learn.dream.project.service.impl.PersonServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTests {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave_ValidPerson() {

        Person person = new Person();
        person.setName("Pedro Santos");

        when(personRepository.save(person)).thenReturn(person);

        Person savedPerson = personService.save(person);


        assertNotNull(savedPerson);
        assertEquals("Pedro Santos", savedPerson.getName());
        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void testSave_PersonWithoutName() {

        Person person = new Person();
        person.setName(null);

        assertThrows(IllegalArgumentException.class,() -> personService.save(person));
    }

}
