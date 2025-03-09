package com.medplus.online_shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.medplus.online_shopping.dao.TransactionDao;
import com.medplus.online_shopping.dto.OrderTransactionDto;
import com.medplus.online_shopping.entities.Cart;
import com.medplus.online_shopping.entities.Transaction;
import com.medplus.online_shopping.exceptions.CustomerNotFoundException;
import com.medplus.online_shopping.exceptions.TransactionListEmptyException;
import com.medplus.online_shopping.exceptions.TransactionNotFoundException;
@Service
public class TransactionServiceImpl implements TransactionService{
	@Autowired
	CustomerService customerService;
	@Autowired
	TransactionDao transactionDao;
	@Autowired
	OrderService orderService;
	@Autowired
	ProductService productService; 
	
	private static int autoGenerateNumber=50050;
	
	
	private int generatetransactionNumber() {
		Optional<Integer> transactionNumber=transactionDao.getMaxOfTransactionId();
		if(!transactionNumber.isPresent()) {
			return autoGenerateNumber;
		}
		else
			return transactionNumber.get()+1;
	}
	@Override
	public String addTransaction(OrderTransactionDto orderTransactionDto) {
		
		String result="Declined";
		if(!customerService.checkCustomer(orderService.getCustomerIdByOrderId(orderTransactionDto.getOrderId())))
			throw new CustomerNotFoundException();
		Transaction transaction = new Transaction();
		transaction.setTransactionId(generatetransactionNumber());
		transaction.setCustomerId(orderService.getCustomerIdByOrderId(orderTransactionDto.getOrderId()));
		transaction.setAmount(orderService.getAmountByOrderId(orderTransactionDto.getOrderId()));
		transaction.setOrderId(orderTransactionDto.getOrderId());
		transaction.setPaymentMode(orderTransactionDto.getPaymentMode());
		transaction.setPaymentStatus("Success");
		transactionDao.save(transaction);
		result="transaction added successfully ,your Transaction ID : "+transaction.getTransactionId()+" ";
		List<Cart> cartList = orderService.getOrderByOid(transaction.getOrderId()).get().getOrderedProductList();
		for(Cart cart:cartList) {
			productService.changeProductQuantity(cart.getProductId(),cart.getProductQuantity());
		}
		orderService.updateOrderStatus(transaction.getOrderId(), "Success");
		
		return result;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		List<Transaction> transactionList = transactionDao.findAll(); 
		return transactionList;
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		Optional<Transaction> checkTransaction = transactionDao.findById(transaction.getTransactionId());
		if(!checkTransaction.isPresent())
			throw new TransactionNotFoundException();
		transactionDao.save(transaction);
	}

	@Override
	public String checkStatusOfPayment(int transactionId) {
		Optional<Transaction> transaction = transactionDao.findById(transactionId);
		if(!transaction.isPresent())
			throw new TransactionNotFoundException();
		return transaction.get().getPaymentStatus();
	}
	@Override
	public Transaction getTransactionById(int transactionId) {
		Optional<Transaction> transaction = transactionDao.findById(transactionId);
		if(!transaction.isPresent())
			throw new TransactionNotFoundException();
		return transaction.get();
	}
	@Override
	public List<Transaction> getTransactionsByCustomerId(int customerId) {
		customerService.checkCustomer(customerId);
		Optional<List<Transaction>> transaction = transactionDao.findAllTransactionByCustomerId(customerId);
		if(!transaction.isPresent())
			throw new TransactionListEmptyException();
 		return transaction.get();
	}
	@Override
	public List<Transaction> getTransactionsByOrderId(int orderId) {
		orderService.checkOrderId(orderId);
		Optional<List<Transaction>> transaction = transactionDao.findAllTransactionByOrderId(orderId);
		if(!transaction.isPresent())
			throw new TransactionListEmptyException();
		return transaction.get();
	}

}
