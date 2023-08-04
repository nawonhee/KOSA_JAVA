/*
package com.my.product.dao;
 

import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dto.Product;

public abstract class ProductDAOList implements ProductDAOInterface{
	private List<Product> products;
	public ProductDAOList() {
		products = new ArrayList<>();
	}
	
	@Override
	public void insert(Product product) throws AddException {
		for(Product p: products) {
			if(p.getProdNo().equals(product.getProdNo())){
				throw new AddException("이미 존재하는 상품입니다");
			}
		}
		products.add(product);
	}

	@Override
	public Product selectByProdNo(String no) throws FindException {
		for(Product p: products) {
			if(p.getProdNo().equals(no)) {
				return p;
			}
		}
		throw new FindException("상품이 없습니다");
	}

	@Override
	public Object selectAll() throws FindException {
		if(products.size()==0) {
			throw new FindException("상품이 한개도 없습니다");
		}
		return products;
	}
	
	@Override
	public void update(Product p) throws ModifyException{
		for(Product savedP: products) {
			if(savedP.getProdNo().equals(p.getProdNo())) {
				if(savedP.getProdNo().equals(p.getProdNo())) {
					if(p.getProdName()!=null) {
						savedP.setProdName(p.getProdName());
					}
					if(p.getProdPrice()!=0) {
						savedP.setProdPrice(p.getProdPrice);
					}
					return;
				}
			}
		}
		throw new ModifyException("상품이 없습니다");
	}
	
	@Override
	public void delete(String prodNo) throws RemoveException{
		for(Product savedP: products) {
			if(savedP.getProdNo().equals(prodNo)) {
				products.remove(savedP);
				return;
			}
		}
		throw new RemoveException("상품이 없습니다");
	}
}
*/
package com.my.product.dao;

import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dto.Product;

public class ProductDAOList  implements ProductDAOInterface{
	private List<Product> products;
	public ProductDAOList() {
		products = new ArrayList<>();
	}
	@Override
	public void insert(Product product) throws AddException {
		for(Product p: products) {
			//if(p.getProdNo().equals(product.getProdNo()))
			if(p.equals(product)){
				throw new AddException("이미 존재하는 상품입니다");
			}
		}
		
		products.add(product);
	}

	@Override
	public Product selectByProdNo(String no) throws FindException {
		for(Product p: products) {
			if(p.getProdNo().equals(no)) {
				return p;
			}
		}
		throw new FindException("상품이 없습니다");
	}

	@Override
	public Object selectAll() throws FindException {
		if(products.size() == 0) {
			throw new FindException("상품이 한개도 없습니다");
		}
		return products;
	}

	@Override
	public void update(Product p) throws ModifyException {
		for(Product savedP: products) {
			if(savedP.getProdNo().equals(p.getProdNo())) {
				if(p.getProdName() != null) {
					savedP.setProdName(p.getProdName());
				}
				
				if(p.getProdPrice() != 0) {
					savedP.setProdPrice(p.getProdPrice());
				}
				return;
			}
		}
		throw new ModifyException("상품이 없습니다");
	}

	@Override
	public void delete(String prodNo) throws RemoveException {
		for(Product savedP: products) {
			if(savedP.getProdNo().equals(prodNo)) {
				products.remove(savedP);
				return;
			}
		}
		throw new RemoveException("상품이 없습니다");
	}


}
