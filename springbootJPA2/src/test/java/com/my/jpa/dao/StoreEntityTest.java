package com.my.jpa.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.jpa.dto.StoreDTO;
import com.my.jpa.entity.StoreEntity;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class StoreEntityTest {
	@Autowired
	StoreEntityRepository r;
	@Test
	void testInsert() {
		//StoreEntity s = new StoreEntity();
		StoreEntity s = StoreEntity
						.builder()
						.corNo("사업자번호1")
						.name("음식점1")
						.build();
		log.error("INSERT용 엔티티객체 사업자번호:{}, 상호명:{}",s.getCorNo(),s.getName());
		r.save(s);
	}
	
	@Test
	void test2Update() {
		String corNo = "사업자번호1";
		r.findById(corNo);
		Optional<StoreEntity> optS = r.findById(corNo);
		StoreEntity s = optS.get();
		s.modifyName("다른음식점");  
		r.save(s);  //문제 없으면 자동커밋됨 
	}
	
	@Test
	void test3Delete() {
		String corNo="사업자번호1";
		r.deleteById(corNo);
	}
	
	@Test
	void test4DtoToVo() {  //DTO -> Vo
//		StoreDTO dto = StoreDTO
//						.builder()
//						.corNo("사업자번호2")
//						.name("음식점2")
//						.dt(new java.util.Date())
//						.build();
		StoreDTO dto = new StoreDTO();
		StoreEntity entity = StoreEntity
								.builder()
								.corNo(dto.getCorNo())
								.name(dto.getName())
								.dt(new java.sql.Date(dto.getDt().getTime()))
								.build();
	}
	
	@Test
	void test5DtoToVo_ModelMapper() {
//		StoreDTO dto = StoreDTO
//						.builder()
//						.corNo("사업자번호2")
//						.name("음식점")
//						.dt(new java.util.Date())
//						.build();
		StoreDTO dto = new StoreDTO();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = dto;
		Class<StoreEntity> destinationType = StoreEntity.class;
		StoreEntity entity = mapper.map(source, destinationType); //DTO->VO
		log.error("entity corNo:{}, name:{}, dt:{}",
					entity.getCorNo(),
					entity.getName(),
					entity.getDt()
				);
	}
	
	@Test
	void test6VoToDto_ModelMapper() {
		String corNo = "사업자번호1";
		Optional<StoreEntity> optS = r.findById(corNo);
		StoreEntity sEntity = optS.get();
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD) //strict보단 이거 쓰는 게 더 좋음
//		    .setMatchingStrategy(MatchingStrategies.STRICT)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = sEntity;
		Class<StoreDTO> destinationType = StoreDTO.class;
		StoreDTO dto = mapper.map(source, destinationType);
//		StoreDTO dto = mapper
//						.typeMap(null, null) //멤버변수가 다른 경우
//						.addMapping(null, null);
		log.error("dto corNo:{}, name:{}, dt:{}",
					dto.getCorNo(),
					dto.getName(),
					dto.getDt());
	}

}
