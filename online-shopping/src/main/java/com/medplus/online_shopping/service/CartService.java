package com.medplus.online_shopping.service;

import java.util.List;

import com.medplus.online_shopping.entities.Cart;
import com.medplus.online_shopping.entities.Product;

public interface CartService {

	public void addProductToCart(int customerId, int productId);

	public void updateCart(int customerId, Cart cart);

	public void deleteCart(int customerId, int productId);

	public List<Product> getProductlist(int customerId);

	public int indexOfProduct(List<Cart> cart, int productId);

}
