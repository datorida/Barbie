package com.example.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Product {
	private int product_num;
	private int quantity;
	private BigDecimal price;
	private String product_name;
	private String product_info;
	private String image_url;
	private int category_num;
	

	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Product findByProduct(int productNum, int quantity) {
		return null;
	}
}