package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.dto.Cart;
import com.example.frame.MyMapper;

@Mapper
@Repository
public interface CartMapper extends MyMapper<Integer, Cart> {

	Cart findCart(int memberNum, int productNum);



	void addToCart(Cart cartItem);

}
