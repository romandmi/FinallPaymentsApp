package com.jcg.examples.services;


import com.jcg.examples.models.Client;
import com.jcg.examples.models.User;

public interface  ClientService {
    Client findByUserId(long id);
}
