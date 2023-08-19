package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Cart;
import com.example.dto.Order;
import com.example.dto.Product;
import com.example.frame.MyService;
import com.example.mapper.CartMapper;

@Service
public class CartService implements MyService<Integer,Cart>{

	@Autowired
	CartMapper cmapper;
	
	@Override
	public void register(Cart v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(Cart v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cart get(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> get() throws Exception {
		return cmapper.selectAll();
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

	public Cart findCart(int memberNum, int productNum) {
		return cmapper.findCart(memberNum, productNum);
	}

	public void addToCart(int memberNum, int productNum, int quantityToCheck) {
		Cart cartItem = new Cart();
		cartItem.setMemberNum(memberNum);
		cartItem.setProductNum(productNum);
		cartItem.setCounts(quantityToCheck);
		
		cmapper.addToCart(cartItem);
	}



}
