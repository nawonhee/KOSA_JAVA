package com.my.jpa.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter @Getter @NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name="line_tbl")
public class Line{
	@EmbeddedId
	private LineEmbedded id = new LineEmbedded();
	
	@ManyToOne
	@JoinColumn(name="line_no") //FK
	@MapsId("lineNo")
	private Info info;
	
	@ManyToOne
	@JoinColumn(name="line_pno")
	@MapsId("pNo")
	private P lineP;
	
	
	@Column(name="line_q")
	private int lineQuantity;
	
}