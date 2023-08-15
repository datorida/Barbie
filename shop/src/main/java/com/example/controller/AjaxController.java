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
	public String addToCart(@RequestParam("productNum") int productNum, @RequestParam("quantity") int quantityToCheck,
			HttpSession session) throws Exception {
		 MemberList sessionMember=(MemberList) session.getAttribute("member");
		    String sessionMemberId= sessionMember !=null ? sessionMember.getMem_id() : null;
		    int memberNum = sessionMemberId !=null ? mservice.MemberNumByMemberId(sessionMemberId) : -1; //-1이나 0은 일반적으로 로그인이 되지 않은 상태
	
		//상품의 재고가 부족한 경우
		boolean isOutOfStock = pservice.isOutOfStock(productNum, quantityToCheck);
		if (isOutOfStock) {
			return "out_of_stock"; //클라이언트로 전달 
		}
		
		List<Cart> cartlist = cservice.get();
		Cart addCart =cservice.findCart(memberNum, productNum);
		
		if (memberNum==-1) {
			
		}else if(addCart != null) {
			int newQuantity = addCart.getCounts()+quantityToCheck;
			addCart.setCounts(newQuantity);
		}else {
			cservice.addToCart(memberNum, productNum, quantityToCheck);
		}
		return "true";
		}
		}

