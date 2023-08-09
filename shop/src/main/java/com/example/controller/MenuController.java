package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.dto.Product;
import com.example.service.ProductService;

@Controller
public class MenuController {
	
	@Autowired
	ProductService productService;
	
    @GetMapping("/menu")
    public String menu(Model model) throws Exception {
    	List<Product> products =productService.get();
    	
    	//각 상품의 카테고리 번호를 이용하여 카테고리 이름을 가져오고 설정
    	for(Product product : products) {
    		String categoryName = productService.selectCategoryNamebyCategoryNum(product.getCategory_num());
    		product.setCategoryName(categoryName);
    	}
    	model.addAttribute("products", products);
        return "menu"; // menu.html 파일을 반환합니다.
    }

}
