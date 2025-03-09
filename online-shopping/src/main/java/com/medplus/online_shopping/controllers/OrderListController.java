package com.medplus.online_shopping.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.online_shopping.entities.OrderList;
import com.medplus.online_shopping.service.OrderService;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/OrderList")
public class OrderListController {
	@Autowired
	OrderService orderService;
//	@PostMapping ("/addOrderList")
//	public ResponseEntity<String> addOrder(@RequestParam("customerId") int cid,@RequestBody List<Cart> cart)
//	{
//		orderService.addOrder(cid, cart);
//	  return new ResponseEntity<String>("order added successfully", HttpStatus.OK);	
//	}
	@PutMapping ("/updateOrderStatus")
	public ResponseEntity<String> updateOrderStatus(@RequestParam("OrderId") int oid,@RequestParam("status") String status)
	{
		orderService.updateOrderStatus(oid, status);
	  return new ResponseEntity<String>("status updated successfully", HttpStatus.OK);	
	}
	 
	@PutMapping("/update Order")
	public ResponseEntity<String> updateOrder(@RequestBody OrderList order)
	{
		orderService.updateOrder(order);;
	  return new ResponseEntity<String>("order updated successfully", HttpStatus.OK);	
	}
	
	@GetMapping ("/getOrderListByCustomerId")
	public ResponseEntity<List<OrderList>> getOrderListByCustomerId(@RequestParam("customerId") int customerId){
		List<OrderList> orderList = orderService.getOrderList(customerId);
		return new ResponseEntity<List<OrderList>> (orderList , HttpStatus.OK);
	}
	
	@GetMapping ("/getAllOrderList")
	public ResponseEntity<List<OrderList>> getOrderList(){
		List<OrderList> orderList = orderService.getAllOrderList();
		return new ResponseEntity<List<OrderList>> (orderList , HttpStatus.OK);
	}
	

	@GetMapping ("/getOrderByOrderid")
	public ResponseEntity<Optional<OrderList>> getOrderByOrderId(@RequestParam("orderId") int orderId){
		Optional<OrderList> orderList = orderService.getOrderByOid(orderId);
		return new ResponseEntity<Optional<OrderList>> (orderList , HttpStatus.OK);
	}
}
