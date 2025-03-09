package com.medplus.online_shopping.test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.medplus.online_shopping.dao.CustomerDao;
import com.medplus.online_shopping.entities.Customer;
import com.medplus.online_shopping.entities.Product;
import com.medplus.online_shopping.exceptions.CustomerNotFoundException;
import com.medplus.online_shopping.service.CustomerService;
import com.medplus.online_shopping.service.WishlistService;

import java.util.List;
import java.util.Optional;
@SpringBootTest
public class CustomerDaoTest {
	
	@Autowired
	WishlistService wishlistService;
	
	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerDao dao;

	@Test
	public void testAddCustomerFalse(){
		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setCustomerEmail("test@test.com");
		customer.setCustomerMobileNumber("12345");
		customer.setCustomerName("test");
		customer.setPassword("test");
		customer.setRole("test");
		dao.save(customer);
	Optional<Customer>  customerOptional= dao.findById(101);
	assertFalse(!customerOptional.isPresent());
	}
	@Test
	public void testAddCustomerTrue(){
	Optional<Customer>  customer= dao.findById(1200);
	assertTrue(customer.isPresent());
	}
	
	@Test
	public void testFindByIdFalse(){
	Optional<Customer>  customer= dao.findById(101);
	assertFalse(!customer.isPresent());
	}
	
	@Test
	public void testFindByIdTrue(){
	Optional<Customer>  customer= dao.findById(111);
	assertTrue(!customer.isPresent());
	}
	
	@Test
	public void testFindAllFalse(){
	List<Customer>  customer= dao.findAll();
	assertFalse(customer.isEmpty());
	}
	
	@Test
	public void testFindAllTrue(){
		List<Customer>  customer= dao.findAll();
	assertTrue(!customer.isEmpty());
	}

	@Test
	public void testDeleteTrue(){
		assertThrows(CustomerNotFoundException.class,()->customerService.deleteCustomerById(111));	
	}
	
	@Test
	public void testGetWishListProductlist() {
		List<Product> list = wishlistService.getProductlist(1200);

		assertTrue(list.size() != 0);

	}
	
	}


