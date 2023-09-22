package com.my.order.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;

public interface OrderRepository {
	void insert(OrderInfo info) throws AddException;
	public List<OrderInfo> selectById(String orderId) throws FindException;
}
