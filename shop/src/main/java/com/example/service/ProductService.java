package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Product;
import com.example.frame.MyService;
import com.example.mapper.ProductMapper;

@Service
public class ProductService implements MyService{

	@Autowired
	ProductMapper mapper;
	
	public Product getProductList() {

		return null;
	}

	@Override
	public void register(Object v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Object k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(Object v) throws Exception {
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
	public Object get(Object k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* 상품의 재고가 있으면 False로 , 없으면 true */
	public boolean isOutOfStock(int productNum, int quantityToCheck) {
	    Product product = mapper.getProductByProductNum(productNum);
	    
	    if (product == null || product.getQuantity() == 0) {
	        // 상품이 없거나 상품의 수량이 0인 경우
	        return true;
	    }
	    
	    return product.getQuantity() < quantityToCheck;
	}




}
