package com.jcg.examples.models;

import javax.persistence.*;

/**
 *
 *  Card is a class that helps to work with database table cards.
 *  This class consists exclusively of private fields and public methods that set or get
 *  values of fields:
 *  - id
 *  - card_number (number of the card)
 *  - account_id (id of the bank account)
 *  - client_id (id of the client)
 *
 */
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String card_number;
    private Integer account_id;
    private Integer client_id;

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
     * This method return the value of field card_number
     * @return number of the card
     */
	public String getCard_number() {
		return card_number;
	}

    /**
     * This method set the value of field card_number
     * @param card_number number of the card
     */
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

    /**
     * This method return the value of field account_id
     * @return id of the bank account
     */
	public Integer getAccount_id() {
		return account_id;
	}

    /**
     * This method set the value of field account_id
     * @param account_id id of the bank account
     */
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

    /**
     * This method return the value of field client_id
     * @return id of the client
     */
	public Integer getClient_id() {
		return client_id;
	}

    /**
     * This method set the value of field client_id
     * @param client_id id of the client
     */
	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}
}
