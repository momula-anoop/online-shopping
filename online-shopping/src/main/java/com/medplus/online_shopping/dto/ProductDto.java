package com.medplus.online_shopping.dto;

public class ProductDto {

	
	private String productName;

	private String productDescription;

	private double productprice;

	private int productQuantity;

	private boolean productAvailability;

	private String category;

	private String gender;

	public ProductDto(String productDescription, int productprice, int productQuantity, boolean productAvailability,
			String category, String gender) {
		super();

		this.productDescription = productDescription;
		this.productprice = productprice;
		this.productQuantity = productQuantity;
		this.productAvailability = productAvailability;
		this.category = category;
		this.gender = gender;
		System.out.println("param productdto constr");
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		System.out.println("product dto name setter");
		this.productName = productName;
	}

	public String getProductDescription() {
		System.out.println("product dto descp setter");
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductprice() {
		return productprice;
	}

	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getCategory() {
		return category;
	}

	public boolean isProductAvailability() {
		return productAvailability;
	}

	public void setProductAvailability(boolean productAvailability) {
		this.productAvailability = productAvailability;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
