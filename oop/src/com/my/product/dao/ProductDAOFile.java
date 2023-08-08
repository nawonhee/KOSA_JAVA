package com.my.product.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.product.dto.Product;

public class ProductDAOFile implements ProductDAOInterface {
	private String fileName = "D:\\products.txt";
	public ProductDAOFile() {
		createFile();
	}
	
	private void createFile() {
		File file = new File(fileName);
		if(!file.exists()) { //없으면 만들어줘
			try {
				file.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void insert(Product product) throws AddException {
		try{
			selectByProdNo(product.getProdNo());
			throw new AddException("이미 존재하는 상품입니다");
		}catch(FindException e) {
			//e.printStackTrace();
			FileWriter fw = null;
			try {
				fw = new FileWriter(fileName, true);
				//String prodStr = product.getProdNo()+":"+product.getProdName()+":"+product.getProdPrice();
				String prodStr = product.toString();
				fw.write(prodStr);
			} catch (IOException e1) {
				e1.printStackTrace();
			}finally {
				if(fw!=null) {
					try {
						fw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public Product selectByProdNo(String no) throws FindException{
		Scanner sc = null;
		try{
			sc = new Scanner(new FileInputStream(fileName));
			while(sc.hasNextLine()) { //다음줄이 있을 동안 반복
				String line = sc.nextLine();
				String[] arr = line.split(":");
				String prodNo = arr[0];
				if(prodNo.equals(no)) {
					return new Product(prodNo, arr[1], Integer.parseInt(arr[2]));
				}
			}
			throw new FindException("번호에 해당하는 상품이 없습니다");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			createFile();
			throw new FindException("번호에 해당하는 상품이 없습니다");
		}finally {
			if(sc!=null) {
				sc.close();
			}
		}
	}
	
	public Object selectAll() throws FindException  {
		List<Product> all = new ArrayList<>();
		Scanner sc = null; //try안에 있으면 try블록에서만 쓸 수 있기 때문에 빼줘야 함
		try{
			sc = new Scanner(new FileInputStream(fileName));
			while(sc.hasNextLine()) { //다음줄이 있을 동안 반복
				String line = sc.nextLine();
				String[] arr = line.split(":");
				String prodNo = arr[0];
				String prodName = arr[1];
				int prodPrice = Integer.parseInt(arr[2]);
				Product p = new Product(prodNo, prodName, prodPrice);
				all.add(p);
			}
			if(all.size()==0) {
				throw new FindException("상품이 없습니다");
			}
			return all;
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			createFile();
			throw new FindException("상품이 없습니다");
		}finally {
			if(sc != null) {
				sc.close();
			}
		}
		
	}
	
	public void update(Product p) {
		FileWriter fw = null;
		try {
			List<Product> all = (List)selectAll();
			boolean updated = false;
			for(Product savedP : all) {
				//if(savedP.getProdNo().equals(p.getProdNo()))
				if(savedP.equals(p)) {
					if(p.getProdName()!=null) {
						savedP.setProdName(p.getProdName());
						updated = true;
					}
					if(p.getProdPrice()!=0) {
						savedP.setProdPrice(p.getProdPrice());
						updated = true;
					}
					break;
				}
			}
			if(updated) {
				fw = new FileWriter(fileName);
				for(Product savedP : all) {
					String pStr = p.toString();
					fw.write(pStr);
				}
			}
		}catch(FindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fw!=null) {
				try {
					fw.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void delete(String prodNo) {
		FileWriter fw = null;
		try{
			List<Product> all = (List)selectAll();
			//이렇게 해도 됨 : 미리 리스트에서 삭제할 애를 없애는 방법
			//Product savedP = new Product();
			//savedP.setProdNo(prodNo);
			//all.remove(savedP);
			fw = new FileWriter(fileName); //새로 써야 되니까 false써도되고 그냥 이름만 써도 됨
			for(Product p : all) {
				if(!p.getProdNo().equals(prodNo)) {
					String pStr = p.toString();
					fw.write(pStr);
				}
			}
		}catch(FindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fw!=null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
