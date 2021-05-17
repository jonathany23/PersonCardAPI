package com.nuvu.person.service;

import com.nuvu.person.persistence.entity.Card;
import com.nuvu.person.persistence.repository.CardReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService implements ICardService {

    @Autowired
    private CardReposiroty reposiroty;

    @Override
    public Optional<Card> saveCard(Card card) {
        return reposiroty.saveCard(card);
    }

    @Override
    public Optional<Card> editCard(Card card) {
        return reposiroty.editCard(card);
    }

    @Override
    public Optional<Card> getCard(String cardNumber) {
        return reposiroty.getCardById(cardNumber);
    }

    @Override
    public boolean deleteCard(String cardNumber) {
        reposiroty.deleteCard(cardNumber);
        return true;
    }
}
