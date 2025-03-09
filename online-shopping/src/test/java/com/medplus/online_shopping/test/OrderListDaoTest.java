package com.medplus.online_shopping.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.medplus.online_shopping.entities.OrderList;
import com.medplus.online_shopping.exceptions.CustomerNotFoundException;
import com.medplus.online_shopping.exceptions.OrderNotFoundException;
import com.medplus.online_shopping.service.OrderService;

@SpringBootTest
 class OrderListDaoTest {
	@Autowired
	OrderService service;

	@Test
	void testFindAllFalse() {
		List<OrderList> orderList = service.getAllOrderList();
		assertFalse(orderList.isEmpty());
	}

	@Test
	void testFindAllTrue() {
		List<OrderList> orderList = service.getAllOrderList();
		assertTrue(!orderList.isEmpty());
	}
	
	@Test
	 void testGetByIdTrue()
	 { 
		Optional<OrderList>  orderList = service.getOrderByOid(11);
		assertTrue(orderList.isPresent()); 
	 }
	
	@Test
	 void testGetByIdFalse()
	 { 
		assertThrows(OrderNotFoundException.class,()->service.getOrderByOid(9));
	 }
	@Test
	void testGetOrderListByIdTrue()
	{
		List<OrderList> orderList = service.getOrderList(1200);//customerId
		assertTrue(!orderList.isEmpty()); 
	}
	
	@Test
	void testGetOrderListByIdFalse()
	{
		assertThrows(CustomerNotFoundException.class,()->service.getOrderList(500));
	}
}
