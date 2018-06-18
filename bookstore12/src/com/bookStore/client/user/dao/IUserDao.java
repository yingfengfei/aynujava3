package com.bookStore.client.user.dao;

import java.util.List;

import com.bookStore.commons.beans.Order;
import com.bookStore.commons.beans.OrderItem;
import com.bookStore.commons.beans.User;

public interface IUserDao {

	User selectEmail(String email);

	int insertUser(User user);

	int activeUser(String activeCode);

	User selectUserByUsernameAndPassword(User user);

	int updateUser(User user);

	List<Order> selectOrderByUser(Integer id);

	List<OrderItem> selectOrderItemById(String id);

	void deleteOrderById(String id);

	void deleteOrderItemById(String id);

	void updateProductPnum(OrderItem item);

}
