package com.qa.rest;

import com.qa.domain.Card;
import com.qa.dto.CardDTO;
import com.qa.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    private final CardService service;

    @Autowired
    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping("/getAllCards")
    public ResponseEntity<List<CardDTO>> getAllCards() {
        return ResponseEntity.ok(this.service.readCards());
    }

    @PostMapping("/createCard")
    public ResponseEntity<CardDTO> createCard(@RequestBody Card card) {
        return new ResponseEntity<CardDTO>(this.service.createCard(card), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCard/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Long cardId) {
        return this.service.deleteCard(cardId)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getCardById/{cardId}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long cardId) {
        return ResponseEntity.ok(this.service.findCardById(cardId));
    }

    @PutMapping("/updateCard/{cardId}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable Long cardId, @RequestBody Card card) {
        return ResponseEntity.ok(this.service.updateCard(cardId, card));
    }

}
