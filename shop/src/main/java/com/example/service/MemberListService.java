package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.MemberList;
import com.example.frame.MyService;
import com.example.mapper.MemberListMapper;

@Service
public class MemberListService implements MyService<String, MemberList> {

	@Autowired
	MemberListMapper mmapper;

	private BCryptPasswordEncoder passwordEncoder;

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
		return mmapper.select(k);
	}

	@Override
	public List<MemberList> get() throws Exception {
		// TODO Auto-generated method stub
		return mmapper.selectall();
	}

	public boolean authenticate(String mem_id, String pwd) throws Exception {
		MemberList user = mmapper.findByUserId(mem_id);
		if (user != null && user.getPwd().equals(pwd)) {
			return true;
		}
		return false;
	}

	public String findid(String mem_name, String email) throws Exception {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("mem_name",mem_name);
		parameters.put("email", email);
		return mmapper.findid(parameters);
	}



}
