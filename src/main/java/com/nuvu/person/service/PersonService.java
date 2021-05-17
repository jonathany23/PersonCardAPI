package com.nuvu.person.service;

import com.nuvu.person.persistence.entity.Person;
import com.nuvu.person.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    public Optional<Person> savePerson(Person person) {
        return repository.savePerson(person);
    }

    @Override
    public Optional<Person> editPerson(Person person) {
        return repository.savePerson(person);
    }

    @Override
    public Optional<Person> getPerson(String legalId) {
        return repository.getPerson(legalId);
    }

    @Override
    public boolean deletePerson(String legalId) {
        repository.deletePerson(legalId);
        return true;
    }
}
