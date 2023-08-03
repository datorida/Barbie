package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.MemberListService;

@RestController
public class AjaxController {
	
	@Autowired
	MemberListService mservice;
	
	//id찾기
	@RequestMapping("/FindID")
		public Object findId(String mem_name, String email) {
		String mem_id=null;
		try {
			mem_id=mservice.findid(mem_name, email);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mem_id;
	}
	
	//비밀번호찾기
	@RequestMapping("/FindFwd")
		public Object findfwd(String mem_id, String email) {
			String pwd=null;
			try {
				pwd=mservice.findpwd(mem_id, email);
			}catch(Exception e){
				e.printStackTrace();
			}
			return pwd;
	}
	
	//아이디 중복체크
	@RequestMapping("/idCheck")
		public int idCheck(String mem_id) {
		int cnt= mservice.idCheck(mem_id);
		return cnt;
	}
	}
	
	
