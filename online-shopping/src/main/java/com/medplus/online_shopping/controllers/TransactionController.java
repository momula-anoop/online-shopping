package com.medplus.online_shopping.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.online_shopping.dto.OrderTransactionDto;
import com.medplus.online_shopping.entities.Transaction;
import com.medplus.online_shopping.service.TransactionService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/Transaction")
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addTransaction(@RequestBody OrderTransactionDto orderTransactionDto) {
		
		return new ResponseEntity<String>(transactionService.addTransaction(orderTransactionDto), HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<String> updateTransaction(@RequestBody Transaction transaction) {
		transactionService.updateTransaction(transaction);
		return new ResponseEntity<String>("Transaction updated", HttpStatus.OK);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Transaction> getTransaction(@PathVariable("id") int transactionid) {
		return new ResponseEntity<Transaction>(transactionService.getTransactionById(transactionid), HttpStatus.OK);
	}
	@GetMapping("AllTran")
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		return new ResponseEntity<List<Transaction>>(transactionService.getAllTransactions(), HttpStatus.OK);
	}
	@GetMapping("/checkStatus/{id}")
	public ResponseEntity<String> checkStatusOfPayment(@PathVariable("id") int transactionid) {
		return new ResponseEntity<String>(transactionService.checkStatusOfPayment(transactionid), HttpStatus.OK);
	}
	
	@GetMapping("/getTranByCustId/{id}")
	public ResponseEntity<List<Transaction>> getTransactionsByCustomerId(@PathVariable("id") int custid) {
		return new ResponseEntity<List<Transaction>>(transactionService.getTransactionsByCustomerId(custid), HttpStatus.OK);
	}
	
	@GetMapping("/getTranByOrderId/{id}")
	public ResponseEntity<List<Transaction>> getTransactionsByOrderId(@PathVariable("id") int orderid) {
		return new ResponseEntity<List<Transaction>>(transactionService.getTransactionsByOrderId(orderid), HttpStatus.OK);
	}
	
	
	
}
