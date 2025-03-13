package com.medplus.online_shopping.service;

import java.util.List;
import java.util.Optional;

import com.medplus.online_shopping.dto.ProductDto;
import com.medplus.online_shopping.entities.Product;

public interface ProductService {

	public String addProduct(ProductDto productDto);

	public void updateProduct(Product product);

	public List<Product> getAllProducts();

	public Optional<Product> getProductById(int id);

	public void removeProductById(int id);

	public List<Product> getProductsBySearch(String text);

	public List<Product> getProductsSorthightolow();

	public List<Product> getProductsSortlowtohigh();

	public List<Product> getRecentlyViewedList();
	
	void changeProductQuantity(int productId,int productQuantity);
	
	int getQuantityOfProductByProductId(int productId);
	
	boolean checkAvailabilityOfProductByProductId(int productId);
}
