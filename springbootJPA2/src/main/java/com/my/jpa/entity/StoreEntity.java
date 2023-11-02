package com.my.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor  //얘는 필요하다
@AllArgsConstructor  //얘는 필요없다 (builder 사용할 거니까)

//@Setter
@Getter
@Builder

@DynamicInsert //INSERT될 때 Null이 아닌 컬럼들만 사용한다
@Entity
@Table(name="store_tbl")
public class StoreEntity {
	@Id
	@Column(name="st_corno")
	private String corNo;  //사업자번호

	@Column(name="st_name")
	private String name;  //상호

	@Column(name = "st_dt")
	@ColumnDefault(value = "SYSDATE")
	private Date dt;//등록일자
	
	/**
	 * 상호명을 변경한다
	 * @param name
	 */
	public void modifyName(String name) {
		this.name = name;
	}	
}
