package com.medplus.online_shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.online_shopping.entities.Cart;
import com.medplus.online_shopping.entities.Customer;
import com.medplus.online_shopping.entities.Product;
import com.medplus.online_shopping.exceptions.ProductQuantityException;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;

	@Override
	public String proceedToCheckout(int custId) {
		customerService.checkCustomer(custId);
		List<Cart> cartList = customerService.getCustomerCart(custId);
		List<Integer> result = new ArrayList<Integer>();
		double totalAmount = 0;
		for (Cart cart : cartList) {

			Optional<Product> optional = productService.getProductById(cart.getProductId());
			totalAmount = totalAmount + (optional.get().getProductprice() * cart.getProductQuantity());

		}
		for (Cart cart : cartList) {

			if ( productService
					.getQuantityOfProductByProductId(cart.getProductId()) < cart.getProductQuantity() || cart.getProductQuantity() <= 0) {
				throw new ProductQuantityException();
			}

		}
		String orderId = orderService.addOrder(custId, cartList);
		
		cartList.clear();
		Customer customer = customerService.getCustomerById(custId);
		customer.setCart(cartList);
		customerService.updateCustomer(customer);
		return orderId;
	}

}
