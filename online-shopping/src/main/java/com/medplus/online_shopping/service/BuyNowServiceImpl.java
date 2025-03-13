package com.medplus.online_shopping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.online_shopping.entities.Cart;

@Service
public class BuyNowServiceImpl implements BuyNowService{
	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productservice;
	@Autowired
	OrderService orderService;
	

	@Override
	public String buyNow(int customerId, int productId) {
		List<Cart> cartList = new ArrayList<>();
		
		Cart cart = new Cart();
		cartList.add(cart);
		cart.setProductId(productId);
		cart.setProductQuantity(1);
		return orderService.addOrder(customerId, cartList);
		
	}

}
