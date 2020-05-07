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
public class CardServiceIntegrationTest {

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
        this.testCard = new Card("Card1", "Common", 50L, 5L);
        this.repository.deleteAll();
        this.testCardWithID = this.repository.save(this.testCard);
    }

    @Test
    public void readNotesTest(){
        assertThat(this.service.readCards())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testCardWithID)).collect(Collectors.toList())
                );
    }

    @Test
    public void createNoteTest(){
        assertEquals(this.mapToDTO(this.testCardWithID), this.service.createCard(testCard));
    }

    @Test
    public void findNoteByIdTest(){
        assertThat(this.service.findCardById(this.testCardWithID.getCardId())).isEqualTo(this.mapToDTO(this.testCardWithID));
    }


//    @Test
//    public void updateNoteTest(){
//        Note newNote = new Note("Favourite movies", "The grinch");
//        Note updateNote = new Note(newNote.getTitle(), newNote.getDescription());
//        updateNote.setId(this.testNoteWithID.getId());
//        assertThat(this.service.updateNote(this.testNoteWithID.getId() ,newNote))
//                .isEqualTo(this.mapToDTO(updateNote));
//    }

    @Test
    public void deleteNoteTest(){
        assertThat(this.service.deleteCard(this.testCardWithID.getCardId())).isFalse();
    }


}