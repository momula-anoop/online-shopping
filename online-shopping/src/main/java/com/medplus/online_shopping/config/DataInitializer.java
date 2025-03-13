package com.medplus.online_shopping.config;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medplus.online_shopping.dao.ProductDao;
import com.medplus.online_shopping.entities.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataInitializer {
	
	@Autowired
	private ProductDao productDao;
	
	

	@PostConstruct
	public void initData() {
		
		List<Product> products = productDao.findAll();
		
		if( products.isEmpty()) {
			products = Arrays.asList(
		            new Product("Men's T-Shirt", "Casual cotton T-shirt", 19.99, 50, true, "Clothing", "Male"),
		            new Product("Women's Dress", "Elegant summer dress", 49.99, 30, true, "Clothing", "Female"),
		            new Product("Men's Running Shoes", "Running shoes with mesh upper", 59.99, 20, true, "Footwear", "Male"),
		            new Product("Women's Sandals", "Comfortable sandals", 29.99, 0, false, "Footwear", "Female"),
		            new Product("Sunglasses", "Stylish sunglasses with UV protection", 15.99, 100, true, "Accessories", "Unisex"),
		            new Product("Bluetooth Headphones", "Wireless Bluetooth Headphones", 79.99, 15, true, "Electronics", "Unisex"),
		            new Product("Men's Jeans", "Slim fit jeans", 39.99, 40, true, "Clothing", "Male"),
		            new Product("Women's Sweater", "Knitted wool sweater", 34.99, 25, true, "Clothing", "Female"),
		            new Product("Wristwatch", "Leather wristwatch", 89.99, 50, true, "Accessories", "Unisex"),
		            new Product("Men's Formal Shoes", "Formal leather shoes", 99.99, 10, true, "Footwear", "Male")
		        );
			productDao.saveAll(products);
		} else {
			log.info("Data already exists, skipping insertion.");
		}
		
	}
}
