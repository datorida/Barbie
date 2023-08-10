package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.dto.Category;
import com.example.dto.Product;
import com.example.frame.MyMapper;

@Mapper
@Repository
public interface CategoryMapper extends MyMapper<Integer,Category>{

}
