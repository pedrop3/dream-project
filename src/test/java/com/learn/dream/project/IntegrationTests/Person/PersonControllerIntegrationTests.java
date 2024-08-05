package com.learn.dream.project.IntegrationTests.Person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.dream.project.controller.PersonController;
import com.learn.dream.project.dto.PersonDTO;
import com.learn.dream.project.model.Person;
import com.learn.dream.project.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PersonControllerIntegrationTests {

    private final String BASE_URL = "/persons";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldSavePerson() throws  Exception {
        var personDTO = buildPersonDTO();
        var personMock = buildPerson();

        when(personService.save(any(Person.class)))
                .thenReturn(personMock);

        mockMvc.perform(
                        post(BASE_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(personDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnBadRequestForInvalidData() throws JsonProcessingException, Exception {
        PersonDTO personDTO = new PersonDTO(null, null);

        mockMvc.perform(
                        post(BASE_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(personDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldHandleServiceException() throws Exception {
        var personDTO = buildPersonDTO();
        // configure personDTO with valid data

        doThrow(new RuntimeException("Service error")).when(personService).save(any(Person.class));

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDTO)))
                .andExpect(status().isInternalServerError());
    }


    private Person buildPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setName("Pedro Santos");

        return person;

    }

    private PersonDTO buildPersonDTO() {
        return new PersonDTO(null,"Pedro Santos");
    }

}
