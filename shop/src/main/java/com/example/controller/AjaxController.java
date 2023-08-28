package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.Cart;
import com.example.dto.GuestCart;
import com.example.dto.MemberList;
import com.example.service.CartService;
import com.example.service.GuestCartService;
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
	
	@Autowired
	GuestCartService guestService;

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
	public String checkLogin(HttpSession session, Model model) {
		MemberList sessionMember = (MemberList) session.getAttribute("member");

		if (sessionMember != null) {
			model.addAttribute("username", sessionMember.getMem_id());
			return "true";
		} else {
			return "false";
		}
	}

	// 장바구니 추가
	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("productNum") int productNum, @RequestParam("quantity") int quantityToCheck,
			HttpSession session) {
		MemberList sessionMember = (MemberList) session.getAttribute("member");
		String sessionMemberId = sessionMember != null ? sessionMember.getMem_id() : null;

		int memberNum = mservice.MemberNumByMemberId(sessionMemberId);

		try {
			// 제품의 재고확인
			boolean isOutofStock = pservice.isOutOfStock(productNum, quantityToCheck);

			if (isOutofStock) {
				return "out_of_stock"; // 클라이언트에 알림
			}

			// 아이템이 이미 장바구니에 있는지 확인
			Cart cart = cservice.findCart(memberNum, productNum);
			if (memberNum != 0 && cart != null) {
				int newQuantity = cart.getCounts() + quantityToCheck;
				cart.setCounts(newQuantity); // 이렇게하면 메모리상에서의 값만 변경되고 데이터베이스에서는 업데이트안됨.
				cservice.modify(cart);// 이렇게해줘야 데이터베이스에들어감
				return "existingCart";

			} else {
				// 장바구니에 없는경우 아이템을 장바구니에 추가
				cservice.addToCart(memberNum, productNum, quantityToCheck);
			}
			return "true"; // 성공
		} catch (Exception e) {
			// 예외를 적절하게 처리
			e.printStackTrace(); // 디버깅 목적으로 사용; 로깅을 고려하세요
			return "error"; // 클라이언트에 오류를 알림
		}
	}

	@PostMapping("/addToCartAsGuest")
	@ResponseBody
	public String addToCartAsGuest(@RequestParam("productNum") int productNum, @RequestParam("quantity") int quantity,
			@RequestParam("temporaryIdentifier") String temporaryIdentifier) {

		// 여기에 비회원의 장바구니에 상품 추가 로직을 구현합니다.
		// temporaryIdentifier를 사용하여 장바구니를 식별하고, productNum과 quantity를 이용해 상품을 추가합니다.
		try {
			// 제품 재고확인
			boolean isOutofStock = pservice.isOutOfStock(productNum, quantity);
			System.out.println(temporaryIdentifier);

			if (isOutofStock) {
				return "out_of_stock"; // 클라이언트에 알림
			}
			// 비회원 장바구니 정보 조회 또는 생성 및 업데이트
		
			// GuestCart 정보 가져오기
			List<GuestCart> guestCarts = guestService.getCartListBytemporaryIdentifier(temporaryIdentifier);

			if (guestCarts == null || guestCarts.isEmpty()) {
			    // 장바구니가 없는 경우 새로 생성
			    GuestCart newGuestCart = new GuestCart();
			    newGuestCart.setProduct_num(productNum);
			    newGuestCart.setCounts(quantity);
			    newGuestCart.setTemporaryIdentifier(temporaryIdentifier);
			    guestService.addToGuestCart(newGuestCart);
			    System.out.println(newGuestCart);
			    return "true";
			} else {
			    // 해당 제품이 이미 장바구니에 있는지 확인
			    for (GuestCart guest : guestCarts) {
			        if (guest.getProduct_num() == productNum) {
			            int existingQuantity = guest.getCounts();
			            guest.setCounts(existingQuantity + quantity);
			            guestService.modify(guest);
			            System.out.println(guest.getCounts());
			            return "existingCart";
			        }
			    }
			    // 장바구니에 없는 경우 새로운 GuestCart를 생성하고 추가
			    GuestCart newGuestCart = new GuestCart();
			    newGuestCart.setProduct_num(productNum);
			    newGuestCart.setCounts(quantity);
			    newGuestCart.setTemporaryIdentifier(temporaryIdentifier);
			    guestService.addToGuestCart(newGuestCart);
			    return "true";
			}

			
		} catch (Exception e) {
			// 예외를 적절하게 처리
			e.printStackTrace(); // 디버깅 목적으로 사용; 로깅을 고려하세요
			return "error"; // 클라이언트에 오류를 알림
		}
	}

}
