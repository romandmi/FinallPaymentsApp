package com.jcg.examples.services;

import com.jcg.examples.models.Transfer;

import java.util.List;

/**
 * This service helps to work with database of transactions
 */
public interface TransactionService {
	 /**
	 * This method selects all transactions from database
	 * @return list of transactions
	 */
	List<Transfer> selectAll();

	 /**
	 * This method selects transactions by user's id from database
	 * @param id user's id
	 * @return list of transactions
	 */
	List<Transfer> selectAll(int id);

	 /**
	 * This method saves transaction in database
	 * @param tr transaction
	 */
    void save(Transfer tr);
}
