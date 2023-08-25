package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.dto.Cart;
import com.example.frame.MyMapper;

@Mapper
@Repository
public interface CartMapper extends MyMapper<Integer, Cart> {

	Cart findCart(int memberNum, int productNum);



	void addToCart(Cart cartItem);


	List<Cart> getCartByMemberNum(int memberNum);



	void updateCartItem(Cart existingCartItem);



	void addToGeustCart(Cart guestCart);



	Cart AsGuestFindCart(String temporaryIdentifier, int productNum);


	void updateGuestCart(Cart guestCart);



	Cart getGuestCartByTemporaryIdentifier(String temporaryIdentifier, int productNum);




	void saveTemporaryIdentifier(Cart cart);









}
