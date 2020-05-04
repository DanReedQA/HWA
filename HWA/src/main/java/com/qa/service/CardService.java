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

    public Card findCardById(Long id){
        return this.repo.findById(id).orElseThrow(CardNotFoundException::new);
    }

    public Card updateCard(Long id, Card card){
        Card update = findCardById(id);
        update.setCard_name(card.getCard_name());
        update.setRarity(card.getRarity());
        return this.repo.save(update);
    }

    public boolean deleteCard(Long id){
        if(!this.repo.existsById(id)){
            throw new CardNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }


}
