package com.medplus.online_shopping.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.online_shopping.dao.ProductDao;
import com.medplus.online_shopping.dto.ProductDto;
import com.medplus.online_shopping.entities.Product;
import com.medplus.online_shopping.exceptions.ProductNotFoundException;
import com.medplus.online_shopping.exceptions.ProductQuantityException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;
	// for external use like sorting from low to high
	ArrayList<Product> recentlyViewedList = null;

	@Override
	public String addProduct(ProductDto productDto) {
		Product product = new Product();
		product.setCategory(productDto.getCategory());
		product.setGender(productDto.getGender());
		product.setProductAvailability(productDto.isProductAvailability());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductName(productDto.getProductName());
		product.setProductprice(productDto.getProductprice());
		product.setProductQuantity(productDto.getProductQuantity());
		productDao.save(product);
		return "Product added sucessfully productID : "+product.getProductId();
	}

	@Override
	public void updateProduct(Product product) {

		checkAvailabilityOfProductByProductId(product.getProductId());
		
//		product2.setProductName(productDto.getProductName());
//		product2.setProductprice(productDto.getProductprice());
//		product2.setCategory(productDto.getCategory());
//		product2.setGender(productDto.getGender());
//		product2.setProductAvailability(productDto.isProductAvailability());
//		product2.setProductDescription(productDto.getProductDescription());
//		product2.setProductQuantity(productDto.getProductQuantity());

		productDao.save(product);
		
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productDao.findAll();
		if (products.size() == 0) {
			throw new ProductNotFoundException();
		}

		return products;

	}

	@Override
	public Optional<Product> getProductById(int id) {
		log.info("productId :{}",id);
		recentlyViewedList = new ArrayList<Product>();
		Optional<Product> pOptional = productDao.findById(id);
		if (!pOptional.isPresent()) {
			throw new ProductNotFoundException();
		}

		recentlyViewedList.add(pOptional.get());

		return pOptional;

	}

	@Override
	public void removeProductById(int id) {
		getProductById(id);
		productDao.deleteById(id);

	}

	@Override
	public List<Product> getProductsBySearch(String text) {
		log.info(" Text search",text);
		recentlyViewedList = new ArrayList<>();
		ArrayList<Product> tempList = new ArrayList<>();
		HashMap<Product, Integer> tempMap = new HashMap<>();

		ArrayList<Product> resultList = null;
		ArrayList<ArrayList<Product>> resultListTemp = new ArrayList<ArrayList<Product>>();

		String[] strings = text.split(" ");
		for (int i = 0; i < strings.length; i++) {
			ArrayList<Product> list = productDao.getProductsByNameOrcategory("%" + strings[i] + "%");
			if (!list.isEmpty())
				tempList.addAll(list);
		}

		if (tempList.isEmpty()) {
			throw new ProductNotFoundException();
		}

		for (int i = 0; i < strings.length; i++) {
			resultListTemp.add(new ArrayList<>());

		}

		int successCount = 0;
		for (int i = 0; i < tempList.size(); i++) {

			if (tempMap.get(tempList.get(i)) != null) {
				successCount++;

				tempMap.put(tempList.get(i), tempMap.get(tempList.get(i)) + 1);

				int k = strings.length - tempMap.get(tempList.get(i));

				resultListTemp.get(k).add(tempList.get(i));

			}

			else {
				tempMap.put(tempList.get(i), 1);
			}

		}


		int i;
		for (i = 0; i < resultListTemp.size(); i++) {

			if (resultListTemp.get(i).size() != 0) {
				resultList = resultListTemp.get(i);

				break;
			}

		}

		if (i == resultListTemp.size()) {
			throw new ProductNotFoundException();
		}

		return resultList;

	}

	@Override
	public List<Product> getProductsSortlowtohigh() {
		return getAllProducts().stream().sorted(Comparator.comparing(Product::getProductprice)).collect(Collectors.toList());
	}

	@Override
	public List<Product> getProductsSorthightolow() {
		return getAllProducts().stream().sorted(Comparator.comparing(Product::getProductprice).reversed()).collect(Collectors.toList());
	}

	@Override
	public List<Product> getRecentlyViewedList() {
		if (recentlyViewedList.size() == 0) {
			throw new ProductNotFoundException();
		}

		return recentlyViewedList;
	}

	@Override
	public void changeProductQuantity(int productId, int productQuantity) {
		Optional<Product> product = getProductById(productId);
		if (!product.isPresent()) 
			throw new ProductNotFoundException();
		if(product.get().getProductQuantity()<productQuantity)
			throw new ProductQuantityException();
		product.get().setProductQuantity(product.get().getProductQuantity()-productQuantity);
		if(product.get().getProductQuantity()==0)
			product.get().setProductAvailability(false);
		productDao.save(product.get());
		
	}

	@Override
	public int getQuantityOfProductByProductId(int productId) {
		Optional<Product> product = getProductById(productId);
		if (!product.isPresent()) 
			throw new ProductNotFoundException();
		if (!product.get().isProductAvailability()) 
			throw new ProductNotFoundException();
		return product.get().getProductQuantity();
		
		
	}

	@Override
	public boolean checkAvailabilityOfProductByProductId(int productId) {
		Optional<Product> product = getProductById(productId);
		if (!product.isPresent()) 
			throw new ProductNotFoundException();
		return product.get().isProductAvailability();
	}

}
