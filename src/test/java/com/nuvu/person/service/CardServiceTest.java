package com.nuvu.person.service;

import com.nuvu.person.persistence.entity.Card;
import com.nuvu.person.persistence.repository.CardReposiroty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CardServiceTest {

    @Autowired
    private CardService service;

    @MockBean
    private CardReposiroty reposiroty;

    private Card card;

    @BeforeEach
    private void setUp(){
        card = new Card();
        card.setCardNumber("0987654321123456");
        card.setCardholdername("test create card name");
        card.setExpirationDate("08/25");
        card.setSecurityCode("234");
        card.setPersonId("001");
    }

    @Test
    void saveCard() {
        when(reposiroty.saveCard(any()))
                .thenReturn(Optional.of(card));

        Optional<Card> cardOptional = service.saveCard(this.card);
        assertTrue(cardOptional.isPresent());
        assertEquals("0987654321123456", cardOptional.get().getCardNumber());
    }

    @Test
    void getCard() {
        when(reposiroty.getCardById(any()))
                .thenReturn(Optional.of(card));

        Optional<Card> cardOpt = service.getCard("0987654321123456");
        assertTrue(cardOpt.isPresent());
        assertEquals("0987654321123456", cardOpt.get().getCardNumber());
    }

    @Test
    void deleteCard() {
        assertTrue(service.deleteCard("0987654321123456"));
    }
}