package com.my.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.transaction.annotation.Transactional;

import com.my.board.dao.BoardOracleRepository;
import com.my.board.dto.Board;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;

@SpringBootTest
class BoardRepositoryTest {
	@Autowired
	BoardOracleRepository repository;
	@Test
	@DisplayName("게시글 전체 검색 리스트")
	void testSelectAll() throws FindException {
		List<Board> list = repository.selectAll();
		int expectedSize = 3;
		Assertions.assertEquals(3,list.size());
	}
	
	@Test
	@DisplayName("게시글 1번 상세조회")
	void testSelectByBoardNo() throws FindException{
		int boardNo = 1;
		Board board = repository.selectByBoardNo(boardNo);
		String expectedTitle = "제목1";
		int expectedReplySize = 4;
		Assertions.assertEquals(expectedTitle, board.getBoardTitle());
		Assertions.assertEquals(expectedReplySize, board.getReplies().size());
	}
	
	@Test
	@DisplayName("존재하지 않는 게시글번호로 상세조회")
	void testSelectByBoardNo2() throws FindException{
		int boardNo = 0;
		Board board = repository.selectByBoardNo(boardNo);
		Assertions.assertNull(board);
		
		Assertions.assertThrows(FindException.class,()->{
			repository.selectByBoardNoOptional(boardNo);
		});
	}
	
	@Test
	@DisplayName("게시글 작성")
	@Transactional //실제로 DB에는 들어가지 않음, 자동 rollback을 해주기 때문
	void testinsertBoard() throws AddException{
		Board board = new Board();
		board.setBoardTitle("제목6");
		board.setBoardContent("내용6");
		board.setBoardId("B");
		board.setBoardDt(new Date());
		repository.insertBoard(board);
	}
	
//	@Test
//	@DisplayName("게시글 수정")
//	@Transactional //실제로 DB에는 들어가지 않음, 자동 rollback을 해주기 때문
//	void testupdateBoard() throws ModifyException{
//		Board board = new Board();
//		board.setBoardTitle("제목6");
//		board.setBoardContent("내용6");
//		board.setBoardId("B");
//		board.setBoardDt(new Date());
//		repository.insertBoard(board);
//	}

}
