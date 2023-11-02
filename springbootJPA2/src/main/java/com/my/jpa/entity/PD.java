package com.my.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pd_tbl")
public class PD {
	@Id
	@Column(name="pd_no")
	private String pdNo; //상품번호
	
	@Column(name="pd_cal")
	private int cal;
	
	@Column(name="pd_na")
	private int na;
	
	//자식쪽 : @ManyToOne역할
	@OneToOne
	@JoinColumn(name="pd_no") //FK
//	@MapsId("pNo") //있어도 무시되지만 안 쓰는 것이 맞음
	private P p; //부모쪽에서의 FK
}
