package com.example.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.dto.MemberList;
import com.example.frame.MyMapper;

@Mapper
@Repository
public interface MemberListMapper extends MyMapper<String, MemberList> {
	public MemberList findByUserId(String mem_id);


	public String findid(Map<String, String> parameters);


	public MemberList selectMember(String email);


	public void pwUpdate(String mem_id, String pwd, String email);


	public String findpwd(String mem_id, String email);


	public int idCheck(String mem_id);
}
