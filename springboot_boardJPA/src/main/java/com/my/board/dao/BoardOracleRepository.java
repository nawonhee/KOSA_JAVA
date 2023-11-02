package com.my.board.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Repository
public class BoardOracleRepository {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 게시글 목록을 검색한다
	 * @return 게시글 목록
	 * @throws FindException
	 */
	public List<Board> selectAll() throws FindException{
		SqlSession session = null;
		try{
			session = sqlSessionFactory.openSession();
			List<Board> list = session.selectList("com.my.board.BoardMapper.selectAll");
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	public Board selectByBoardNo(int boardNo) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Board board = session.selectOne("com.my.board.BoardMapper.selectByBoardNo",boardNo);
			return board;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	public Optional<Board> selectByBoardNoOptional(int boardNo) throws FindException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Board b =  session.selectOne("com.my.board.BoardMapper.selectByBoardNo", boardNo);
			Optional<Board> optB = Optional.of(b);
			return optB;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	public void insertBoard(Board board) throws AddException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.my.board.BoardMapper.insertBoard", board);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	public void updateBoard(Board board) throws ModifyException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.my.board.BoardMapper.updateBoard", board);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	public void deleteBoard(int boardNo) throws RemoveException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.my.board.BoardMapper.deleteBoard", boardNo);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	public void insertReply(Reply reply) throws AddException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.my.board.BoardMapper.insertReply", reply);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	public void updateReply(Reply reply) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.my.board.BoardMapper.updateReply", reply);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	public void deleteReply(int replyNo) throws RemoveException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.my.board.BoardMapper.deleteReply", replyNo);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
}
