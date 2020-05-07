package com.qa.service;

import com.qa.domain.Card;
import com.qa.dto.CardDTO;
import com.qa.exceptions.CardNotFoundException;
import com.qa.repo.CardsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CardServiceUnitTest {

    @InjectMocks
    private CardService service;

    @Mock
    private CardsRepository repository;

    @Mock
    private ModelMapper mapper;

    private List<Card> cardList;

    private Card testCard;

    private long cardId = 1L;

    private Card testCardWithID;

    private CardDTO cardDTO;

    private CardDTO mapToDTO(Card card){
        return this.mapper.map(card, CardDTO.class);
    }

    @Before
    public void setUp() {
        this.cardList = new ArrayList<>();
        this.testCard = new Card("Card1", "Common", 50L, 5L);
        this.cardList.add(testCard);
        this.testCardWithID = new Card(testCard.getCardName(), testCard.getRarity(), testCard.getStock(), testCard.getValue());
        this.testCardWithID.setCardId(cardId);
        this.cardDTO = this.mapToDTO(testCardWithID);
    }

    @Test
    public void getAllCardsTest(){
        when(repository.findAll()).thenReturn(this.cardList);
        when(this.mapper.map(testCardWithID, CardDTO.class)).thenReturn(cardDTO);
        assertFalse("Service returned no Cards", this.service.readCards().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void createNoteTest(){
        when(repository.save(testCard)).thenReturn(testCardWithID);
        when(this.mapper.map(testCardWithID, CardDTO.class)).thenReturn(cardDTO);
        assertEquals(this.service.createCard(testCard), this.cardDTO);
        verify(repository, times(1)).save(this.testCard);
    }

    @Test
    public void findNoteByIdTest(){
        when(this.repository.findById(cardId)).thenReturn(java.util.Optional.ofNullable(testCardWithID));
        when(this.mapper.map(testCardWithID, CardDTO.class)).thenReturn(cardDTO);
        assertEquals(this.service.findCardById(this.cardId), cardDTO);
        verify(repository, times(1)).findById(cardId);
    }

    @Test
    public void deleteNoteByExistingId(){
        when(this.repository.existsById(cardId)).thenReturn(true, false);
        assertFalse(service.deleteCard(cardId));
        verify(repository, times(1)).deleteById(cardId);
        verify(repository, times(2)).existsById(cardId);
    }

    @Test(expected = CardNotFoundException.class)
    public void deleteNoteByNonExistingId(){
        when(this.repository.existsById(cardId)).thenReturn(false);
        service.deleteCard(cardId);
        verify(repository, times(1)).existsById(cardId);
    }

//    @Test
//    public void updateNoteTest(){
//
//        Note newNote = new Note("Favourite movies", "The grinch");
//        Note updateNote = new Note(newNote.getTitle(), newNote.getDescription());
//        updateNote.setId(id);
//
//        NoteDTO updateNoteDTO = new ModelMapper().map(updateNote, NoteDTO.class);
//
//        when(this.repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testNoteWithID));
//        when(this.repository.save(updateNote)).thenReturn(updateNote);
//        when(this.mapper.map(updateNote, NoteDTO.class)).thenReturn(updateNoteDTO);
//
//        assertEquals(updateNoteDTO, this.service.updateNote(id, newNote));
//        verify(this.repository, times(1)).findById(id);
//        verify(this.repository, times(1)).save(updateNote);
//    }

}