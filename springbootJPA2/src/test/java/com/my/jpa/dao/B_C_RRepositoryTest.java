package com.my.jpa.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.jpa.entity.B;
import com.my.jpa.entity.C;
import com.my.jpa.entity.R;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class B_C_RRepositoryTest {
	@Autowired
	BRepository br;
	
	@Autowired
	CRepository cr;
	
	@Autowired
	RRepository rr;
	
	@Test
	@Transactional
	@Commit
	void test1C_Save() {
		for(int i=1;i<=5;i++) {
			C c = new C("id"+i,"n"+i);
			cr.save(c);
		}
	}
	
	@Test
	@Transactional
	@Commit
	void test1B_Save() {
		Optional<C> optC = cr.findById("id1");
		Assertions.assertTrue(optC.isPresent());
		
		C c = optC.get();
		for(int i=1;i<=2;i++) {
			B b = new B();
			b.setBC(c); //게시글 작성자
			br.save(b);
		}
	}
	
	@Test
	@Transactional
	@Commit
	void test3B_Find() {
		br.findAll();
		Iterable<B> it = br.findAll();
		log.error("br.findAll(): {}"+it);
	}
	
	@Test
	@Transactional
	@Commit
	void test4R_Save() {
		for(long no=2;no<=4;no++) {	
			R r = new R();
			r.setRNo(1L); //댓글번호
			r.setRContent("댓글1");
			r.setBNo(1L); //1번글에 대한 댓글
			rr.save(r);
		}
	}
	
	@Test
	@Transactional
	@Commit
	void test5B_DeleteById() {
		br.deleteById(1L);
	}
	
}
