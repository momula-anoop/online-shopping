package com.medplus.online_shopping.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.online_shopping.dao.OrderListDao;
import com.medplus.online_shopping.entities.Cart;
import com.medplus.online_shopping.entities.OrderList;
import com.medplus.online_shopping.entities.Product;
import com.medplus.online_shopping.exceptions.OrderNotFoundException;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	ProductService service;
	@Autowired
	OrderListDao orderListDao;
	@Autowired
	OrderService orderService;
	@Autowired
	CustomerService customerService;
	
	@Temporal (TemporalType.TIMESTAMP)
	static Date date=new Date(System.currentTimeMillis());

	@Override
	public void updateOrderStatus(int orderId, String status) {
		checkOrderId(orderId);
		Optional<OrderList> order = orderListDao.findById(orderId);
		OrderList orderdetail = null;
		if(order.isPresent())
		{
			orderdetail =order.get();
		    orderdetail.setStatus(status);
		    orderService.updateOrder(orderdetail);
		}
	}
	

	@Override
	public List<OrderList> getOrderList(int customerId) {
		customerService.checkCustomer(customerId);
		return orderListDao.getOrderListById(customerId);
	}

	@Override
	public List<OrderList> getAllOrderList() {
		return orderListDao.findAll();
	}

	@Override
	public String addOrder(int customerId, List<Cart> cart) {
		customerService.checkCustomer(customerId);
		OrderList order = new OrderList();
		List<Cart> newcartList = new ArrayList<Cart>();
		newcartList.addAll(cart);
		order.setCustomerId(customerId);
		order.setOrderedProductList(newcartList);
		order.setStatus("pending");
		order.setDateOfOrder(new java.sql.Date(date.getTime()));
		order.setTotalAmmount(orderService.getTotalAmmount(cart));
//		order.setCity(customerService.getCustomerAddress(customerId).get(0).getCity());
//		order.setHouseNumber(customerService.getCustomerAddress(customerId).get(0).getHouseNumber());
//		order.setState(customerService.getCustomerAddress(customerId).get(0).getState());
//		order.setStreetNumber(customerService.getCustomerAddress(customerId).get(0).getStreetNumber());
//		order.setZipCode(customerService.getCustomerAddress(customerId).get(0).getZipCode());
		order.setAddress(customerService.getCustomerAddress(customerId).get(0));
		System.out.println(order);
		orderListDao.save(order);
		return ""+order.getOrderId();
	}

	@Override
	public double getTotalAmmount(List<Cart> productList) {
		double total =0;
		for(Cart cart : productList)
		{
			int productId= cart.getProductId();
			Optional<Product> oproduct = service.getProductById(productId);
			Product product =oproduct.get();
			double price = product.getProductprice();
			total += price * cart.getProductQuantity();
		}
		return total;
	}


	@Override
	public void updateOrder(OrderList order) {
			orderListDao.save(order);
	}

	@Override
	public Optional<OrderList> getOrderByOid(int orderId) {
		checkOrderId(orderId);
		return orderListDao.findById(orderId);
	}


	@Override
	public int getCustomerIdByOrderId(int orderId) {
		checkOrderId(orderId);
		Optional<OrderList> order = orderListDao.findById(orderId);
		return order.get().getCustomerId();
	}


	@Override
	public double getAmountByOrderId(int orderId) {
		checkOrderId(orderId);
		Optional<OrderList> order = orderListDao.findById(orderId);
		return order.get().getTotalAmmount();
	}


	@Override
	public boolean checkOrderId(int orderId) {
		Optional<OrderList> order = orderListDao.findById(orderId);
		if(!order.isPresent())
			throw new OrderNotFoundException();
		return true;
	}

}
