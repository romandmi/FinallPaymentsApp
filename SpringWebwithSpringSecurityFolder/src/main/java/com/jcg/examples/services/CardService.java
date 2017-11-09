package com.jcg.examples.services;


import com.jcg.examples.models.Card;

import java.util.List;

public interface  CardService {
    List<Card> findByClientId(long id);
    List<Card> selectAll();
    void save(Card card);
    void update(Card card);
    void deleteById(Long id);
    Card findById(long id);

    Card findByNumber(String card_number);
}
