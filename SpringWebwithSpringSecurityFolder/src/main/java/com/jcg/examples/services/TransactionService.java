package com.jcg.examples.services;

import com.jcg.examples.models.Transfer;

import java.util.List;

public interface TransactionService {
	List<Transfer> selectAll();
	List<Transfer> selectAll(int id);

}
