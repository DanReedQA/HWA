package com.qa.repo;

import com.qa.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<Card, Long> {
    //Card findByCardName(String cardName);
}
