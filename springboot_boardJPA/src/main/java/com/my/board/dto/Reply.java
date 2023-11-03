package com.my.board.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@ToString
@Builder
public class Reply {
	private Long replyNo;
	private Long replyBoardNo;
	private Long replyParentNo;
	private String replyContent;
	private String replyId;
	private Date replyDt;
	private Long level;
}
