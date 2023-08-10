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
		// TODO Auto-generated method stub
		return mapper.getProductByProductNum(productNum);
	}

	@Override
	public List<Product> get() throws Exception {
		return mapper.selectAll();
	}

	@Override
	public boolean authenticate(String mem_id, String pwd) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public String selectCategoryNamebyCategoryNum(int categoryNum) {
		// TODO Auto-generated method stub
		return mapper.selectCategoryNameByCategoryNum(categoryNum);
	}

	@Override
	public Object get(Object k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	


}
