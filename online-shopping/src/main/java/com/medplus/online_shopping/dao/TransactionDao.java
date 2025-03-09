package com.medplus.online_shopping.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medplus.online_shopping.entities.Transaction;
@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer>{

	@Query(value = "select max(tran.transactionId) from Transaction tran")
	public Optional<Integer> getMaxOfTransactionId();
	
	@Query(value = "select tran from Transaction tran where tran.customerId=?1")
	public Optional<List<Transaction>> findAllTransactionByCustomerId(int customerId);
	
	@Query(value = "select tran from Transaction tran where tran.orderId=?1")
	public Optional<List<Transaction>> findAllTransactionByOrderId(int orderId);
}
