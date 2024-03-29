package com.my.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.exception.FindException;
import com.my.product.dao.ProductOracleMybatisRepository;
import com.my.product.dao.ProductOracleRepository;
import com.my.product.dao.ProductRepository;
import com.my.product.dto.PageGroup;
import com.my.product.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service(value="productService")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	public PageGroup<Product> findAll(int currentPage) throws FindException{
		if(currentPage <1) {
			currentPage = 1;
		}
		int cntPerPage = 3; //한페이지당 보여줄 목록 수
		
		int startRow = 0;
		int endRow = 0;
		
		startRow = (currentPage -1)*cntPerPage +1;
		endRow = currentPage*cntPerPage;
		
		//return repository.selectAll(startRow, endRow);

		List<Product> list = repository.selectAll(startRow, endRow);
		
		int totalCnt = repository.selectCount();
		
		/*
		int totalPage; //총페이지수 계산
		int cntPerPageGroup = 2; //페이지그룹에 보여줄 페이지목록 수
		
		//currentPage   //1  2  3  4  5
		int startPage;  //1  1  3  3  5
		int endPage;    //2  2  4  4  6
		*/
		
		PageGroup<Product> pg = new PageGroup<>(list, currentPage, totalCnt); 
		return pg;
	}
	
	public Product findByProdNo(String prodNo) throws FindException{
		return repository.selectByProdNo(prodNo);
	}

}
