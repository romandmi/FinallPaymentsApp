package com.jcg.examples.services;


import com.jcg.examples.models.Card;

import java.util.List;

/**
 *
 * This service helps to work with database of cards
 *
 */
public interface  CardService {

    /**
     * This method finds all cards of clients using client's id in database
     * @param id client's id
     * @return list of cards
     */
    List<Card> findByClientId(long id);

    /**
     * This method selects all cards from database
     * @return list of cards
     */
    List<Card> selectAll();

    /**
     * This method saves card in database
     * @param card card
     */
    void save(Card card);

    /**
     * This method updates card in database
     * @param card card
     */
    void update(Card card);

    /**
     * This method deletes card by id in database
     * @param id card's id
     */
    void deleteById(Long id);

    /**
     * This method finds card by id in database
     * @param id card's id
     * @return card
     */
    Card findById(long id);

    /**
     * This method finds card by number in database
     * @param card_number card's number
     * @return card
     */
    Card findByNumber(String card_number);
}
