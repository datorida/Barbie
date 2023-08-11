package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.dto.Cart;
import com.example.dto.Order;
import com.example.frame.MyMapper;

@Mapper
@Repository
public interface CartMapper extends MyMapper<Integer, Order> {

	Cart findCart(int memberNum, int productNum);


	void addToCart(int MemberNum, int product_num, int counts);


	void updateCart(int memberNum, int counts, int product_num);

}
