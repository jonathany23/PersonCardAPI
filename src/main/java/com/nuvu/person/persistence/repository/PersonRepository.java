package com.nuvu.person.persistence.repository;

import com.nuvu.person.persistence.crud.PersonCrudRepository;
import com.nuvu.person.persistence.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonRepository {

    @Autowired
    private PersonCrudRepository personCrudRepository;

    public Optional<Person> savePerson(Person person){
        return Optional.of(personCrudRepository.save(person));
    }

    public Optional<Person> editPerson(Person person){
        return Optional.of(personCrudRepository.save(person));
    }

    public Optional<Person> getPerson(String legal_id){
        return personCrudRepository.findById(legal_id);
    }

    public void deletePerson(String legal_id){
        personCrudRepository.deleteById(legal_id);
    }
}
