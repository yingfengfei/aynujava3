package com.bookStore.admin.order.service;

import java.util.List;

import com.bookStore.commons.beans.Order;
import com.bookStore.commons.beans.OrderItem;

public interface IAdminOrderService {

	List<Order> findOrders();

	List<Order> findOrderByManyCondition(Order order);

	List<OrderItem> findOrderItemById(String id);

	void delOrderById(String id);

}
