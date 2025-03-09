package com.medplus.online_shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.online_shopping.dto.CustomerDto;
import com.medplus.online_shopping.exceptions.InvalidPasswordException;



@Service
public class UserServiceImpl implements UserService{
	@Autowired
	CustomerService customerService;

	@Override
	public void userLogin(int userId, String password) {
		CustomerDto customer=customerService.getCustomerDtoById(userId);
		if(!(customer.getPassword().equals(password)))
			throw new InvalidPasswordException();
	}
	
	


}
