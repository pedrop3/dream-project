package com.learn.dream.project.IntegrationTests.Person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.dream.project.controller.PersonController;
import com.learn.dream.project.dto.PersonDTO;
import com.learn.dream.project.model.Person;
import com.learn.dream.project.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    @MockBean
    private  ModelMapper modelMapper;

    @Test
    public void shouldSavePerson() throws JsonProcessingException, Exception {
        var personDTO = buildPersonDTO();
        var personMock = buildPerson();

        when(personService.save(any(Person.class)))
                .thenReturn(personMock);

        mockMvc.perform(
                        post(BASE_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(personDTO)))
                .andExpect(status().isOk());

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

    @Test
    public void savePersonSuccess() throws Exception {
        PersonDTO personDTO = new PersonDTO();
        // configure o personDTO com os dados necessários para o teste
        personDTO.setName("John Doe");
        // adicione outros campos conforme necessário

        String personJson = objectMapper.writeValueAsString(personDTO);

        MvcResult result = mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson))
                .andExpect(status().isCreated())
                .andReturn();

        // Você pode adicionar verificações adicionais aqui para garantir que a resposta seja correta
    }

    @Test
    public void savePersonConflict() throws Exception {

        Person existingPerson = new Person();
        existingPerson.setName("John Doe");

        personService.save(existingPerson);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("John Doe");
        // adicione outros campos conforme necessário

        String personJson = objectMapper.writeValueAsString(personDTO);

        mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson))
                .andExpect(status().isConflict());
    }

    @Test
    public void savePersonInternalServerError() throws Exception {
        PersonDTO personDTO = new PersonDTO();
        // configure o personDTO com dados que causem um erro no serviço
        personDTO.setName(null);  // Por exemplo, nome nulo pode causar uma falha de validação

        String personJson = objectMapper.writeValueAsString(personDTO);

        mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson))
                .andExpect(status().isInternalServerError());
    }

    private Person buildPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setName("Pedro Santos");

        return person;

    }

    private PersonDTO buildPersonDTO() {
        return new PersonDTO(1L, "Pedro Santos");

    }

}
