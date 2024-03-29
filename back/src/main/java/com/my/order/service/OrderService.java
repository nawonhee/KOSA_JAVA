package com.my.order.service;

import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dao.OrderOracleMybatisRepository;
import com.my.order.dao.OrderOracleRepository;
import com.my.order.dao.OrderRepository;
import com.my.order.dto.OrderInfo;

public class OrderService {
	private OrderRepository repository;
	public OrderService() {
		repository = new OrderOracleMybatisRepository(); //new OrderOracleRepository();
	}
	/**
	 * 주문을 추가한다
	 * @param info
	 * @throws AddException
	 */
	public void add(OrderInfo info) throws AddException{
		repository.insert(info);
	}
	
	public List<OrderInfo> findById(String loginedId) throws FindException{
		List<OrderInfo> list = new ArrayList<>();
		list = repository.selectById(loginedId);
		return list;
	}
}
