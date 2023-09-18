package com.my.customer.dao;

import com.my.customer.dto.Customer;
import com.my.exception.FindException;

public interface CustomerRepository {
	/**
	 * 아이디에 해당하는 고객을 검색한다
	 * @param id 아이디
	 * @return 고객
	 * @throws FindException 아이디에 해당하는 고객이 없거나 DB와 연결 실패하면 예외 발생한다
	 */
	Customer selectById(String id) throws FindException;
}
