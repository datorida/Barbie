package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.Cart;
import com.example.dto.GuestCart;
import com.example.dto.MemberList;
import com.example.dto.Product;
import com.example.service.CartService;
import com.example.service.GuestCartService;
import com.example.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CartService cartService;

	@Autowired
	GuestCartService guestService;

	@GetMapping("/productList")
	public String productList(@RequestParam("productNum") int productNum, Model model) throws Exception {
		Product product = productService.getProductByProductNum(productNum);
		model.addAttribute("product", product);
		return "/product/productList";
	}

	@GetMapping("/cart")
	public String cartList(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
	    MemberList memberlist = (MemberList) session.getAttribute("member");
	    List<Cart> cartlist = new ArrayList<>();
	    List<GuestCart> guestCartList = new ArrayList<>();
	    String guestCookie =null;

	    if (memberlist != null) {
	        // 로그인 상태인 경우, 로그인한 사용자의 장바구니 정보를 가져옴

	        int memberNum = memberlist.getMembernum();
	        cartlist = cartService.getCartByMemberNum(memberNum);
	        for (Cart cartitem : cartlist) {
	            int productNum = cartitem.getProductNum();
	            Product product = productService.getProductByProductNum(productNum);
	            cartitem.setProduct(product);
	        }
	    } else {
	        // 비회원인 경우, 쿠키로부터 장바구니 정보를 가져오는 대신
	        // 임시식별자를 사용하여 데이터베이스에서 비회원 장바구니 정보를 가져옴
	        String temporaryIdentifier = getTemporaryIdentifierFromCookie(request);
	        if (temporaryIdentifier != null) {
	            guestCartList = guestService.getCartListBytemporaryIdentifier(temporaryIdentifier);
	            for (GuestCart guestCart : guestCartList) {
	                int productNum = guestCart.getProduct_num();
	                Product product = productService.getProductByProductNum(productNum);
	                guestCart.setProduct(product);
	            }
	        }
	    }

	    model.addAttribute("cartlist", cartlist); // 회원 장바구니
	    model.addAttribute("guestCartList", guestCartList); // 비회원 장바구니
	    return "/product/cart";
	}

	private String getTemporaryIdentifierFromCookie(HttpServletRequest request) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("temporaryIdentifier")) {
	                return cookie.getValue();
	            }
	        }
	    }
	    return null;
	}
}