package com.my.board.service;

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
	private BoardRepository repository;
	
	public BoardEntity boardToVo(Board board) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = board;
		Class<BoardEntity> destinationType = BoardEntity.class;
		BoardEntity entity = mapper.map(source, destinationType); //DTO->VO
		log.error("entity boardNo:{}, boardTitle:{}, boardId:{}, boardDt:{}, boardContent:{}",
					entity.getBoardNo(),
					entity.getBoardTitle(),
					entity.getBoardId(),
					entity.getBoardDt(),
					entity.getBoardContent()
				);
		
		return entity;
	}
	
	public ReplyEntity replyToVo(Reply reply) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = reply;
		Class<ReplyEntity> destinationType = ReplyEntity.class;
		ReplyEntity entity = mapper.map(source, destinationType); //DTO->VO
		log.error("entity replyNo:{}, replyParentNo:{}, replyId:{}, replyDt:{}, replyContent:{}, replyBoardNo:{}",
					entity.getReplyNo(),
					entity.getReplyParentNo(),
					entity.getReplyId(),
					entity.getReplyDt(),
					entity.getReplyContent(),
					entity.getReplyBoardNo()
				);
		
		return entity;
	}
	
	public Board VoToBoard(BoardEntity entity) {
		//Optional<BoardEntity> optS = r.findById(corNo);
		//entity = optS.get();
		
		Board board = new Board();
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = entity;
		Class<Board> destinationType = Board.class;
		Board dto = mapper.map(source, destinationType);
		log.error("dto boardNo:{}, boardTitle:{}, boardId:{}, boardDt:{}, boardContent:{}",
					board.getBoardNo(),
					board.getBoardTitle(),
					board.getBoardId(),
					board.getBoardDt(),
					board.getBoardContent());
		
		return board;
	}
	
	public Reply VoToReply(ReplyEntity entity) {
		Reply reply = new Reply();
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = entity;
		Class<Board> destinationType = Board.class;
		Board dto = mapper.map(source, destinationType);
		log.error("dto replyNo:{}, replyParentNo:{}, replyId:{}, replyDt:{}, replyContent:{}, replyBoardNo:{}",
				reply.getReplyNo(),
				reply.getReplyParentNo(),
				reply.getReplyId(),
				reply.getReplyDt(),
				reply.getReplyContent(),
				reply.getReplyBoardNo());
		
		return reply;
	}
}
