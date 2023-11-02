package com.my.board.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor

@Getter @Builder

@Entity
@Table(name="board_reply_tbl")
@SequenceGenerator(name="replytbl_seq_generator", sequenceName ="replytbl_seq",initialValue = 1, allocationSize=1)
public class ReplyEntity {
	@Id
	@Column(name="reply_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "replytbl_seq_generator")
	private Long replyNo;
	
	@ManyToOne
	@JoinColumn(name="reply_parent_no")
	private Long replyParentNo;
	
	@Column(name="reply_id")
	private String replyId;
	
	@Column(name="reply_dt")
	@ColumnDefault(value = "SYSDATE")
	private Date replyDt;
	
	@Column(name="reply_content")
	private String replyContent;
	
	@ManyToOne
	@JoinColumn(name="reply_board_no") //FK
	private Long replyBoardNo;
	
	private Long level;
}
