package com.example.dto;

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

public class Cart {
    private int cart_num;
    private int product_num;
    private int counts;
    private int membernum;

    public Cart(int product_num, int counts) {
        this.product_num = product_num;
        this.counts = counts;
    }

    // Getter and setter methods...
}
