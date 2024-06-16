package com.learn.dream.project.controller;

import com.learn.dream.project.dto.PersonDTO;
import com.learn.dream.project.model.Person;
import com.learn.dream.project.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {

    private final ModelMapper modelMapper;
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDTO> save(@RequestBody @Valid PersonDTO personDTO) {
        try {
            Person person = modelMapper.map(personDTO, Person.class);
            Person savedPerson = personService.save(person);

            var responseDTO = modelMapper.map(savedPerson, PersonDTO.class);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedPerson.getId())
                    .toUri();

            return ResponseEntity.created(location).body(responseDTO);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("path/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {

        return entity;
    }

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return "";
    }

    @DeleteMapping("path")
    public String delete(@RequestParam String param) {
        return "";
    }

}
