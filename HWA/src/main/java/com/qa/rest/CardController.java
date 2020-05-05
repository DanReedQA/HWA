package com.qa.rest;

import com.qa.domain.Card;
import com.qa.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class CardController {

    private final CardService service;

    @Autowired
    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping("/getAllCards")
    public List<Card> getAllCards(){
        return this.service.readCards();
    }

    @PostMapping("/createCard")
    public Card createCard(@RequestBody Card card){
        return this.service.createCard(card);
    }

    @DeleteMapping("/deleteCard/{cardId}")
    public boolean deleteCard(@PathVariable Long cardId){
        return this.service.deleteCard(cardId);
    }

    @GetMapping("/getCardById/{cardId}")
    public Card getCardById(@PathVariable Long cardId){
        return this.service.findCardById(cardId);
    }

    @PutMapping("/updateCard/{cardId}")
    public Card updateCard(@PathVariable Long cardId, @RequestBody Card card){ return this.service.updateCard(cardId, card); }

    @PutMapping("/updateCard2")
    public Card updateCard2(@PathParam("cardId") Long cardId, @RequestBody Card card){ return this.service.updateCard(cardId, card); }

}
