package com.medplus.online_shopping.entities;


import javax.persistence.Embeddable;

@Embeddable
public class Cart {
	private int productId;
	private int productQuantity;
	
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}	
	
}
