package com.jcg.examples.models;

import javax.persistence.*;

/**
 *
 *  BankAccount is a class that helps to work with database table bank_accounts.
 *  This class consists exclusively of private fields and public methods that set or get
 *  values of fields:
 *  - id
 *  - balance
 *  - is_blocked
 *
 */
@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int balance;
    private boolean is_blocked;

    /**
     * This method return the value of field id
     * @return id value
     */
    public Long getId() {
        return id;
    }

    /**
     * This method set the value of field id
     * @param id id value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method return the value of field balance
     * @return balance of the bank account
     */
    public int getBalance() {
        return balance;
    }

    /**
     * This method set the value of field balance
     * @param balance balance of the bank account
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * This method return if the bank account is blocked
     * @return boolean value
     */
    public boolean getIs_blocked() {
        return is_blocked;
    }

    /**
     * This method set if the bank account is blocked
     * @param is_blocked boolean value
     */
    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }
}
