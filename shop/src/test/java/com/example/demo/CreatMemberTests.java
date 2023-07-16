package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dto.MemberList;
import com.example.service.MemberListService;

@SpringBootTest
public class CreatMemberTests {
	@Autowired
	MemberListService mservice;
	
	@Test
	void contextLoads() {
		MemberList obj = new MemberList(1, "chungwoon73", "박하", "chungwoon73@google.com", "01086168407", "qkrgkektha1!", "경주시 황성동");
		
		try {
			mservice.register(obj);
			System.out.println("ok");
		}catch (Exception e) {
			System.out.println("Failed");
			e.printStackTrace();
		}
	 }
	}
