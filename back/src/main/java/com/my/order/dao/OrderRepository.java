package com.my.order.dao;

import com.my.exception.AddException;
import com.my.order.dto.OrderInfo;

public interface OrderRepository {
	void insert(OrderInfo info) throws AddException;
}
