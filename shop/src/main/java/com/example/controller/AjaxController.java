package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.Cart;
import com.example.dto.MemberList;
import com.example.service.CartService;
import com.example.service.MemberListService;

@RestController
public class AjaxController {

	@Autowired
	MemberListService mservice;
	
	@Autowired
	CartService cservice;

	// id찾기
	@GetMapping("/FindID")
	public Object findId(String mem_name, String email) {
		String mem_id = null;
		try {
			mem_id = mservice.findid(mem_name, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mem_id;
	}

	// 비밀번호찾기
	@GetMapping("/FindFwd")
	public Object findfwd(String mem_id, String email) {
		String pwd = null;
		try {
			pwd = mservice.findpwd(mem_id, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwd;
	}

	// 아이디 중복체크
	@PostMapping("/idCheck")
	public int idCheck(String mem_id) {
		int cnt = mservice.idCheck(mem_id);
		return cnt;
	}

	// 로그인 여부
	@GetMapping("/checkLogin")
	@ResponseBody
	public String checkLogin(HttpSession session) {
		String sessionMemberId = (String) session.getAttribute("member"); // 세션에서 멤버가져오기
		List<String> memberIdList = mservice.checkLogin();
		for (String memberId : memberIdList) {
			if (sessionMemberId.equals(memberId)) {
				return "true";
			}
		}
		return "false";
	}

	// 장바구니 추가
	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("productNum") int productNum, @RequestParam("quantity") int quantity,
			HttpSession session) {
		String sessionMemberId = (String)session.getAttribute("member");
		if(sessionMemberId==null) {
			return "false"; //로그인되지않은상태
		}
		
		int MemberNum = mservice.MemberNumByMemberId(sessionMemberId);
		//장바구니 리스트에 현재 상품찾기
		Cart cartlist = cservice.findCart(MemberNum, productNum);
		
		if(cartlist==null) {
			//장바구니에 상품이 없으면 추가
			cartlist = new Cart(productNum, quantity);
		    cservice.addToCart(MemberNum, cartlist);
		}
		else {
			// 이미 장바구니에 있는 상품의 수량만 증가시킴
			cartlist.setCounts(cartlist.getCounts()+quantity);
			cservice.updateCart(MemberNum, cartlist);
		}
	
	return "true";
}
}
