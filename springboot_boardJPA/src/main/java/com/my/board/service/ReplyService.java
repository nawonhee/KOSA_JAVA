package com.my.board.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.BoardRepository;
import com.my.board.dao.ReplyRepository;
import com.my.board.dto.Reply;
import com.my.board.entity.BoardEntity;
import com.my.board.entity.ReplyEntity;
import com.my.exception.AddException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Service
public class ReplyService {
	@Autowired
	private ReplyRepository rr;
	
	public ReplyEntity replyToVo(Reply reply) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = reply;
		Class<ReplyEntity> destinationType = ReplyEntity.class;
		ReplyEntity entity = mapper.map(source, destinationType); //DTO->VO
		
		return entity;
	}
	
	public Reply voToReply(ReplyEntity entity) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = entity;
		Class<Reply> destinationType = Reply.class;
		Reply reply = mapper.map(source, destinationType);
		
		return reply;
	}
	
	// -----------------------------------------------------------
	
	public void writeReply(Reply reply) throws AddException{
		ReplyEntity entity = replyToVo(reply);
		rr.save(entity);
	}
	
	public void updateReply(Reply reply) throws ModifyException{
		Optional<ReplyEntity> optR = rr.findById(reply.getReplyNo());
		ReplyEntity entity = optR.get();
		entity.modifyReplyContent(reply.getReplyContent());
		rr.save(entity);
	}
	
	public void deleteReply(Long replyNo) throws RemoveException{
		rr.deleteById(replyNo);
	}
}
