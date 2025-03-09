package com.medplus.online_shopping.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.online_shopping.service.CheckoutService;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class CheckOutController {
	@Autowired
	CheckoutService checkoutService;

	@GetMapping("/proceedToCheckout")
	public ResponseEntity<String> proceedToCheckout(@RequestParam("id") int customerId) {
		return new ResponseEntity<>(checkoutService.proceedToCheckout(customerId), HttpStatus.OK);

	}

}
