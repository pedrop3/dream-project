package com.learn.dream.project.controller;

import com.learn.dream.project.dto.PersonCreateDTO;
import com.learn.dream.project.dto.PersonResponseDTO;
import com.learn.dream.project.model.Person;
import com.learn.dream.project.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponseDTO> createPerson(@RequestBody @Valid PersonCreateDTO personCreateDTO) {
        Person person = Person.builder()
                .name(personCreateDTO.name())
                .build();

        Person savedPerson = personService.save(person);

        var responseDTO = new PersonResponseDTO(savedPerson.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPerson.getId())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }
}

