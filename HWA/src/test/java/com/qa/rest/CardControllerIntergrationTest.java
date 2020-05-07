package com.qa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Card;
import com.qa.dto.CardDTO;
import com.qa.repo.CardsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private CardsRepository repository;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Card testCard;

    private Card testCardWithID;

    private long cardId;

    private CardDTO cardDTO;

    private CardDTO mapToDTO(Card card){
        return this.mapper.map(card, CardDTO.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testCard = new Card("Card1", "Common", 50L, 5L);
        this.testCardWithID = this.repository.save(testCard);
        this.cardId = testCardWithID.getCardId();
        this.cardDTO = this.mapToDTO(testCardWithID);
    }

    @Test
    public void getAllCardsTest() throws Exception {
        List<CardDTO> cardDTOList = new ArrayList<>();
        cardDTOList.add(cardDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllCards")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(cardDTOList));
    }

    @Test
    public void getCardByID() throws Exception {
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getCardById/" + this.cardId)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(cardDTO));
    }

    @Test
    public void createCardTest() throws Exception {
        String result = this.mock.perform(
                request(HttpMethod.POST, "/createCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testCard))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(cardDTO));
    }

    @Test
    public void deleteCardTest() throws Exception {
        this.mock.perform(
                request(HttpMethod.DELETE, "/deleteCard/" + this.cardId)
        ).andExpect(status().isNoContent());
    }


}