package com.nuvu.person.service;

import com.nuvu.person.persistence.entity.Person;
import com.nuvu.person.persistence.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService service;

    @MockBean
    private PersonRepository repository;

    private Person person;

    @BeforeEach
    private void setUp(){
        person = new Person();
        person.setLegalIdType("CC");
        person.setLegalId("001");
        person.setName("testname");
        person.setLastName("tetslastname");
        person.setPhone("038982382");
        person.setBirthDate(new Date());
    }

    @Test
    void savePerson() {

        when(repository.savePerson(any()))
                .thenReturn(Optional.of(person));

        Optional<Person> personOptional = service.savePerson(person);
        assertNotNull(personOptional.isPresent());
        assertEquals("001", personOptional.get().getLegalId());
    }

    @Test
    void getPerson() {
        when(repository.getPerson(anyString()))
                .thenReturn(Optional.of(person));

        Optional<Person> personOpt = service.getPerson("001");
        assertTrue(personOpt.isPresent());
        assertEquals("001", personOpt.get().getLegalId());
    }

    @Test
    void deletePerson() {
        assertTrue(service.deletePerson("001"));
    }
}