package com.medplus.online_shopping.service;

import java.util.List;

import com.medplus.online_shopping.dto.CustomerAddDto;
import com.medplus.online_shopping.dto.CustomerDto;
import com.medplus.online_shopping.entities.Address;
import com.medplus.online_shopping.entities.Cart;
import com.medplus.online_shopping.entities.Customer;
import com.medplus.online_shopping.entities.WishList;

public interface CustomerService {
	
	String addCustomer(CustomerAddDto customerAddDto);
	
	CustomerDto getCustomerDtoById(int customerId);
	
	Customer getCustomerById(int customerId);
	
	void deleteCustomerById(int customerId);
	
	void updateCustomerDto(CustomerDto customer);
	
	void updateCustomer(Customer customer);
	
	List<WishList> getCustomerWishList(int customerId);
	
	List<Cart> getCustomerCart(int customerId);
	
	List<Address> getCustomerAddress(int customerId);
	
	void addCustomerAddress(int customerId,Address address);
	
	
	boolean checkCustomer(int customerId);//update1.1
	
	
	
	
	
	

}
