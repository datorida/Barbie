package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.Cart;
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
			List<Product> allProducts = productService.get();
			Product product = productService.getProductByProductNum(productNum);
			model.addAttribute("product", product);
			model.addAttribute("products",allProducts);
			return "/product/productList";
			
		}
	
	//장바구니페이지
	@GetMapping("/cartList")
	public String cartList(Model model) throws Exception {
		List<Cart> cartlist = cartService.get();
		List<Product>products =new ArrayList<>(); //상품정보를 담을 리스트
		for(Cart cart : cartlist) {
			int productNum=cart.getProduct_num();
			Product product=productService.getProductByProductNum(productNum);
			products.add(product); //상품정보추가
		}
		model.addAttribute("cartlist", cartlist);
		model.addAttribute("product", products);
		return "/product/cart";
		
	}

}
