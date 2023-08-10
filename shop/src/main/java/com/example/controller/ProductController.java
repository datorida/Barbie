package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.Product;
import com.example.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/productList")
		public String productList(@RequestParam("productNum") int productNum, Model model) throws Exception {
			List<Product> allProducts = productService.get();
			Product product = productService.getProductByProductNum(productNum);
			model.addAttribute("product", product);
			model.addAttribute("products",allProducts);
			return "/product/productList";
			
		}
	}
