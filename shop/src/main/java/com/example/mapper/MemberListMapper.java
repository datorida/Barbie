package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.dto.MemberList;
import com.example.frame.MyMapper;

@Mapper
@Repository
public interface MemberListMapper extends MyMapper<String, MemberList> {

}
