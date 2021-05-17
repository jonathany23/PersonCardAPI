package com.nuvu.person.persistence.repository;

import com.nuvu.person.persistence.crud.CardCrudRepository;
import com.nuvu.person.persistence.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CardReposiroty {

    @Autowired
    private CardCrudRepository cardCrudRepository;

    public Optional<Card> saveCard(Card card) {
        return Optional.of(cardCrudRepository.save(card));
    }

    public Optional<Card> editCard(Card card){
        return Optional.of(cardCrudRepository.save(card));
    }

    public Optional<Card> getCardById(String cardNumber){
        return cardCrudRepository.findById(cardNumber);
    }

    public void deleteCard(String cardNumber){
        cardCrudRepository.deleteById(cardNumber);
    }
}
