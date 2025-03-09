package com.medplus.online_shopping.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medplus.online_shopping.entities.OrderList;
@Repository
public interface OrderListDao extends JpaRepository<OrderList, Integer> {
	@Query(value = "select orderlst from OrderList orderlst where  orderlst.customerId=?1")
	public List<OrderList> getOrderListById(int customerId);
}
