package com.jcg.examples.services;


import com.jcg.examples.models.Client;

import java.util.List;

public interface  ClientService {
    Client findByUserId(long id);
    List<Client> selectAll();
    void save(Client client);
    void update(Client client);
    void deleteById(Long id);
    Client findById(long id);

}
