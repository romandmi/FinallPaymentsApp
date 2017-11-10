package com.jcg.examples.services;


import com.jcg.examples.models.Client;

import java.util.List;

/**
 * This service helps to work with database of clients
 */
public interface  ClientService {
    /**
     * This method finds client by his user's id in database
     * @param id user's id
     * @return client
     */
    Client findByUserId(long id);

    /**
     * This method selects all clients from database
     * @return list of clients
     */
    List<Client> selectAll();

    /**
     * This method saves client in database
     * @param client client
     */
    void save(Client client);

    /**
     * This method updates client in database
     * @param client client
     */
    void update(Client client);

     /**
     * This method deletes client by id in database
     * @param id client's id
     */
    void deleteById(Long id);

    /**
     * This method finds client by id in database
     * @param id client's id
     * @return client
     */
    Client findById(long id);
}
