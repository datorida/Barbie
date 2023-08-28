package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.dto.GuestCart;
import com.example.frame.MyMapper;

@Mapper
@Repository
public interface GuestCartMapper extends MyMapper<Integer, GuestCart> {

}
