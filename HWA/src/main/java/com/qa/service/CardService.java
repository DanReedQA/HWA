package com.qa.service;

import com.qa.domain.Card;
import com.qa.dto.CardDTO;
import com.qa.exceptions.CardNotFoundException;
import com.qa.repo.CardsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardsRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public CardService(CardsRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private CardDTO mapToDTO(Card card) {
        return this.mapper.map(card, CardDTO.class);
    }

    public List<CardDTO> readCards() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CardDTO createCard(Card card) {
        Card tempCard = this.repo.save(card);
        return this.mapToDTO(tempCard);
    }

    public CardDTO findCardById(Long cardId) {
        return this.mapToDTO(this.repo.findById(cardId).orElseThrow(CardNotFoundException::new));
    }

    public CardDTO updateCard(Long cardId, Card card) {
        Card update = this.repo.findById(cardId).orElseThrow(CardNotFoundException::new);
        update.setCardName(card.getCardName());
        update.setRarity(card.getRarity());
        update.setStock(card.getStock());
        update.setValue(card.getValue());
        Card tempCard = this.repo.save(update);
        return this.mapToDTO(tempCard);
    }

    public boolean deleteCard(Long cardId){
        if(!this.repo.existsById(cardId)){
            throw new CardNotFoundException();
        }
        this.repo.deleteById(cardId);
        return this.repo.existsById(cardId);
    }


}
