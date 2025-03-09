package com.medplus.online_shopping.service;

import java.util.List;
import java.util.Optional;

import com.medplus.online_shopping.entities.Cart;
import com.medplus.online_shopping.entities.OrderList;

public interface OrderService {
	public String addOrder(int customerId, List<Cart> cart);

	public void updateOrderStatus(int orderId, String status);

	public void updateOrder(OrderList order);
	
	public Optional<OrderList> getOrderByOid(int orderId);

	public List<OrderList> getOrderList(int customerId);

	public List<OrderList> getAllOrderList();

	public double getTotalAmmount(List<Cart> productList);
	
	int getCustomerIdByOrderId(int orderId);
	
	double getAmountByOrderId(int orderId);
	
	boolean checkOrderId(int orderId);
}
