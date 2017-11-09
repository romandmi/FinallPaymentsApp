package com.jcg.examples.models;

import javax.persistence.*;
import java.sql.Date;

/**
 *
 *  Transfer is a class that helps to work with database table transactions.
 *  This class consists exclusively of private fields and public methods that set or get
 *  values of fields:
 *  - id
 *  - tr_date (date of the transaction)
 *  - tr_sum (sum of the transaction)
 *  - tr_type (type of the transaction)
 *  - card_id (id of the card)
 *  - client_id (id of the client)
 *
 */
@Entity
@Table(name = "transactions")
public class Transfer {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date tr_date;
	private Integer tr_sum;
	private Integer tr_type;
	private Integer card_id;
	private Integer client_id;

    /**
     * This method return the value of field id
     * @return Long id value
     */
	public Long getId() {
		return id;
	}

    /**
     * This method set the value of field id
     * @param id Long id value
     */
	public void setId(Long id) {
		this.id = id;
	}

    /**
     * This method return the value of field tr_date, date of the transaction
     * @return Date tr_date value
     */
	public Date getTr_date() {
		return tr_date;
	}

    /**
     * This method set the value of field tr_date, date of the transaction
     * @param tr_date Date value
     */
	public void setTr_date(Date tr_date) {
		this.tr_date = tr_date;
	}

    /**
     * This method return the value of field tr_sum, sum of the transaction
     * @return Integer tr_sum, sum of the transaction
     */
	public Integer getTr_sum() {
		return tr_sum;
	}

    /**
     * This method set the value of field tr_sum, sum of the transaction
     * @param tr_sum Integer tr_sum, sum of the transaction
     */
	public void setTr_sum(Integer tr_sum) {
		this.tr_sum = tr_sum;
	}

    /**
     * This method return the value of field tr_type, type of the transaction
     * @return Integer tr_type, type of the transaction
     */
	public Integer getTr_type() {
		return tr_type;
	}

    /**
     * This method set the value of field tr_type, type of the transaction
     * @param tr_type Integer tr_type, type of the transaction
     */
	public void setTr_type(Integer tr_type) {
		this.tr_type = tr_type;
	}

    /**
     * This method return the value of field card_id, id of card
     * @return Integer card_id, id of the card
     */
	public Integer getCard_id() {
		return card_id;
	}

    /**
     * This method set the value of field card_id, id of card
     * @param card_id Integer card_id, id of the card
     */
	public void setCard_id(Integer card_id) {
		this.card_id = card_id;
	}

    /**
     * This method return the value of field client_id, id of client
     * @return Integer client_id, id of the client
     */
	public Integer getClient_id() {
		return client_id;
	}

    /**
     * This method set the value of field client_id, id of client
     * @param client_id Integer client_id, id of client
     */
	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}
}
