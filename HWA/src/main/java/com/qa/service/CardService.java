package com.qa.service;

import com.qa.domain.Card;
import com.qa.exceptions.CardNotFoundException;
import com.qa.repo.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardsRepository repo;

    @Autowired
    public CardService(CardsRepository repo) {
        this.repo = repo;
    }

    public List<Card> readCards(){
        return this.repo.findAll();
    }

    public Card createCard(Card card){
        return this.repo.save(card);
    }

    public Card findCardById(Long cardId){
        return this.repo.findById(cardId).orElseThrow(CardNotFoundException::new);
    }

    public Card updateCard(Long cardId, Card card){
        Card update = findCardById(cardId);
        update.setCardName(card.getCardName());
        update.setRarity(card.getRarity());
        return this.repo.save(update);
    }

    public boolean deleteCard(Long cardId){
        if(!this.repo.existsById(cardId)){
            throw new CardNotFoundException();
        }
        this.repo.deleteById(cardId);
        return this.repo.existsById(cardId);
    }


}
