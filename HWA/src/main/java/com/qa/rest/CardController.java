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
        return this.service.createNote(card);
    }

    @DeleteMapping("/deleteCard/{id}")
    public boolean deleteCard(@PathVariable Long id){
        return this.service.deleteCard(id);
    }

    @GetMapping("/getCardById/{id}")
    public Card getCardById(@PathVariable Long id){
        return this.service.findCardById(id);
    }

    @PutMapping("/updateCard/{id}")
    public Card updateCard(@PathVariable Long id, @RequestBody Card card){
        return this.service.updateCard(id, card);
    }

    @PutMapping("/updateCard2")
    public Card updateCard2(@PathParam("id") Long id, @RequestBody Card card){
        return this.service.updateCard(id, card);
    }

}
