
package com.medplus.online_shopping.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.online_shopping.entities.Customer;
import com.medplus.online_shopping.entities.Product;
import com.medplus.online_shopping.entities.WishList;
import com.medplus.online_shopping.exceptions.ProductNotAvailableInWishListException;
import com.medplus.online_shopping.exceptions.WishlistEmptyException;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	CustomerService service;

	@Autowired
	WishlistService wishlistService;

	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;

	@Override
	public Product addProductToWishlist(int customerId, int productId) {
		service.checkCustomer(customerId);
		productService.checkAvailabilityOfProductByProductId(productId);
		Customer customer = service.getCustomerById(customerId);
		List<WishList> wishlist = customer.getWishList();
		int index = wishlistService.indexOfProduct(wishlist, productId);
		if (index == -1) {
			final int quantity = 1;
			WishList wish = new WishList();
			wish.setProductId(productId);
			wish.setProductQuantity(quantity);
			wishlist.add(wish);
			customer.setWishList(wishlist);
			service.updateCustomer(customer);
		} else {
			WishList wish = wishlist.get(index);
			wish.setProductQuantity(wish.getProductQuantity() + 1);
			wishlist.set(index, wish);
			customer.setWishList(wishlist);
			service.updateCustomer(customer);
		}
		return productService.getProductById(productId).get();
	}

	@Override
	public void removeProductWishlist(int customerId, int productId) {
		service.checkCustomer(customerId);
		productService.checkAvailabilityOfProductByProductId(productId);
		Customer customer = service.getCustomerById(customerId);
		List<WishList> wishlist = customer.getWishList();
		int index = wishlistService.indexOfProduct(wishlist, productId);
		if(index==-1)
			throw new ProductNotAvailableInWishListException();
		wishlist.remove(index);
		customer.setWishList(wishlist);
		service.updateCustomer(customer);
	}

	@Override
	public void updateProductWishlist(int customerId, WishList wishlst) {
		service.checkCustomer(customerId);
		Customer customer = service.getCustomerById(customerId);
		List<WishList> wishlist = customer.getWishList();
		int index = wishlistService.indexOfProduct(wishlist, wishlst.getProductId());
		if(index==-1)
			throw new ProductNotAvailableInWishListException();
		wishlist.set(index, wishlst);
		customer.setWishList(wishlist);
		service.updateCustomer(customer);
	}

	@Override
	public List<Product> getProductlist(int customerId) {
		service.checkCustomer(customerId);
		Customer customer = service.getCustomerById(customerId);
		List<WishList> wishlist = customer.getWishList();
		List<Product> productList = new ArrayList<>();
		if(wishlist.isEmpty())
			throw new WishlistEmptyException();
		for (WishList wish : wishlist) {
			int productId = wish.getProductId();
			Optional<Product> product = productService.getProductById(productId);
			productList.add(product.get());
		}
		return productList;
	}

	@Override
	public int indexOfProduct(List<WishList> wishlist, int productId) {
		int i = 0;
		Iterator<WishList> itr = wishlist.iterator();
		while (itr.hasNext()) {
			WishList e = itr.next();
			if (e.getProductId() == productId) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public void putWishListItemToCart(int custId, int productId) {
		service.checkCustomer(custId);
		productService.checkAvailabilityOfProductByProductId(productId);
		if(service.getCustomerById(custId).getWishList().isEmpty())
			throw new WishlistEmptyException();
		
		// remove product from wishlit and add to cart
		wishlistService.removeProductWishlist(custId, productId);

//again adding cart item with updated values
		cartService.addProductToCart(custId, productId);

	}

	@Override
	public void putWishListALLItemToCart(int custId) {
		service.checkCustomer(custId);
		if(service.getCustomerById(custId).getWishList().isEmpty())
			throw new WishlistEmptyException();
		List<WishList> wList = service.getCustomerWishList(custId);

		
		for (int i = 0; i < wList.size(); i++) {

			WishList wishList = wList.get(i);

			cartService.addProductToCart(custId, wishList.getProductId());

		}

	}
}
