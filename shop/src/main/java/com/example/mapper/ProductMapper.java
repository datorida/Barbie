package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.dto.Product;
import com.example.frame.MyMapper;

@Mapper
@Repository
public interface ProductMapper extends MyMapper<Integer,Product>{

	String selectCategoryNameByCategoryNum(int categoryNum);

	Product getProductByProductNum(int productNum);

	Integer isOutOfStock(int productNum);



}
