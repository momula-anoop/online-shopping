package com.medplus.online_shopping.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class OrderList {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int orderId;
	@Column
	private int customerId;
	@Column
	private Date dateOfOrder;
	@ElementCollection
	private List<Cart> orderedProductList;
	@Column
	private Double totalAmmount;
//	@Column
//	private String houseNumber;
//	@Column
//	private String streetNumber;
//	@Column
//	private int zipCode;
//	@Column
//	private String city;
//	@Column
//	private String State;
	
	private Address address;
	
	

	

//	public String getHouseNumber() {
//		return houseNumber;
//	}
//
//	public void setHouseNumber(String houseNumber) {
//		this.houseNumber = houseNumber;
//	}
//
//	public String getStreetNumber() {
//		return streetNumber;
//	}
//
//	public void setStreetNumber(String streetNumber) {
//		this.streetNumber = streetNumber;
//	}
//
//	public int getZipCode() {
//		return zipCode;
//	}
//
//	public void setZipCode(int zipCode) {
//		this.zipCode = zipCode;
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public String getState() {
//		return State;
//	}
//
//	public void setState(String state) {
//		State = state;
//	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getOrderId() {
		return orderId;
	}

	public List<Cart> getOrderedProductList() {
		return orderedProductList;
	}

	public void setOrderedProductList(List<Cart> orderedProductList) {
		this.orderedProductList = orderedProductList;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}


	public Double getTotalAmmount() {
		return totalAmmount;
	}

	public void setTotalAmmount(Double totalAmmount) {
		this.totalAmmount = totalAmmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column
	private String status;





//	@Override
//	public String toString() {
//		return "OrderList [orderId=" + orderId + ", customerId=" + customerId + ", dateOfOrder=" + dateOfOrder
//				+ ", orderedProductList=" + orderedProductList + ", totalAmmount=" + totalAmmount + ", houseNumber="
//				+ houseNumber + ", streetNumber=" + streetNumber + ", zipCode=" + zipCode + ", city=" + city
//				+ ", State=" + State + ", status=" + status + "]";
//	}
	
	
}
