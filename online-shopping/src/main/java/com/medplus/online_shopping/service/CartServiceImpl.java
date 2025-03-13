package com.medplus.online_shopping.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.online_shopping.entities.Cart;
import com.medplus.online_shopping.entities.Customer;
import com.medplus.online_shopping.entities.Product;
import com.medplus.online_shopping.exceptions.CartEmptyException;
import com.medplus.online_shopping.exceptions.ProductNotAvailableInCartException;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CustomerService service;

	@Autowired
	CartService cartService;

	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerService customerService;

	@Override
	public void addProductToCart(int customerId, int productId) {
		customerService.checkCustomer(customerId);
		productService.checkAvailabilityOfProductByProductId(productId);
		Customer customer = service.getCustomerById(customerId);
		List<Cart> cartList = customer.getCart();
		int index = cartService.indexOfProduct(cartList, productId);
		if (index == -1) {
			final int quantity = 1;
			Cart cart = new Cart();
			cart.setProductId(productId);
			cart.setProductQuantity(quantity);
			cartList.add(cart);
			customer.setCart(cartList);
			service.updateCustomer(customer);
		} else {
			Cart cart = cartList.get(index);
			cart.setProductQuantity(cart.getProductQuantity() + 1);
			cartList.set(index, cart);
			customer.setCart(cartList);
			service.updateCustomer(customer);
		}
	}

	@Override
	public void updateCart(int customerId, Cart cart) {
		customerService.checkCustomer(customerId);
		Customer customer = service.getCustomerById(customerId);
		List<Cart> cartlist = customer.getCart();
		int index = cartService.indexOfProduct(cartlist, cart.getProductId());
		if(index==-1)
			throw new CartEmptyException();
		cartlist.set(index, cart);
		customer.setCart(cartlist);
		service.updateCustomer(customer);
	}

	@Override
	public void deleteCart(int customerId, int productId) {
		customerService.checkCustomer(customerId);
		productService.checkAvailabilityOfProductByProductId(productId);
		Customer customer = service.getCustomerById(customerId);
		List<Cart> cartlist = customer.getCart();
		int index = cartService.indexOfProduct(cartlist, productId);
		if(index==-1)
			throw new ProductNotAvailableInCartException();
		cartlist.remove(index);
		customer.setCart(cartlist);
		service.updateCustomer(customer);

	}

	@Override
	public List<Product> getProductlist(int customerId) {
		customerService.checkCustomer(customerId);
		Customer customer = service.getCustomerById(customerId);
		List<Cart> cartlist = customer.getCart();
		if(cartlist.isEmpty())
			throw new CartEmptyException();
		List<Product> productList = new ArrayList<>();
		for (Cart cart : cartlist) {
			int productId = cart.getProductId();
			Optional<Product> product = productService.getProductById(productId);
			productList.add(product.isPresent() ? product.get() : null);
		}
		return productList;
	}

	@Override
	public int indexOfProduct(List<Cart> cart, int productId) {
		int i = 0;
		Iterator<Cart> itr = cart.iterator();
		while (itr.hasNext()) {
			Cart e = itr.next();
			if (e.getProductId() == productId) {
				return i;
			}
			i++;
		}
		return -1;
	}

}
