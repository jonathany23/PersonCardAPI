package com.nuvu.person.persistence.repository;

import com.nuvu.person.persistence.entity.Card;
import com.nuvu.person.persistence.entity.Person;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CardReposirotyTest {

    @Autowired
    private CardReposiroty cardReposiroty;

    private Person person;

    @BeforeEach
    private void setUp(){
        person = new Person();
        person.setLegalIdType("CC");
        person.setLegalId("002");
        person.setName("testname");
        person.setLastName("tetslastname");
        person.setPhone("038982382");
        person.setBirthDate(new Date());
    }

    @Test
    @Order(1)
    void saveCard() {
        Card card = new Card();
        card.setCardNumber("0987654321123457");
        card.setCardholdername("test create card name");
        card.setExpirationDate("08/25");
        card.setSecurityCode("234");
        card.setPersonId("001");
        cardReposiroty.saveCard(card);
    }

    @Test
    @Order(2)
    void getCardById() {
        Optional<Card> card = cardReposiroty.getCardById("0987654321123457");
        assertNotNull(card.isPresent());
        assertEquals("0987654321123457", card.get().getCardNumber());
    }

    @Test
    @Order(3)
    void deleteCard_ok() {
        assertDoesNotThrow(() ->cardReposiroty.deleteCard("0987654321123457"));
    }

    @Test
    @Order(4)
    void deleteCard_error() {
        assertThrows(EmptyResultDataAccessException.class, () -> cardReposiroty.deleteCard("0987654321123453"));
    }
}