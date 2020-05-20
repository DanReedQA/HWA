package com.qa.service;

import com.qa.domain.Card;
import com.qa.dto.CardDTO;
import com.qa.repo.CardsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class CardServiceIntegrationTest {

    @Autowired
    private CardService service;

    @Autowired
    private CardsRepository repository;

    @Autowired
    private ModelMapper mapper;

    private Card testCard;

    private Card testCardWithID;

    private CardDTO mapToDTO(Card card){
        return this.mapper.map(card, CardDTO.class);
    }

    @Before
    public void setUp(){
        this.testCard = new Card("Card1", 5L);
        this.repository.deleteAll();
        this.testCardWithID = this.repository.save(this.testCard);
    }

    @Test
    public void readCardsTest(){
        assertThat(this.service.readCards())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testCardWithID)).collect(Collectors.toList())
                );
    }

    @Test
    public void createCardTest(){
        assertEquals(this.mapToDTO(this.testCardWithID), this.service.createCard(testCard));
    }

    @Test
    public void findCardByIdTest(){
        assertThat(this.service.findCardById(this.testCardWithID.getCardId())).isEqualTo(this.mapToDTO(this.testCardWithID));
    }


    @Test
    public void updateCardTest(){
        Card newCard = new Card("card1", 5L);
        Card updateCard = new Card(newCard.getCardName(), newCard.getValue());
        updateCard.setCardId(this.testCardWithID.getCardId());
        assertThat(this.service.updateCard(this.testCardWithID.getCardId() ,newCard))
                .isEqualTo(this.mapToDTO(updateCard));
    }

    @Test
    public void deleteCardTest(){
        assertThat(this.service.deleteCard(this.testCardWithID.getCardId())).isFalse();
    }


}