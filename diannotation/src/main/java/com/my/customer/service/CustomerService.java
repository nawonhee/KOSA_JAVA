package com.my.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.customer.dao.CustomerOracleMybatisRepository;
import com.my.customer.dao.CustomerOracleRepository;
import com.my.customer.dao.CustomerRepository;
import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service(value="customerService")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class CustomerService {
	@Autowired
	private CustomerRepository repository;
	
	/**
	 * 아이디와 비밀번호에 일치하는 고객정보가 존재한다면 반환값이 없고
	 *                             존재하지 않으면 FindException 발생한다
	 * @param id
	 * @param pwd
	 * @throws FindException
	 */
	public void login(String id, String pwd) throws FindException {
		try{
			Customer c = repository.selectById(id);
			if(!c.getPwd().equals(pwd)) {
				throw new FindException();
			}
		}catch(FindException e) {
			throw new FindException("로그인 실패");
		}
		System.out.println("로그인 성공");
	}
	
	/**
	 * id에 해당하는 고객이 존재하지 않으면 FindException이 발생한다
	 * @param id
	 * @throws FindException
	 */
	public void idDupChk(String id) throws FindException{
		repository.selectById(id);
	}
	
	public void signup(Customer c) throws AddException {
		repository.insert(c);
	}
}
