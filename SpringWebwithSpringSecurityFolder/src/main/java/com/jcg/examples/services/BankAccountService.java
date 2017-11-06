package com.jcg.examples.services;



import com.jcg.examples.models.BankAccount;

import java.util.List;


public interface BankAccountService {
    List<BankAccount> selectAll();

    void changeStatusById(long id);

    BankAccount getById(long id);
}