package com.example.dto;

import com.example.dto.CartCookieItem;

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

public class CartCookieItem {
    private int productNum;
    private int quantity;

    // 생성자, getter 및 setter 메서드 추가
}
