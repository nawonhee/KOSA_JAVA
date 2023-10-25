package com.my.board.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@ToString
public class Board {
	private Integer boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardId;
	private Date boardDt;
	private List<Reply> replies; //답글목록
	private Integer replycnt; //답글수
}
