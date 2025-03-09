package com.medplus.online_shopping.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.medplus.online_shopping.dao.ProductDao;
import com.medplus.online_shopping.entities.Product;
import com.medplus.online_shopping.exceptions.ProductNotFoundException;
import com.medplus.online_shopping.service.ProductService;

import java.util.List;
import java.util.Optional;
@SpringBootTest
public class ProductDaoTest {
	
	@Autowired
	ProductService productService;
	
		

		@Autowired
		ProductDao dao;

		@Test
		public void testFindByIdFalse(){
		Optional<Product>  product= dao.findById(101);
		assertFalse(product.isPresent());
		}
		@Test
		public void testFindByIdTrue(){
		Optional<Product>  product= dao.findById(111);
		assertTrue(!product.isPresent());
		}
		@Test
		public void testFindAllFalse(){
		List<Product>  product= dao.findAll();
		assertFalse(product.isEmpty());
		}
		@Test
		public void testFindAllTrue(){
			List<Product>  product= dao.findAll();
		assertTrue(!product.isEmpty());
		}

		@Test
		public void testProductNotFound(){
			assertThrows(ProductNotFoundException.class,()->productService.changeProductQuantity(100, 0));	
		}
		
		@Test
		public void testCustomQuerySearchTrue(){
			List<Product> products =dao.getProductsByNameOrcategory("USPolo"); 
			assertNotNull(products);
		}
		
		@Test
		public void testCustomQuerySearchFalse(){
			List<Product> products =dao.getProductsByNameOrcategory("Waste"); 
			assertFalse(!products.isEmpty());
		}
		@Test
		public void getAllProducts() {
			List<Product> list = productService.getAllProducts();
			assertTrue(list.size() != 0);
		}

		@Test
		public void getProductById() {
			Optional<Product> product = productService.getProductById(1);
			assertTrue(product.isPresent());

		}
		
		}




