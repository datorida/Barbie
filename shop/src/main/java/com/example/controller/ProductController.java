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
import com.example.dto.MemberList;
import com.example.dto.Product;
import com.example.service.CartService;
import com.example.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CartService cartService;
	
	

	@GetMapping("/productList")
		public String productList(@RequestParam("productNum") int productNum, Model model) throws Exception {
			Product product = productService.getProductByProductNum(productNum);
			model.addAttribute("product", product);
			return "/product/productList";
			
		}
	
	//장바구니페이지
	@GetMapping("/cart")
	public String cartList(Model model, HttpSession session, HttpServletRequest request) throws Exception {
	    MemberList memberlist = (MemberList) session.getAttribute("member");
	    List<Cart> cartlist = new ArrayList<>();
	    List<Product> products = productService.get();
	    // 로그인 상태인 경우, 내장바구니 정보 가져오기
	    if (memberlist != null) {
	        System.out.println(memberlist.getMem_id());
	        cartlist = cartService.get();
	    }

	    // 쿠키에서 장바구니 정보 가져오기
	    List<Cart> cookieCartList = getCartFromCookie(request);

	    for (Cart cart : cartlist) {
	        int productNum = cart.getProductNum();
	        Product product = productService.getProductByProductNum(productNum);
	        if (product != null && product.getProduct_name() != null) {
	            products.add(product);
	        }
	    }
	    
	    
	    // 모델에 내장바구니 정보와 쿠키 장바구니 정보를 추가
	    model.addAttribute("cartlist", cartlist);
	    model.addAttribute("cookieCartList", cookieCartList);

	    return "/product/cart";
	}

	private List<Cart> getCartFromCookie(HttpServletRequest request) {
	    List<Cart> cartList = new ArrayList<>();
	    Cookie[] cookies = request.getCookies();

	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("cart")) {
	                String cartJson = cookie.getValue();
	                // cartJson을 파싱하여 Cart 객체로 변환하고 cartList에 추가하는 코드 작성
	                // 예: cartList.add(new Cart(productNum, counts));
	            }
	        }
	    }

	    return cartList;
	}
}
