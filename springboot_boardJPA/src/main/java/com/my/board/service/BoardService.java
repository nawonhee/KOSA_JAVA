package com.my.board.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.BoardOracleRepository;
import com.my.board.dao.BoardRepository;
import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.board.entity.BoardEntity;
import com.my.board.entity.ReplyEntity;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {
	@Autowired
	private BoardRepository br;
	
	public BoardEntity boardToVo(Board board) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = board;
		Class<BoardEntity> destinationType = BoardEntity.class;
		BoardEntity entity = mapper.map(source, destinationType); //DTO->VO
		
		return entity;
	}
	
	
	public Board voToBoard(BoardEntity entity) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = entity;
		Class<Board> destinationType = Board.class;
		Board board = mapper.map(source, destinationType);
		
		return board;
	}
	
	// -----------------------------------------------------------
	
	
	public List<Board> findAll() throws FindException{
		List<BoardEntity> entity = br.findAll();
		List<Board> boardList = new ArrayList<>();
		
		for(int i=0;i<entity.size();i++) {
			Board board = new Board();
			board = voToBoard(entity.get(i));
			board.setReplycnt(board.getReplies().size());
			boardList.add(board);
		}
		
		return boardList;
	}
	
	public Board findByBoardNo(Long boardNo) throws FindException{
		List<Object[]> entity = br.findByBoardNo(boardNo);
		Object[] obj = entity.get(0);
		List<Reply> replies = new ArrayList<>();
		
		Board board = Board.builder()
							.boardNo(((BigDecimal)obj[0]).longValue())
							.boardContent((String)obj[1])
							.boardDt((Date)obj[2])
							.boardId((String)obj[3])
							.boardTitle((String)obj[4])
							.build();
		
		for(Object replyObj : entity) {
			Reply reply = Reply.builder()
								.level(((BigDecimal)obj[5]).longValue())
								.replyNo(((BigDecimal)obj[6]).longValue())
								.replyBoardNo(((BigDecimal)obj[7]).longValue())
								.replyContent((String)obj[8])
								.replyDt((Date)obj[9])
								.replyId((String)obj[10])
								.build();
			if(obj[11]!=null) {
				reply.setReplyParentNo(((BigDecimal)obj[11]).longValue());
			}
			replies.add(reply);
		}
		
		board.setReplies(replies);
		board.setReplycnt(replies.size());
		
		return board;
	}
	
	public void write(Board board) throws AddException{
		BoardEntity entity = boardToVo(board);
		br.save(entity);
	}
	
	public void modify(Board board) throws ModifyException{
		Optional<BoardEntity> optB = br.findById(board.getBoardNo());
		BoardEntity entity = optB.get();
		entity.modifyContent(board.getBoardContent());
		br.save(entity);
	}
	
	public void remove(Long boardNo) throws RemoveException{
		br.deleteById(boardNo);
	}
	
}
