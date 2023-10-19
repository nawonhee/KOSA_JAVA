package com.my.product.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString 
@NoArgsConstructor @AllArgsConstructor
public class Product {
	private String prodNo;
	private String prodName;
	private int prodPrice;
	
	
	public boolean equals(Product p) {
		return (this.prodNo).equals(p.prodNo);
	}
	
	public boolean equals(String no) {
		return (this.prodNo).equals(no);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(prodNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		//if (getClass() != obj.getClass())
		//	return false;
		Class currentClass = this.getClass();
		Class paramClass = obj.getClass();
		if(currentClass != paramClass) {
			return false;
		}
		Product other = (Product) obj;
		return Objects.equals(prodNo, other.prodNo);
	}
	
	public String toString() {
		return prodNo+":"+prodName+":"+prodPrice+"\n";
	}
}
