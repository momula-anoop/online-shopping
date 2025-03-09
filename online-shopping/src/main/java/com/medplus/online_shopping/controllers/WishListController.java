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

import com.medplus.online_shopping.entities.Product;
import com.medplus.online_shopping.entities.WishList;
import com.medplus.online_shopping.service.CartService;
import com.medplus.online_shopping.service.CustomerService;
import com.medplus.online_shopping.service.ProductService;
import com.medplus.online_shopping.service.WishlistService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class WishListController {
	@Autowired
	CustomerService service;
	@Autowired
	WishlistService wishListService;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;

	@GetMapping("/wishList/{id}")
	public ResponseEntity<List<WishList>> getWishListById(@PathVariable("id") int customerId) {
		return new ResponseEntity<List<WishList>>(service.getCustomerWishList(customerId), HttpStatus.OK);
	}

	@PostMapping("/AddToWish")
	public ResponseEntity<String> addProductToWishList(@RequestParam("id") int id, @RequestParam("pid") int productId) {
		wishListService.addProductToWishlist(id, productId);
		return new ResponseEntity<String>("Product added successfully", HttpStatus.OK);
	}

	@DeleteMapping("/DeleteWishList/{cid}/{pid}")
	public ResponseEntity<String> deleteWishListById(@PathVariable("cid") int customerId,
			@PathVariable("pid") int productId) {
		wishListService.removeProductWishlist(customerId, productId);
		return new ResponseEntity<String>("Deleted product from wishlist successfully", HttpStatus.OK);
	}

	@PutMapping("/updateWish")
	public ResponseEntity<String> updateProductToWishList(@RequestParam("id") int id, @RequestBody WishList wishList) {
		wishListService.updateProductWishlist(id, wishList);
		return new ResponseEntity<String>("Product updated successfully", HttpStatus.OK);
	}

	@GetMapping("/putWishListToCart/{id}/{custId}")
	public ResponseEntity<String> putWishListItemToCart(@PathVariable("id") int pId,
			@PathVariable("custId") int custId) {

		wishListService.putWishListItemToCart(custId, pId);

		return new ResponseEntity<String>("placed wishlistItem to cart", HttpStatus.OK);
	}

	@GetMapping("/putWishListALLItemsToCart/{custId}")
	public ResponseEntity<String> putWishListALLItemsToCart(@PathVariable("custId") int custId) {

		wishListService.putWishListALLItemToCart(custId);

		return new ResponseEntity<String>("placed  all wishlistItems to cart", HttpStatus.OK);
	}
	
	@GetMapping("/wishListproducts/{id}")
	public ResponseEntity<List<Product>> getProductsInWishList(@PathVariable("id") int custId) {

		

		return new ResponseEntity<>(wishListService.getProductlist(custId), HttpStatus.OK);
	}

}
