package com.medplus.online_shopping.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medplus.online_shopping.entities.Customer;
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
	@Query(value = "select max(cust.customerId) from Customer cust")
	public Optional<Integer> getMaxOfCustomerId();

}
