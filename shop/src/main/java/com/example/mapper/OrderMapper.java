package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.dto.Order;
import com.example.frame.MyMapper;

@Mapper
@Repository
public interface OrderMapper extends MyMapper<Integer, Order> {

}
