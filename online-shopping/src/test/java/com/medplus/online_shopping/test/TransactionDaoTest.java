package com.medplus.online_shopping.test;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.medplus.online_shopping.dao.TransactionDao;
import com.medplus.online_shopping.entities.Transaction;

@SpringBootTest
public class TransactionDaoTest {
	@Autowired
	TransactionDao transactionDao;
	@Test
	public void testaddTransactionTrue() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(101);
		transaction.setOrderId(101);
		transaction.setCustomerId(101);
		transaction.setAmount(101);
		transaction.setPaymentMode("test");
		transaction.setPaymentStatus("testing");
		transactionDao.save(transaction);
		Optional<Transaction> transactionCheck = transactionDao.findById(101);
		assertTrue(transactionCheck.isPresent());
	}
	
	@Test
	public void testTransactionFindByIdTrue() {
		Optional<Transaction> transactionCheck = transactionDao.findById(50050);
		assertTrue(transactionCheck.isPresent());
	}
	
	@Test
	public void testTransactionFindByIdFalse() {
		Optional<Transaction> transactionCheck = transactionDao.findById(100);
		assertFalse(transactionCheck.isPresent());
	}
	
	@Test
	public void testTransactionFindAllFalse() {
		List<Transaction> transactionList = transactionDao.findAll();
		assertFalse(transactionList.isEmpty());
	}
	
	@Test
	public void testTransactionFindAllTrue() {
		List<Transaction> transactionList = transactionDao.findAll();
		assertTrue(!transactionList.isEmpty());
	}
	
	@Test
	public void testCustomQueryGetMaxIdTrue() {
		Optional<Integer> maxId=transactionDao.getMaxOfTransactionId();
		assertTrue(maxId.isPresent());
	}
	
	@Test
	public void testCustomQueryGetMaxIdFalse() {
		Optional<Integer> maxId=transactionDao.getMaxOfTransactionId();
		assertFalse(!maxId.isPresent());
	}
}
