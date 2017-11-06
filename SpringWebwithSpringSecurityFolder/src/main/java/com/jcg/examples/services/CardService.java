package com.jcg.examples.services;


import com.jcg.examples.models.Card;

import java.util.List;

public interface  CardService {
    List<Card> findByClientId(long id);
}
