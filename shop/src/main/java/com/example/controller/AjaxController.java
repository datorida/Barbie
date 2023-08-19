package com.example.controller;

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
import com.example.service.ProductService;

@RestController
public class AjaxController {

	@Autowired
	MemberListService mservice;

	@Autowired
	CartService cservice;

	@Autowired
	ProductService pservice;

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

	// 로그인 여부 체크 컨트롤러
	@GetMapping("/checkLogin")
	@ResponseBody
	public String checkLogin(HttpSession session) {
	    MemberList sessionMember = (MemberList) session.getAttribute("member");
	    
	    if (sessionMember != null) {
	        return "true"; // 로그인된 상태
	    } else {
	        return "false"; // 비로그인 상태
	    }
	}

	// 장바구니 추가
	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("productNum") int productNum,
	                        @RequestParam("quantity") int quantityToCheck,
	                        HttpSession session) {
	    MemberList sessionMember = (MemberList) session.getAttribute("member");
	    String sessionMemberId = sessionMember != null ? sessionMember.getMem_id() : null;
	    
	    int memberNum = -1; // 인증되지 않은 사용자의 기본 값
	    if (sessionMemberId != null) {
	        memberNum = mservice.MemberNumByMemberId(sessionMemberId);
	    }
	    
	    try {
	        // 제품이 품절인지 확인
	        if (pservice.isOutOfStock(productNum, quantityToCheck)) {
	            return "out_of_stock"; // 클라이언트에 알림
	        }
	        
	        // 아이템이 이미 장바구니에 있는지 확인
	        Cart addCart = cservice.findCart(memberNum, productNum);
	        if (memberNum != -1 && addCart != null) {
	            int newQuantity = addCart.getCounts() + quantityToCheck;
	            addCart.setCounts(newQuantity);
	        } else {
	            // 아이템을 장바구니에 추가
	            cservice.addToCart(memberNum, productNum, quantityToCheck);
	        }
	        
	        return "true"; // 성공
	    } catch (Exception e) {
	        // 예외를 적절하게 처리
	        e.printStackTrace(); // 디버깅 목적으로 사용; 로깅을 고려하세요
	        return "error"; // 클라이언트에 오류를 알림
	    }
	}

}
