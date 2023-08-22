package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.Cart;
import com.example.dto.CartCookieItem;
import com.example.dto.MemberList;
import com.example.dto.Product;
import com.example.service.CartService;
import com.example.service.ProductService;
import com.google.gson.Gson;

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

    // 장바구니페이지
    private final Gson gson = new Gson();

    @GetMapping("/cart")
    public String cartList(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        MemberList memberlist = (MemberList) session.getAttribute("member");
        List<Cart> cartlist = new ArrayList<>();
        List<Product> products = productService.get();

        if (memberlist != null) {
            // 로그인 상태인 경우, 로그인한 사용자의 장바구니 정보를 가져옴
            String memberId = memberlist.getMem_id();
            int memberNum = memberlist.getMembernum();
            cartlist = cartService.getCartByMemberNum(memberNum);
            for (Cart cartitem : cartlist) {
                int productNum = cartitem.getProductNum();
                Product product = productService.getProductByProductNum(productNum);
                cartitem.setProduct(product);
            }
        } else {
            // 비로그인 상태인 경우, 쿠키를 사용하여 임시 식별자를 관리
            String temporaryIdentifier = getTemporaryIdentifierFromCookie(request);
            String cartCookieValue = getCookie("cart_" + temporaryIdentifier, request);
            
            
            if (temporaryIdentifier == null) {
                // 쿠키에 임시 식별자가 없으면 생성
                temporaryIdentifier = generateTemporaryIdentifier();
                // 생성한 임시 식별자를 쿠키에 설정
                setTemporaryIdentifierCookie(response, temporaryIdentifier);
            }

            // 해당 임시 식별자의 장바구니 정보를 가져옴 (쿠키에서 읽어옴)
            cartlist = getCartItemsFromCookie(temporaryIdentifier, cartCookieValue);
            model.addAttribute("temporaryIdentifier", temporaryIdentifier); // 임시 식별자를 모델에 추가
        }

        model.addAttribute("cartlist", cartlist);
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

    private void setTemporaryIdentifierCookie(HttpServletResponse response, String temporaryIdentifier) {
        Cookie cookie = new Cookie("cart_" + temporaryIdentifier, "");
        cookie.setMaxAge(86400);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private String generateTemporaryIdentifier() {
        // UUID를 사용하여 임시 식별자 생성
        return UUID.randomUUID().toString();
    }

    private String getCookie(String cookieName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    // 쿠키에서 장바구니 아이템을 읽어오는 메서드
    private List<Cart> getCartItemsFromCookie(String temporaryIdentifier, String cartCookie) {
        List<Cart> cartlist = new ArrayList<>();

        if (cartCookie != null && cartCookie.startsWith("[")) {
            List<CartCookieItem> cartItems = Arrays.asList(gson.fromJson(cartCookie, CartCookieItem[].class));

            for (CartCookieItem cartItem : cartItems) {
                Cart cart = new Cart();
                cart.setTemporaryIdentifier(temporaryIdentifier);
                cart.setProductNum(cartItem.getProductNum());
                cart.setCounts(cartItem.getQuantity());
                cartlist.add(cart);
            }
        }

        return cartlist;
    }
    
    
  
    
}
