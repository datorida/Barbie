package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.MemberList;
import com.example.dto.Product;
import com.example.frame.MyService;
import com.example.mapper.MemberListMapper;

@Service
public class MemberListService implements MyService<String, MemberList> {

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
		return mmapper.select(k);
	}

	@Override
	public List<MemberList> get() throws Exception {
		// TODO Auto-generated method stub
		return mmapper.selectAll();
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

	public MemberList selectMember(String email) {
		return mmapper.selectMember(email);
	}

	public void pwUpdate_M(String mem_id, String pwd, String email) throws Exception{
		mmapper.pwUpdate(mem_id, pwd, email);
	}
	
	public String findpwd(String mem_id, String email) {
		return mmapper.findpwd(mem_id, email);
	}

	public int idCheck(String mem_id) {
		int cnt = mmapper.idCheck(mem_id);
		return cnt;
	}

	@Override
	public Product getProductByProductNum(int productNum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> checkLogin() {
	    return mmapper.checkLogin();
	}

	public int MemberNumByMemberId(String sessionMemberId) {
		return mmapper.MemberNumByMemberId(sessionMemberId);
	}


	

}
