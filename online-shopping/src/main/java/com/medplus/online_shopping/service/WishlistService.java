package com.medplus.online_shopping.service;

import java.util.List;

import com.medplus.online_shopping.entities.Product;
import com.medplus.online_shopping.entities.WishList;

public interface WishlistService {
	public Product addProductToWishlist(int customerId, int productId);

	public void removeProductWishlist(int customerId, int productId);

	public void updateProductWishlist(int customerId, WishList wishlist);

	public List<Product> getProductlist(int customerId);

	public int indexOfProduct(List<WishList> wishlist, int productId);

	public void putWishListItemToCart(int custId, int productId);

	public void putWishListALLItemToCart(int custId);
}
