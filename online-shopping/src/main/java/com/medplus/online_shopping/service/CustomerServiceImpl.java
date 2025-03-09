package com.medplus.online_shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.online_shopping.dao.CustomerDao;
import com.medplus.online_shopping.dto.CustomerAddDto;
import com.medplus.online_shopping.dto.CustomerDto;
import com.medplus.online_shopping.entities.Address;
import com.medplus.online_shopping.entities.Cart;
import com.medplus.online_shopping.entities.Customer;
import com.medplus.online_shopping.entities.WishList;
import com.medplus.online_shopping.exceptions.AddressEmptyException;
import com.medplus.online_shopping.exceptions.CartEmptyException;
import com.medplus.online_shopping.exceptions.CustomerNotFoundException;
import com.medplus.online_shopping.exceptions.WishlistEmptyException;
@Service
public class CustomerServiceImpl  implements CustomerService{
	

	private static int autoGenerateNumber=1200;
	
	@Autowired
	CustomerDao customerDao;
	
	private int generateCustomerNumber() {
		Optional<Integer> customerNumber=customerDao.getMaxOfCustomerId();
		if(!customerNumber.isPresent()) {
			System.out.println("if");
			return autoGenerateNumber;
		}
		else
			return customerNumber.get()+1;
	}
	
	public String addCustomer(CustomerAddDto customerAddDto) {
		Customer customer = new Customer();
		System.out.println();
		customer.setCustomerId(generateCustomerNumber());
		customer.setCustomerName(customerAddDto.getCustomerName());
		customer.setCustomerMobileNumber(customerAddDto.getMobileNumber());
		customer.setPassword(customerAddDto.getPassword());
		customer.setCustomerEmail(customerAddDto.getCustomerEmail());
		customer.setRole(customerAddDto.getRole());
		System.out.println(customer.getCustomerId());
		customerDao.save(customer);
		
		return customer.getCustomerId()+"";//change
	}

	@Override
	public CustomerDto getCustomerDtoById(int customerId) {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(!customer.isPresent())
			throw new CustomerNotFoundException();
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(customer.get().getCustomerId());
		customerDto.setCustomerName(customer.get().getCustomerName());
		customerDto.setCustomerEmail(customer.get().getCustomerEmail());
		customerDto.setMobileNumber(customer.get().getCustomerMobileNumber());
		customerDto.setPassword(customer.get().getPassword());
		customerDto.setRole(customer.get().getRole());
		return customerDto;
	}

	@Override
	public void deleteCustomerById(int customerId) {
		checkCustomer(customerId);
		customerDao.deleteById(customerId);
	}

	@Override
	public void updateCustomerDto(CustomerDto customerDto) {
		Optional<Customer> customerCheck = customerDao.findById(customerDto.getCustomerId());
		if(!customerCheck.isPresent())
			throw new CustomerNotFoundException();
		Customer customer = new Customer();
		customer.setCustomerId(customerDto.getCustomerId());
		customer.setCustomerName(customerDto.getCustomerName());
		customer.setCustomerMobileNumber(customerDto.getMobileNumber());
		customer.setPassword(customerDto.getPassword());
		customer.setCustomerEmail(customerDto.getCustomerEmail());
		customer.setRole(customerDto.getRole());
		customerDao.save(customer);
	}

	@Override
	public List<WishList> getCustomerWishList(int customerId) {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(!customer.isPresent())
			throw new CustomerNotFoundException();
		if(customer.get().getWishList().isEmpty())
			throw new WishlistEmptyException();
		return customer.get().getWishList();
	}

	@Override
	public List<Cart> getCustomerCart(int customerId) {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(!customer.isPresent())
			throw new CustomerNotFoundException();
		if(customer.get().getCart().isEmpty())
			throw new CartEmptyException();
		return customer.get().getCart();
	}

	@Override
	public List<Address> getCustomerAddress(int customerId) {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(!customer.isPresent())
			throw new CustomerNotFoundException();
		if(customer.get().getAddress().isEmpty())
			throw new AddressEmptyException();
		return customer.get().getAddress();
	}

	@Override
	public void addCustomerAddress(int customerId, Address address) {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(!customer.isPresent())
			throw new CustomerNotFoundException();
		List<Address> addresses = customer.get().getAddress();
		addresses.add(address);
		customer.get().setAddress(addresses);
		customerDao.save(customer.get());
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(!customer.isPresent())
			throw new CustomerNotFoundException();
		return customer.get();
	}

	@Override
	public void updateCustomer(Customer customer) {
		Optional<Customer> customerCheck = customerDao.findById(customer.getCustomerId());
		if(!customerCheck.isPresent())
			throw new CustomerNotFoundException();
		customerDao.save(customer);
	}

	@Override//update 1.1
	public boolean checkCustomer(int customerId) {
		Optional<Customer> customerCheck = customerDao.findById(customerId);
		if(!customerCheck.isPresent())
			throw new CustomerNotFoundException();
		return customerCheck.isPresent();
	}

	
	

}
