package com.nuvu.person.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuvu.person.dto.AuthenticationRequest;
import com.nuvu.person.dto.AuthenticationResponse;
import com.nuvu.person.persistence.entity.Person;
import com.nuvu.person.service.IPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonAppTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPersonService service;

    @Autowired
    private AuthController authController;

    private static final String jsonRequest = "{\"legalId\":\"002\",\"legalIdType\":\"CC\",\"name\":\"testname\",\"lastName\":\"tetslastname\",\"phone\":\"038982382\",\"birthDate\":\"17/05/2021\"}";

    private Person person;
    private String token;

    @BeforeEach
    private void setUp(){
        person = new Person();
        person.setLegalIdType("CC");
        person.setLegalId("002");
        person.setName("testname");
        person.setLastName("tetslastname");
        person.setPhone("038982382");
        person.setBirthDate(new Date());

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("admin");
        authenticationRequest.setPassword("admin");

        ResponseEntity<AuthenticationResponse> tokenER = authController.createToken(authenticationRequest);
        token = tokenER.getBody().getJwt();
    }

    @Test
    void savePerson() throws Exception {
        doReturn(Optional.of(person))
                .when(service).savePerson(any());

        mockMvc.perform(post("/person/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .header("Authorization", "Bearer "+token))

                .andExpect(status().isOk());
    }

    @Test
    void editPerson() throws Exception {
        doReturn(Optional.of(person))
                .when(service).editPerson(any());

        mockMvc.perform(patch("/person/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .header("Authorization", "Bearer "+token))

                .andExpect(status().isOk());
    }

    @Test
    void getPerson() throws Exception {
        doReturn(Optional.of(person))
                .when(service).getPerson(any());

        mockMvc.perform(get("/person/001")
                .header("Authorization", "Bearer "+token))

                .andExpect(status().isOk());
    }

    @Test
    void deletePerson() throws Exception {
        doReturn(true)
                .when(service).deletePerson(any());

        mockMvc.perform(delete("/person/delete/001")
                .header("Authorization", "Bearer "+token))
                .andExpect(status().isOk());
    }
}