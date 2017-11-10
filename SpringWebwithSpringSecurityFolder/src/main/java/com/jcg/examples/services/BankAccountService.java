package com.jcg.examples.services;



import com.jcg.examples.models.BankAccount;

import java.util.List;

/**
 * This service helps to work with database of bank accounts
 */
public interface BankAccountService {

    /**
     * This method selects all bank accounts from database
     * @return list of bank accounts
     */
    List<BankAccount> selectAll();

    /**
     * This method changes status of bank account by id in database
     * @param id bank account's id
     */
    void changeStatusById(long id);

    /**
     * This method finds bank account by id in database
     * @param id bank account's id
     * @return bank account
     */
    BankAccount getById(long id);

    /**
     * This method saves bank account in database
     * @param account bank account
     */
    void save(BankAccount account);

    /**
     * This method updates bank account in database
     * @param account bank account
     */
    void update(BankAccount account);

    /**
     * This method deletes bank account by id from database
     * @param id bank account's id
     */
    void deleteById(Long id);
}