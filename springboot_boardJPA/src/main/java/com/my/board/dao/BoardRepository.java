package com.my.board.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.my.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
//	@Query(value= "SELECT new.com.my.board.BoardEntity(b.*, (SELECT COUNT(*) FROM board_reply WHERE reply_board_no=b.board_no) replycnt)\n"
//			+"FROM board b\n"
//			+"ORDER BY board_no DESC", nativeQuery = true)
//	public List<BoardEntity> findAll();
	
	@Query(value="SELECT *"
			+ "	FROM board_tbl b LEFT JOIN"
			+ "	(SELECT level,r1.* FROM board_reply_tbl r1 START WITH reply_parent_no IS NULL CONNECT BY PRIOR reply_no =reply_parent_no"
			+ " 	ORDER SIBLINGS BY reply_no DESC) r"
			+ "	ON b.board_no = r.reply_board_no"
			+ "	WHERE board_no =:boardNo", nativeQuery = true)
	public List<Object[]> findByBoardNo(Long boardNo);	
}
