package com.my.jpa.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.jpa.entity.A;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ARepositoryTest {
	@Autowired
	ARepository r;
	
	@Test
	void test() {
		log.error(r.getClass().getName());
	}
	
	@Test
	@Transactional
	@Commit
	void test1Save() {
		A a = new A();
		a.setA_1("one");
		a.setA_2(new BigDecimal(1.0));
		a.setA4("a4");
		r.save(a);
		
		//업데이트할 때 이 방법을 사용하면 안 된다 (직접 객체 생성해서 하는 것)
//		A a1 = new A();
//		a1.setA_1("one");
//		a1.setA_2(new BigDecimal(2.0));
//		r.save(a1);
//		//:
//		//r.save(a1);
//		//r.save(a2);
	}
	
	@Test
	void test2findById() {
		String a_1 = "one";
		Optional<A> optA = r.findById(a_1);
		Assertions.assertTrue(optA.isPresent());
		A a = optA.get();
		log.error("a_1{}, a_1{}", a.getA_1(), a.getA_2());
	}
	
	@DisplayName("UPDATE용도의 save()테스트")
	@Test
	void test3Save() {
		String a_1 = "one";
		Optional<A> optA = r.findById(a_1);
		Assertions.assertTrue(optA.isPresent());
		A a = optA.get();
		a.setA_2(new BigDecimal(3.0));
		r.save(a);
	}
	
	@Test
	@Transactional
	@Commit
	void test4DeleteById() {
		String a_1 = "one";
		r.deleteById(a_1); //entity가 없으면 silently ignore -> rollback발생! 이 방법을 더 권장
	}
	
	@Test
	@Transactional
	@Commit
	void test4Delete() {
		//select먼저 해서 행이 있는지 찾아보고 delete하는 것을 권장
		String a_1 = "one";
//		A a = new A();
//		a.setA_1(a_1);
//		r.delete(a); //행이 없을 때 -> 그냥 행이 없구나 하고 끝 rollback예외발생 안 함
		Optional<A> aOpt = r.findById(a_1);
		aOpt.ifPresent(a->r.delete(a));
	}
	
	@Test
	void test5QueryMethod() {
		List<A> list = r.findByA4("a4one");
		log.error("r.findByA4(a4one)결과 : {}", list);
	}
	
	@Test
	void test6FindByA4Like() {
		String word="%4f%";
		List<A> list = r.findByA4Like(word);
		log.error("r.findByA4Like(4f)결과 : {}", list);
				
	}
	
	@Test 
	void test7FindByA4LikeOrderByA4() {
		String word="%4f%";
		List<A> list = r.findByA4LikeOrderByA4(word);
		log.error("r.findByA4LikeOrderByA4(4f) 결과 : {}", list);
	}
	
	@Test 
	void test8FindByA4LikeJpql() {
		String word="%4f%";
		List<A> list = r.findByA4LikeJpql(word);
		log.error("r.findByA4LikeJpql(4f) 결과 : {}", list);
	}
	
	@Test 
	void test9FindByA4LikeJpqlA1A4() {
		String word="%4f%";
		List<Object[]> list = r.findByA4LikeJpqlA1A4(word);
		for(Object[] arr : list) {
			log.error("r.findByA4LikeJpqlA1A4(4f) 결과 : {},{}", arr[0], arr[1]);
		}
	}
	
	@Test 
	@Transactional
	@Commit
	void test10Modify() {
		String a_1="one";
		BigDecimal a_2 = new BigDecimal(999);
		r.modify(a_1, a_2);
	}
	
}
