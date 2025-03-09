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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.online_shopping.dto.CustomerAddDto;
import com.medplus.online_shopping.dto.CustomerDto;
import com.medplus.online_shopping.entities.Address;
import com.medplus.online_shopping.entities.Cart;

import com.medplus.online_shopping.entities.WishList;
import com.medplus.online_shopping.service.CartService;
import com.medplus.online_shopping.service.CustomerService;

import com.medplus.online_shopping.service.WishlistService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/Customer")
public class CustomerController {

	@Autowired
	CustomerService service;
	@Autowired
	WishlistService wishListService;
	@Autowired
	CartService cartService;
	
	@PostMapping("/Add")
	public ResponseEntity<String> addCustomer(@RequestBody CustomerAddDto customer){
		
		return new ResponseEntity<String>(service.addCustomer(customer),HttpStatus.OK);
	}
	
	@GetMapping("/ById/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") int id){
		
		return new ResponseEntity<CustomerDto>(service.getCustomerDtoById(id),HttpStatus.OK); 
		
	}
	@PutMapping
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customerDto) {
		
		
		service.updateCustomerDto(customerDto);
		return new ResponseEntity<String>("Updated successfully",HttpStatus.OK);
	} 
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("customerId") int customerId) {
		service.deleteCustomerById(customerId);
		return new ResponseEntity<String>("Delete successfully",HttpStatus.OK);
	}
	
	@GetMapping("/wishList/{customerId}")
	public ResponseEntity<List<WishList>> getWishListById(@PathVariable("customerId") int customerId){
		return new ResponseEntity<List<WishList>>(service.getCustomerWishList(customerId),HttpStatus.OK);
	}
	
	@GetMapping("/cart/{customerId}")
	public ResponseEntity<List<Cart>> getCartById(@PathVariable("customerId") int customerId){
		return new ResponseEntity<List<Cart>>(service.getCustomerCart(customerId),HttpStatus.OK);
	}
	
	@PostMapping("/AddAddress")
	public ResponseEntity<String> addAddresstoCustomer(@RequestParam("id") int id,@RequestBody Address address) {
		service.addCustomerAddress(id, address);
		return new ResponseEntity<String>("address added successfully",HttpStatus.OK);
	}
	
	@GetMapping("/getAddresses")
	public ResponseEntity<List<Address>> getAddressesOfCustomer(@RequestParam("id") int id) {
		
		return new ResponseEntity<List<Address>>(service.getCustomerAddress(id),HttpStatus.OK);
	}
	
//	@PostMapping("/AddToWish/{productId}")
//	public ResponseEntity<String> addProductToWishList(@RequestParam("customerId") int id,@PathVariable("productId") int productId) {
//		wishListService.addProductToWishlist(id, productId);
//		return new ResponseEntity<String>("Product added successfully",HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/DeleteWishList/{cid}/{pid}")
//	public ResponseEntity<String> deleteWishListById(@PathVariable("customerId") int customerId,@PathVariable("productId") int productId) {
//		wishListService.removeProductWishlist(customerId, productId);
//		return new ResponseEntity<String>("Deleted product from wishlist successfully",HttpStatus.OK);
//	}
//	
//
//	
//	@PostMapping("/AddToCart")
//	public ResponseEntity<String> addProductToCart(@RequestParam("customerId") int id,@RequestParam("productId") int productId) {
//		cartService.addProductToCart(id, productId);
//		return new ResponseEntity<String>("Product added successfully",HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/DeleteCart/{customerId}/{productId}")
//	public ResponseEntity<String> deleteCustomerById(@PathVariable("customerId") int customerId,@PathVariable("productId") int productId) {
//		cartService.deleteCart(customerId, productId);
//		return new ResponseEntity<String>("Deleted product from cart successfully",HttpStatus.OK);
//	}
//	
//	@PutMapping("/updateCart")
//	public ResponseEntity<String> updateProductToWishList(@RequestParam("customerId") int id,@RequestBody Cart cart ) {
//		cartService.updateCart(id, cart);
//		return new ResponseEntity<String>("Product updated successfully",HttpStatus.OK);
//	}
	
	
}
