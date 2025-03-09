package com.medplus.online_shopping.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.online_shopping.dto.ProductDto;
import com.medplus.online_shopping.entities.Product;
import com.medplus.online_shopping.service.ProductService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class ProductController {
	@Autowired
	ProductService productservice;

	@PostMapping("/addproduct")
	public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) {

		return new ResponseEntity<String>(productservice.addProduct(productDto), HttpStatus.OK);
	}

	@PostMapping("/updateproduct")
	public ResponseEntity<String> updateproduct(@RequestBody Product product) {

		productservice.updateProduct(product);
		return new ResponseEntity<String>("updated ", HttpStatus.OK);
	}

	@GetMapping("/getallproducts")
	public ResponseEntity<List<Product>> getallproducts() {

		List<Product> products = productservice.getAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/getProductById/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") int productId) {

		Optional<Product> product = productservice.getProductById(productId);

		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
	}

	@GetMapping("/removeProductById/{productId}")
	public ResponseEntity<String> removeProductById(@PathVariable("productId") int productId) {

		productservice.removeProductById(productId);

		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	@GetMapping("/getSearch/{text}")
	public ResponseEntity<List<Product>> getProductsBySearch(@PathVariable("text") String text)

	{
		List<Product> products = productservice.getProductsBySearch(text);

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/sortproductspriceinhightolow")
	public ResponseEntity<List<Product>> sortproductspriceinhightolow()

	{
		List<Product> products = productservice.getProductsSorthightolow();

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/sortproductspriceinlowtohigh")
	public ResponseEntity<List<Product>> sortproductspriceinlowtohigh()

	{
		List<Product> products = productservice.getProductsSortlowtohigh();

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/getrecentlyvieweditems")
	public ResponseEntity<List<Product>> getrecentlyvieweditems()

	{

		return new ResponseEntity<List<Product>>(productservice.getRecentlyViewedList(), HttpStatus.OK);
	}

}
