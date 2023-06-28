package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.MemberList;
import com.example.frame.MyService;
import com.example.mapper.MemberListMapper;

@Service
public class MemberListService implements MyService<String, MemberList>{
	
	@Autowired
	MemberListMapper mmapper;

	@Override
	public void register(MemberList v) throws Exception {
		mmapper.insert(v);
	}

	@Override
	public void remove(String k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(MemberList v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberList get(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberList> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
