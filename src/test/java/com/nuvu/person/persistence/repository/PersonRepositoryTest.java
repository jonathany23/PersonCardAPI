package com.nuvu.person.persistence.repository;

import com.nuvu.person.persistence.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.xml.crypto.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Test
    void savePerson() {
        Person person = new Person();
        person.setLegalIdType("CC");
        person.setLegalId("002");
        person.setName("testname");
        person.setLastName("tetslastname");
        person.setPhone("038982382");
        person.setBirthDate(new Date());

        Optional<Person> personOptional = repository.savePerson(person);

        assertTrue(personOptional.isPresent());
        assertEquals("002", personOptional.get().getLegalId());
    }

    @Test
    void getPerson() {
        Optional<Person> personOptional = repository.getPerson("001");

        assertTrue(personOptional.isPresent());
        assertEquals("001", personOptional.get().getLegalId());
    }

    @Test
    void getPerson_id_not_found() {
        Optional<Person> personOptional = repository.getPerson("002");

        assertFalse(personOptional.isPresent());
    }

    @Test
    void deletePerson_ok() {
        assertDoesNotThrow(() -> repository.deletePerson("002"));
    }

    @Test
    void deletePerson_id_not_found() {
        assertThrows(EmptyResultDataAccessException.class, () -> repository.deletePerson("003"));
    }
}