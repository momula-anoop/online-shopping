package com.medplus.online_shopping.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medplus.online_shopping.entities.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	@Query(value = "select prod from Product prod where prod.productName LIKE ?1 or prod.productDescription LIKE ?1 or prod.category LIKE ?1")
	public ArrayList<Product> getProductsByNameOrcategory(String text);
}
