package com.my.board.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.my.board.dto.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor

@Getter @Builder

@Entity
@DynamicInsert
@Table(name="board_reply_tbl")
@SequenceGenerator(name="replytbl_seq_generator", sequenceName ="replytbl_seq",initialValue = 1, allocationSize=1)
public class ReplyEntity {
	@Id
	@Column(name="reply_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "replytbl_seq_generator")
	private Long replyNo;
	
	@Column(name="reply_parent_no")
	private Long replyParentNo;
	
	@Column(name="reply_id")
	private String replyId;
	
	@Column(name="reply_dt")
	@ColumnDefault(value = "SYSDATE")
	private Date replyDt;
	
	@Column(name="reply_content")
	private String replyContent;
	
	@OneToMany
	@JoinColumn(name="reply_parent_no")
	private List<ReplyEntity> reply;

	@Column(name="reply_board_no") //FK
	private Long replyBoardNo;
	
	/**
	 * 내용을 변경한다
	 * @param 내용
	 */
	public void modifyReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}	

}
