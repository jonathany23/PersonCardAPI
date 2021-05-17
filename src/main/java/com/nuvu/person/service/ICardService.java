package com.nuvu.person.service;

import com.nuvu.person.persistence.entity.Card;
import com.nuvu.person.persistence.entity.Person;

import java.util.Optional;

public interface ICardService {

    Optional<Card> saveCard(Card card);
    Optional<Card> editCard(Card card);
    Optional<Card> getCard(String cardNumber);
    boolean deleteCard(String cardNumber);
}
