package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.MemberListService;

@RestController
public class LoginController {
	
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
	
	@RequestMapping("/FindFwd")
		public String FindIFwd(Model model) {
		return "FindFwd";
	}
	
	
}
