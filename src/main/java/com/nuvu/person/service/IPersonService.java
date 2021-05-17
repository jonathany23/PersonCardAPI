package com.nuvu.person.service;

import com.nuvu.person.persistence.entity.Person;

import java.util.Optional;

public interface IPersonService {

    Optional<Person> savePerson(Person person);
    Optional<Person> editPerson(Person person);
    Optional<Person> getPerson(String legalId);
    boolean deletePerson(String legalId);
}
