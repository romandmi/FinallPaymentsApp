package com.jcg.examples.services;


import com.jcg.examples.models.Client;
import com.jcg.examples.models.User;

import java.util.List;

public interface  ClientService {
    Client findByUserId(long id);

    List<Client> selectAll();
}
