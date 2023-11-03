package com.my.board.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.my.board.dto.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor @AllArgsConstructor

@Getter @Builder

@DynamicInsert
@Entity
@Table(name="board_tbl")
@SequenceGenerator(name="boardtbl_seq_generator", sequenceName ="boardtbl_seq",initialValue = 1, allocationSize=1)
public class BoardEntity {
	@Id
	@Column(name="board_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardtbl_seq_generator")
	private Long boardNo;
	
	@Column(name="board_title")
	private String boardTitle;
	
	@Column(name="board_id")
	private String boardId;
	
	@Column(name="board_dt")
	@ColumnDefault(value = "SYSDATE")
	private Date boardDt;
	
	@Column(name="board_content")
	private String boardContent;
	
	@OneToMany (
			fetch = FetchType.EAGER,
			cascade = CascadeType.REMOVE)
	@JoinColumn(name="reply_board_no")
	private List<ReplyEntity> replies; //답글목록
	
//	@Column(name="board_reply_cnt")
	@Transient
	private Integer replyCnt; //답글수

	/**
	 * 내용을 변경한다
	 * @param 내용
	 */
	public void modifyContent(String boardContent) {
		this.boardContent = boardContent;
	}	
}
