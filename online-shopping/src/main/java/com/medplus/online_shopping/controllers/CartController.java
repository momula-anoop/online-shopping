package com.medplus.online_shopping.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.online_shopping.entities.Cart;
import com.medplus.online_shopping.service.CartService;
import com.medplus.online_shopping.service.CustomerService;
import com.medplus.online_shopping.service.WishlistService;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class CartController {
	@Autowired
	CustomerService service;
	@Autowired
	WishlistService wishListService;
	@Autowired
	CartService cartService;

	@PostMapping("/AddToCart")
	public ResponseEntity<String> addProductToCart(@RequestParam("customerId") int id, @RequestParam("productId") int productId) {
		cartService.addProductToCart(id, productId);
		return new ResponseEntity<String>("Product added successfully", HttpStatus.OK);
	}

	@GetMapping("/cart/{customerId}")
	public ResponseEntity<List<Cart>> getCartById(@PathVariable("customerId") int customerId) {
		return new ResponseEntity<List<Cart>>(service.getCustomerCart(customerId), HttpStatus.OK);
	}

	@DeleteMapping("/DeleteCart/{customerId}/{productId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("customerId") int customerId,
			@PathVariable("productId") int productId) {
		cartService.deleteCart(customerId, productId);
		return new ResponseEntity<String>("Deleted product from cart successfully", HttpStatus.OK);
	}

	@PutMapping("/updateCart")
	public ResponseEntity<String> updateProductToCart(@RequestParam("customerId") int id, @RequestBody Cart cart) {
		cartService.updateCart(id, cart);
		return new ResponseEntity<String>("Product updated successfully", HttpStatus.OK);
	}

}
