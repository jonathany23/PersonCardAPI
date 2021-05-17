package com.nuvu.person.persistence.crud;

import com.nuvu.person.persistence.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonCrudRepository extends CrudRepository<Person, String> {
}
