package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.GuestCart;
import com.example.dto.Product;
import com.example.frame.MyService;
import com.example.mapper.GuestCartMapper;

@Service
public class GuestCartService implements MyService<Integer,GuestCart>{

	@Autowired
	GuestCartMapper guestmapper;

	@Override
	public void register(GuestCart v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Integer guestCartId) throws Exception {
		guestmapper.delete(guestCartId);
		
	}

	@Override
	public void modify(GuestCart guest) throws Exception {
		guestmapper.update(guest);
		
	}

	@Override
	public GuestCart get(Integer k) throws Exception {
		
		return null;
	}

	@Override
	public List<GuestCart> get() throws Exception {
		return guestmapper.selectAll();
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
	
	public GuestCart getCartBytemporaryIdentifier(String temporaryIdentifier) {
		return guestmapper.getCartBytemporaryIdentifier(temporaryIdentifier);
	}


	public void addToGuestCart(GuestCart guestCart) {
	    guestmapper.addToGuestCart(guestCart);
	}


	public List<GuestCart> getCartListBytemporaryIdentifier(String temporaryIdentifier) {
	    return guestmapper.getCartListBytemporaryIdentifier(temporaryIdentifier);
	}



	
	
}