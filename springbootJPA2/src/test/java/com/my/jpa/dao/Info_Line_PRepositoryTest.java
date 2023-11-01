package com.my.jpa.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.jpa.entity.Info;
import com.my.jpa.entity.Line;
import com.my.jpa.entity.P;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class Info_Line_PRepositoryTest {

	@Autowired
	InfoRepository infoR;
	@Test
	void test1Info_save() {
		Info info = new Info();
		info.setInfoId("id1"); //주문자 아이디
		info.setInfoDt(new Date(new java.util.Date().getTime())); //주문시간
		List<Line>lines = new ArrayList<>();
		for(int i=1;i<=2;i++) {
			Line line = new Line(); //상세
			line.setLineQuantity(i);
			
			line.setInfo(info); //자식쪽에서 부모와 연결
			P p = new P();
			p.setPNo("C"+i);
			line.setLineP(p);
			lines.add(line);
		}
		info.setLines(lines); //부모쪽에서 자식과 연결
		infoR.save(info);
	}
	
	@Test
	void test2Info_findById() {
		Long infoNo = 1L;
		Optional<Info> optInfo = infoR.findById(infoNo);
		Assertions.assertTrue(optInfo.isPresent());
		
		Info info = optInfo.get();
		log.error("주문자아이디:{}", info.getInfoId());
		log.error("주문일자:{}", info.getInfoDt());
		log.error("주문상세:{}", info.getLines());
	}
	
	@Test
	@Transactional
	@Commit
	void test3Info_deleteById() {
		Long infoNo = 1L;
		infoR.deleteById(infoNo);
	}
}
