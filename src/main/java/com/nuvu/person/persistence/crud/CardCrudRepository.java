package com.nuvu.person.persistence.crud;

import com.nuvu.person.persistence.entity.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardCrudRepository extends CrudRepository<Card, String> {

}
