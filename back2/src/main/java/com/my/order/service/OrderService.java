package com.my.order.service;

import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dao.OrderOracleMybatisRepository;
import com.my.order.dao.OrderOracleRepository;
import com.my.order.dao.OrderRepository;
import com.my.order.dto.OrderInfo;
import com.my.product.service.ProductService;

public class OrderService {
	private OrderRepository repository;
	private static OrderService service = new OrderService();
	public OrderService() {
		repository = new OrderOracleMybatisRepository(); //new OrderOracleRepository();
	}
	public static OrderService getInstance() {
		return service;
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
