package com.shop.demo.dto;

import java.time.LocalDateTime;

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

public class Review {
    private int review_num;
    private String re_comment;
    private LocalDateTime  created_at;
    private int rating;
    private int membernum;
    private int product_num;
    
}
