package com.my.jpa.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.my.jpa.entity.A;

//CrudRepository는 단순 crud이고 JpaRepository가 page를 포함하고 있음
public interface ARepository extends JpaRepository<A, String>, ASearch{ //entity타입과 key타입
	//쿼리메서드작성 가능 : findBy멤버변수명, 반환형은 List자료형
	List<A> findByA4(String a4);
	
	/**
	 * 단어를 포함한 a4멤버변수를 갖는 entity 객체들을 반환한다
	 * @param word 단어
	 * @return
	 */
	List<A> findByA4Like(String word); //SELECT FROM WHERE a4 LIKE '%?%'
	
	List<A> findByA4LikeOrderByA4(String word); //WHERE a4 LIKE '%?%' ORDER BY a_2
	//JPQL문법을 사용 
//	@Query("SELECT p FROM 엔티티클래스 AS p WHERE 멤버변수 LIKE %:word%")
//	@Query(value="SELECT * FROM 테이블", nativeQuery = true)
//    public List<A> findByName(@org.springframework.data.repository.query.Param("word") String name);
//	
	//JPQL문법을 사용
//	@Query("SELECT a FROM A AS a WHERE a4 LIKE %:word%")
	@Query(value="SELECT * FROM a_tbl WHERE a4 LIKE :word", nativeQuery = true)
	public List<A> findByA4LikeJpql(String word); //아무 이름이나 써도 됨
	
	@Query(value="SELECT a_1, a4 FROM a_tbl WHERE a4 LIKE :word", nativeQuery=true) //: 뒤에 공백 주의...
	public List<Object[]> findByA4LikeJpqlA1A4(String word);
	
	@Modifying
	@Query(value="UPDATE a_tbl SET a_2=:a_2 WHERE a_1=:a_1", nativeQuery=true)
	public void modify(String a_1, BigDecimal a_2);
}
