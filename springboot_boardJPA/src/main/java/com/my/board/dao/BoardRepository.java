package com.my.board.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, String>{

}
