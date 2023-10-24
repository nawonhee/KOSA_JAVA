package com.my.customer.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.customer.dto.Customer;
import com.my.exception.FindException;

@ExtendWith(SpringExtension.class) //스프링용 단위테스트
@ContextConfiguration(classes = {config.MyApplicationContext.class}) //ApplicationContext= 스프링컨테이너 생성=스프링엔진 생성
class CustomerRepositoryTest {
	@Autowired
	CustomerRepository repository;

	@Test
	void test() {
		int i=10; //실제 처리결과 데이터
		int expectedI = 10; //성공될 예상데이터
		assertTrue(i==expectedI);
		
		String msg = "hello"; //실제 처리결과 데이터
		String expectedMsg = "Hi";
		assertEquals(expectedMsg, msg);
	}
	
	@Test
	void testInsert() {

	}
	
	@Test
	@DisplayName("아이디로 고객검색테스트")
	void testSelectById() throws FindException{
		String id = "B";
		Customer c = repository.selectById(id);
		String expectedPwd = "b";
		String expectedName = "B";
		assertEquals(expectedPwd,c.getPwd());
		assertEquals(expectedName,c.getName());
	}

}
