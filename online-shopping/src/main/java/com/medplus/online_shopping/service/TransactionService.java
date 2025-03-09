package com.medplus.online_shopping.service;

import java.util.List;

import com.medplus.online_shopping.dto.OrderTransactionDto;
import com.medplus.online_shopping.entities.Transaction;

public interface TransactionService {
	
	public String addTransaction(OrderTransactionDto orderTransactionDto);
	
	public List<Transaction> getAllTransactions();
	
	public void updateTransaction(Transaction transaction);
	
	public String checkStatusOfPayment(int transactionId);
	
	public Transaction getTransactionById(int transactionId);
	
	public List<Transaction> getTransactionsByCustomerId(int customerId);
	
	public List<Transaction> getTransactionsByOrderId(int customerId);
}
