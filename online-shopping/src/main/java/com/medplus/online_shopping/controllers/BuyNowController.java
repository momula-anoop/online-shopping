package com.medplus.online_shopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.online_shopping.service.BuyNowService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class BuyNowController {
	@Autowired
	BuyNowService service;

	@PostMapping("/BuyNow/{cid}/{pid}")
	public ResponseEntity<String> proceedToCheckout(@PathVariable("cid") int customerId,@PathVariable("pid") int productId) {
		return new ResponseEntity<>(service.buyNow(customerId, productId), HttpStatus.OK);

	}
}
