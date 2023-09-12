package com.my.product.service;

import java.util.List;

import com.my.exception.FindException;
import com.my.product.dao.ProductOracleRepository;
import com.my.product.dao.ProductRepository;
import com.my.product.dto.Product;

public class ProductService {
	private ProductRepository repository;
	public ProductService() {
		repository = new ProductOracleRepository();
	}
	public List<Product> findAll(int currentPage) throws FindException{
		if(currentPage <1) {
			currentPage = 1;
		}
		int cntPerPage = 3; //한페이지당 보여줄 목록 수
		
		int startRow = 0;
		int endRow = 0;
		
		startRow = currentPage+1;
		endRow = currentPage*cntPerPage;
		
		return repository.selectAll(startRow, endRow);
	}

}
