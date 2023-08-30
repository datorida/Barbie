package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Product;
import com.example.frame.MyService;
import com.example.mapper.ProductMapper;

@Service
public class ProductService implements MyService<Integer, Product>{

	@Autowired
	ProductMapper mapper;
	
	public Product getProductList() {

		return null;
	}

	@Override
	public void register(Product v) throws Exception {

		
	}

	@Override
	public void remove(Integer k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(Product v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product getProductByProductNum(int productNum) throws Exception {
	
		return mapper.getProductByProductNum(productNum);
	}

	@Override
	public List<Product> get() throws Exception {
		return mapper.selectAll();
	}

	@Override
	public boolean authenticate(String mem_id, String pwd) throws Exception {
		return false;
	}

	public String selectCategoryNamebyCategoryNum(int categoryNum) {
		return mapper.selectCategoryNameByCategoryNum(categoryNum);
	}

	@Override
	public Product get(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* 상품의 재고가 있으면 False로 , 없으면 true */
	public boolean isOutOfStock(int productNum, int quantityToCheck) {
	 int productQuantity=mapper.isOutOfStock(productNum);
	 
	 if(productQuantity == 0) {
		 return true;
	 }
	 return productQuantity < quantityToCheck; 
	 //재고가 주문수량보다 작을경우 true반환, 그렇지않으면 false반환
	 //비교 연산자(<)는 왼쪽 피연산자가 오른쪽 피연산자보다 작을 때 true를 반환하고, 그렇지 않으면 false를 반환합니다. 
	}

	



}