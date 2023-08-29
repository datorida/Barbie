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
import org.springframework.web.bind.annotation.ResponseBody;

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

	

	@RequestMapping("/productList")
	public String productList(@RequestParam("productNum") int productNum, Model model, HttpSession session) throws Exception {
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
	    String temporaryIdentifier = null;

	    // 임시 식별자를 쿠키에서 추출
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("temporaryIdentifier".equals(cookie.getName())) {
	                temporaryIdentifier = cookie.getValue();
	                break;
	            }
	        }
	    }

	    if (memberlist != null) {
	        // 로그인 상태인 경우, 로그인한 사용자의 장바구니 정보를 가져옴
	        int memberNum = memberlist.getMembernum();
	        cartlist = cartService.getCartByMemberNum(memberNum);
	        for (Cart cartitem : cartlist) {
	            int productNum = cartitem.getProductNum();
	            Product product = productService.getProductByProductNum(productNum);
	            cartitem.setProduct(product);
	        }
	    } else if (temporaryIdentifier != null) {
	        // 임시 식별자를 사용하여 비회원 장바구니 정보를 가져옴
	        guestCartList = guestService.getCartListBytemporaryIdentifier(temporaryIdentifier);
	        for (GuestCart guestCartItem : guestCartList) {
	            int productNum = guestCartItem.getProductNum();
	            Product product = productService.getProductByProductNum(productNum);
	            guestCartItem.setProduct(product);
	        }
	    }

	    // 모델에 데이터 추가
	    model.addAttribute("cartlist", cartlist); // 회원 장바구니
	    model.addAttribute("guestCartList", guestCartList); // 비회원 장바구니
	    model.addAttribute("temporaryIdentifier", temporaryIdentifier); // 임시 식별자
	    return "/product/cart";
	}

}
