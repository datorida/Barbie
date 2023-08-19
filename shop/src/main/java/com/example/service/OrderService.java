package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.Order;
import com.example.dto.Product;
import com.example.frame.MyService;

@Service
public class OrderService implements MyService<Integer, Order>{

	@Override
	public void register(Order v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(Order v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order get(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public boolean authenticate(String mem_id, String pwd) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product getProductByProductNum(int productNum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
